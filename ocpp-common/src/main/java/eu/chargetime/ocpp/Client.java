package eu.chargetime.ocpp;

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.profiles.CoreProfile;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

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
    private HashMap<String, CompletableFuture<Confirmation>> promises;
    private Transmitter transmitter;
    private Communicator communicator;
    private CoreProfile core;

    public Client(Transmitter transmitter, Queue queue, CoreProfile core, Communicator communicator) {
        this.core = core;
        this.communicator = communicator;
        this.queue = queue;
        this.transmitter = transmitter;

        promises = new HashMap<>();
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
                Confirmation conf = communicator.unpack(s, core.findConfirmation(queue.restoreRequest(id)).getClass());
                promises.get(id).complete(conf);
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
        String[] segments = message.substring(1, message.length()-1).split(",", 3);
        return segments[index].substring(1, segments[index].length()-1);
    }

    public CompletableFuture<Confirmation> send(Request request)
    {
        String id = storeRequest(request);
        CompletableFuture<Confirmation> promise = createPromise(id);

        transmitter.send(createCallMessage(id, request.action(), new JSONObject(request)));
        return promise;
    }

    private CompletableFuture<Confirmation> createPromise(String uniqueId) {
        CompletableFuture<Confirmation> promise = new CompletableFuture<>();
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
