package eu.chargetime.ocpp;

import eu.chargetime.ocpp.v1_6.WebSocketTransmitter;

import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Thomas Volden on 20-Apr-16.
 */
public class Client
{
    private String CALLFORMAT = "[2,\"%s\",\"%s\",{%s}]";

    private Queue queue;
    private HashMap<String, CompletableFuture<String>> promises;
    private Transmitter transmitter;

    public Client(Transmitter transmitter, Queue queue) {
        this.queue = queue;
        promises = new HashMap<>();
        this.transmitter = transmitter;
    }

    public void connect(String uri)
    {
        TransmitterEvents transmitterEvents = new TransmitterEvents()
        {
            @Override
            public void receivedMessage(String s) {
                System.out.println("ChargePoint   - Message received: " + s);
                String id = getUniqueId(s);
                promises.get(id).complete(queue.restoreRequest(id));
            }

            @Override
            public void connected() { }

            @Override
            public void disconnected() { }
        };
        transmitter.connect(uri, transmitterEvents);
    }

    public void disconnect()
    {
        try {
            transmitter.disconnect();
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
    }

    private String getUniqueId(String message)
    {
        if (message == null || "".equals(message))
            return "";

        String[] segments = message.substring(1, message.length()-1).split(",");
        return segments[1].substring(1, segments[1].length()-1);
    }

    public CompletableFuture<String> send(String request, String payload)
    {
        String id = queue.store(request);
        CompletableFuture<String> promis = new CompletableFuture<>();
        promises.put(id, promis);
        transmitter.send(String.format(CALLFORMAT, id, request, payload));
        return promis;
    }
}
