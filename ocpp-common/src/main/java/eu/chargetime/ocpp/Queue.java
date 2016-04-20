package eu.chargetime.ocpp;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Thomas Volden on 18-Apr-16.
 */
public class Queue
{
    HashMap<String, String> requestQueue;

    public Queue () {
        requestQueue = new HashMap<>();
    }

    public String store(String request) {
        String ticket = UUID.randomUUID().toString();
        requestQueue.put(ticket, request);
        return ticket;
    }

    public String restoreRequest(String ticket) {
        String request = null;
        try {
            request = requestQueue.get(ticket);
            requestQueue.remove(ticket);
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
        return request;
    }
}
