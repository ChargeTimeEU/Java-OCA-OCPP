package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

import java.util.concurrent.CompletableFuture;

/**
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
public class Session {

    private Communicator communicator;
    private Queue queue;
    private SessionEvents events;

    public Session(Communicator communicator, Queue queue) {
        this.communicator = communicator;
        this.queue = queue;
    }

    public String sendRequest(String action, Request payload) {
        String uuid = queue.store(payload);
        communicator.sendCall(uuid, action, payload);
        return uuid;
    }

    public void sendConfirmation(String uniqueId, Confirmation confirmation) {
        communicator.sendCallResult(uniqueId, confirmation);
    }

    private Class<? extends Confirmation> getConfirmationType(String uniqueId) {
        Request request = queue.restoreRequest(uniqueId);
        Feature feature = events.findFeatureByRequest(request);
        return feature.getConfirmationType();
    }

    public void open(String uri, SessionEvents eventHandler) {
        this.events = eventHandler;
        communicator.connect(uri, new CommunicatorEventHandler());
    }

    public void close() {
        communicator.disconnect();
    }

    private class CommunicatorEventHandler implements CommunicatorEvents {
        @Override
        public void onCallResult(String id, String payload) {
            try {
                Confirmation confirmation = communicator.unpackPayload(payload, getConfirmationType(id));
                if (confirmation.validate()) {
                    events.handleConfirmation(id, confirmation);
                } else {
                    communicator.sendCallError(id, "OccurenceConstraintViolation", "Payload for Action is syntactically correct but at least one of the fields violates occurence constraints");
                }
            }
            catch (PropertyConstraintException ex) {
                String message = "Field %s violates constraints with value: \"%s\". %s";
                communicator.sendCallError(id, "TypeConstraintViolation", String.format(message, ex.getFieldKey(), ex.getFieldValue(), ex.getMessage()));
                ex.printStackTrace();
            } catch (Exception ex) {
                communicator.sendCallError(id, "FormationViolation", "Unable to process action");
                ex.printStackTrace();
            }
        }

        @Override
        public void onCall(String id, String action, String payload) {
            Feature feature = events.findFeatureByAction(action);
            if (feature != null) {
                try {
                    Request request = communicator.unpackPayload(payload, feature.getRequestType());
                    if (request.validate()) {
                        CompletableFuture<Confirmation> promise = handleIncomingRequest(request);
                        promise.whenComplete(((confirmation, throwable) -> {
                            if (promise.isCompletedExceptionally()) {
                                communicator.sendCallError(id, "InternalError", "An internal error occurred and the receiver was not able to process the requested Action successfully");
                            } else if (confirmation == null) {
                                communicator.sendCallError(id, "NotSupported", "Requested Action is recognized but not supported by the receiver");
                            } else {
                                communicator.sendCallResult(id, confirmation);
                            }
                        }));
                    } else {
                        communicator.sendCallError(id, "OccurenceConstraintViolation", "Payload for Action is syntactically correct but at least one of the fields violates occurence constraints");
                    }
                } catch (PropertyConstraintException ex) {
                    String message = "Field %s violates constraints with value: \"%s\". %s";
                    communicator.sendCallError(id, "TypeConstraintViolation", String.format(message, ex.getFieldKey(), ex.getFieldValue(), ex.getMessage()));
                    ex.printStackTrace();
                } catch (Exception ex) {
                    communicator.sendCallError(id, "FormationViolation", "Unable to process action");
                    ex.printStackTrace();
                }
            } else {
                communicator.sendCallError(id, "NotImplemented", "Requested Action is not known by receiver");
            }
        }

        @Override
        public void onError(String id, String payload) { }

        @Override
        public void onDisconnected() { }

        @Override
        public void onConnected() { }

        private CompletableFuture<Confirmation> handleIncomingRequest(Request request) {
            CompletableFuture<Confirmation> promise = new CompletableFuture<>();
            new Thread(() -> {
                try {
                    Confirmation conf = events.handleRequest(request);
                    promise.complete(conf);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    promise.completeExceptionally(ex);
                }
            }).start();
            return promise;
        }
    }
}
