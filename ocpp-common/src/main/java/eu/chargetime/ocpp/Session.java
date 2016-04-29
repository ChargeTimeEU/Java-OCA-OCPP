package eu.chargetime.ocpp;

import eu.chargetime.ocpp.model.Request;

/**
 * Created by Thomas Volden on 29-Apr-16.
 */
public class Session {

    private Communicator communicator;
    private Queue queue;

    public Session(Communicator communicator, Queue queue) {
        this.communicator = communicator;
        this.queue = queue;
    }

    public String sendRequest(String action, Request payload) {
        String uuid = queue.store(payload);
        communicator.sendCall(uuid, action, payload);
        return uuid;
    }
}
