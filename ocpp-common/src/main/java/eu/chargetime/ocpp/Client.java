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
    private final int INDEX_UNIQUEID = 1;

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
        return extractValueAt(message, INDEX_UNIQUEID);
    }

    private String extractValueAt(String message, int index) {
        if (message == null || "".equals(message))
            return "";
        String[] segments = message.substring(1, message.length()-1).split(",");
        return segments[index].substring(1, segments[index].length()-1);
    }

    public CompletableFuture<Request> send(Request request)
    {
        String id = storeRequest(request);
        CompletableFuture<Request> promise = createPromise(id);

        transmitter.send(createCallMessage(id, request.action(), new JSONObject(request)));
        return promise;
    }

    private CompletableFuture<Request> createPromise(String uniqueId) {
        CompletableFuture<Request> promise = new CompletableFuture<>();
        promises.put(uniqueId, promise);
        return promise;
    }

    private String storeRequest(Request request) {
        return queue.store(request);
    }

    private String createCallMessage(String uniqueId, String action, JSONObject payload) {
        return String.format(CALLFORMAT, uniqueId, action, payload);
    }
}
