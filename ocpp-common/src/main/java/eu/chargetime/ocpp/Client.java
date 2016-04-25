package eu.chargetime.ocpp;

import eu.chargetime.ocpp.model.Request;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Thomas Volden on 20-Apr-16.
 */
public class Client
{
    private final String CALLFORMAT = "[2,\"%s\",\"%s\",%s]";

    private Queue queue;
    private HashMap<String, CompletableFuture<Request>> promises;
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
                // TODO: Remove debug code
                System.out.println("Client        - Message received: " + s);

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

    public CompletableFuture<Request> send(Request request)
    {
        String id = queue.store(request);
        CompletableFuture<Request> promise = new CompletableFuture<>();
        promises.put(id, promise);
        transmitter.send(String.format(CALLFORMAT, id, request.action(), new JSONObject(request)));
        return promise;
    }
}
