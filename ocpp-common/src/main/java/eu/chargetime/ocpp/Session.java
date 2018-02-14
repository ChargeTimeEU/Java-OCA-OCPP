package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.CompletableFuture;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (C) 2016-2018 Thomas Volden

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

/**
 * Unites outgoing {@link Request} with incoming {@link Confirmation}s or errors.
 * Catches errors and responds with error messages.
 */
public class Session implements ISession {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Session.class);

    private final Communicator communicator;
    private final Queue queue;
    private final RequestDispatcher dispatcher;
    private final IFeatureRepository featureRepository;
    private SessionEvents events;

    /**
     * Handles required injections.
     *
     * @param communicator          send and receive messages.
     * @param queue                 store and restore requests based on unique ids.
     */
    public Session(Communicator communicator, Queue queue, PromiseFulfiller fulfiller, IFeatureRepository featureRepository) {
        this.communicator = communicator;
        this.queue = queue;
        this.dispatcher = new RequestDispatcher(fulfiller);
        this.featureRepository = featureRepository;
    }

    /**
     * Send a {@link Request}.
     *
     * @param action    action name to identify the feature.
     * @param payload   the {@link Request} payload to send
     * @param uuid      unique identification to identify the request
     */
    public void sendRequest(String action, Request payload, String uuid) {
        communicator.sendCall(uuid, action, payload);
    }

    /**
     * Store a {@link Request} and get the unique id.
     *
     * @param payload the {@link Request} payload to send
     * @return unique identification to identify the request.
     */
    public String storeRequest(Request payload) {
        return queue.store(payload);
    }

    /**
     * Send a {@link Confirmation} to a {@link Request}
     *
     * @param uniqueId     the unique identification the receiver expects.
     * @param confirmation the {@link Confirmation} payload to send.
     */
    public void sendConfirmation(String uniqueId, String action, Confirmation confirmation) {
        communicator.sendCallResult(uniqueId, action, confirmation);
    }


    private Class<? extends Confirmation> getConfirmationType(String uniqueId) throws UnsupportedFeatureException {
        Request request = queue.restoreRequest(uniqueId);
        Feature feature = featureRepository.findFeature(request);
        if (feature == null)
            throw new UnsupportedFeatureException("Error with getting confirmation type: request = " + request.toString() + ", feature = " + feature);
        return feature.getConfirmationType();
    }

    /**
     * Connect to a specific uri, provided a call back handler for connection related events.
     *
     * @param uri             url and port of the remote system.
     * @param eventHandler    call back handler for connection related events.
     */
    public void open(String uri, SessionEvents eventHandler) {
        this.events = eventHandler;
        dispatcher.setEventHandler(eventHandler);
        communicator.connect(uri, new CommunicatorEventHandler());
    }

    /**
     * Close down the connection.
     */
    public void close() {
        communicator.disconnect();
    }

    public void accept(SessionEvents eventHandler) {
        this.events = eventHandler;
        dispatcher.setEventHandler(eventHandler);
        communicator.accept(new CommunicatorEventHandler());
    }

    private class CommunicatorEventHandler implements CommunicatorEvents {
        private static final String OCCURENCE_CONSTRAINT_VIOLATION = "Payload for Action is syntactically correct but at least one of the fields violates occurence constraints";
        private static final String FIELD_CONSTRAINT_VIOLATION = "Field %s violates constraints with value: \"%s\". %s";
        private static final String INTERNAL_ERROR = "An internal error occurred and the receiver was not able to process the requested Action successfully";
        private static final String UNABLE_TO_PROCESS = "Unable to process action";

        @Override
        public void onCallResult(String id, String action, Object payload) {
            try {
                Confirmation confirmation = communicator.unpackPayload(payload, getConfirmationType(id));
                if (confirmation.validate()) {
                    events.handleConfirmation(id, confirmation);
                } else {
                    communicator.sendCallError(id, action, "OccurenceConstraintViolation", OCCURENCE_CONSTRAINT_VIOLATION);
                }
            } catch (PropertyConstraintException ex) {
                String message = String.format(FIELD_CONSTRAINT_VIOLATION, ex.getFieldKey(), ex.getFieldValue(), ex.getMessage());
                logger.warn(message, ex);
                communicator.sendCallError(id, action, "TypeConstraintViolation", message);
            } catch (UnsupportedFeatureException ex) {
                logger.warn(INTERNAL_ERROR, ex);
                communicator.sendCallError(id, action, "InternalError", INTERNAL_ERROR);
            } catch (Exception ex) {
                logger.warn(UNABLE_TO_PROCESS, ex);
                communicator.sendCallError(id, action, "FormationViolation", UNABLE_TO_PROCESS);
            }
        }

        @Override
        synchronized public void onCall(String id, String action, Object payload) {
            Feature feature = featureRepository.findFeature(action);
            if (feature == null) {
                communicator.sendCallError(id, action, "NotImplemented", "Requested Action is not known by receiver");
            } else {
                try {
                    Request request = communicator.unpackPayload(payload, feature.getRequestType());
                    if (request.validate()) {
                        CompletableFuture<Confirmation> promise = dispatcher.handleRequest(request);
                        promise.whenComplete(new ConfirmationHandler(id, action, communicator));
                    } else {
                        communicator.sendCallError(id, action, "OccurenceConstraintViolation", OCCURENCE_CONSTRAINT_VIOLATION);
                    }
                } catch (PropertyConstraintException ex) {
                    String message = String.format(FIELD_CONSTRAINT_VIOLATION, ex.getFieldKey(), ex.getFieldValue(), ex.getMessage());
                    logger.warn(message, ex);
                    communicator.sendCallError(id, action, "TypeConstraintViolation", message);
                } catch (Exception ex) {
                    logger.warn(UNABLE_TO_PROCESS, ex);
                    communicator.sendCallError(id, action, "FormationViolation", UNABLE_TO_PROCESS);
                }
            }
        }

        @Override
        public void onError(String id, String errorCode, String errorDescription, Object payload) {
            events.handleError(id, errorCode, errorDescription, payload);
        }

        @Override
        public void onDisconnected() {
            events.handleConnectionClosed();
        }

        @Override
        public void onConnected() {
            events.handleConnectionOpened();
        }

    }
}
