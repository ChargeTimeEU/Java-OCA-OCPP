package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Request;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Thomas Volden on 18-Apr-16.
 */
public class Queue
{
    HashMap<String, Request> requestQueue;

    public Queue () {
        requestQueue = new HashMap<>();
    }

    public String store(Request request) {
        String ticket = UUID.randomUUID().toString();
        requestQueue.put(ticket, request);
        return ticket;
    }

    public Request restoreRequest(String ticket) {
        Request request = null;
        try {
            request = requestQueue.get(ticket);
            requestQueue.remove(ticket);
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
        return request;
    }
}
