package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import groovyx.gpars.dataflow.Promise;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Thomas Volden on 29-Apr-16.
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

    private Class<? extends Request> getRequestType(String action) {
        Feature feature = events.findFeatureByAction(action);
        return feature.getRequestType();
    }

    public void open(String uri, SessionEvents eventHandler) {
        this.events = eventHandler;
        communicator.connect(uri, new CommunicatorEventHandler());
    }

    private class CommunicatorEventHandler implements CommunicatorEvents {
        @Override
        public void onCallResult(String id, String payload) {
            events.handleConfirmation(id, communicator.unpackPayload(payload, getConfirmationType(id)));
        }

        @Override
        public void onCall(String id, String action, String payload) {
            Feature feature = events.findFeatureByAction(action);
            if (feature != null) {
                handleIncommingRequest(communicator.unpackPayload(payload, feature.getRequestType()))
                        .whenComplete(((confirmation, throwable) -> communicator.sendCallResult(id, confirmation)));
            }
        }

        @Override
        public void onError(String id, String payload) { }

        @Override
        public void onDisconnected() { }

        @Override
        public void onConnected() { }

        private CompletableFuture<Confirmation> handleIncommingRequest(Request request) {
            CompletableFuture<Confirmation> promise = new CompletableFuture<>();
            new Thread(() -> {
                Confirmation conf = events.handleRequest(request);
                promise.complete(conf);
            }).start();
            return promise;
        }
    }
}
