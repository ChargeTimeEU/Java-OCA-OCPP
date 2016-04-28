package eu.chargetime.ocpp;

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Request;
import org.json.JSONArray;

import java.util.ArrayList;
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
    private ArrayList<Feature> featureList;

    public Client(Transmitter transmitter, Queue queue, Communicator communicator) {
        this.promises = new HashMap<>();
        this.featureList = new ArrayList<>();

        this.transmitter = transmitter;
        this.queue = queue;
        this.communicator = communicator;
    }

    public void addFeatureProfile(Profile profile) {
        Feature[] features = profile.getFeatureList();
        for (Feature feature: features)
            featureList.add(feature);
    }

    private Class<? extends Request> getRequestType(String action) {
        return findFeature(action).getRequestType();
    }

    private Class<? extends Confirmation> getConfirmationType(String id) {
        Request request = queue.restoreRequest(id);
        return findFeature(request).getConfirmationType();
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
                Object[] message = unpack(s);
                if (message[0].equals(3)) {
                    Confirmation conf = communicator.unpack(message[2].toString(), getConfirmationType(id));
                    promises.get(id).complete(conf);
                } else if (message[0].equals(2)) {
                    Request request = communicator.unpack(message[3].toString(), getRequestType(message[2].toString()));

                }
            }

            @Override
            public void connected() { }

            @Override
            public void disconnected() { }
        };
        transmitter.connect(uri, transmitterEvents);
    }

    private Object[] unpack(String message) {
        if (message == null || "".equals(message))
            return null;

        JSONArray json = new JSONArray(message);
        Object[] output = new Object[json.length()];
        for (int i = 0; i < json.length(); i++) {
            output[i] = json.get(i);
        }
        return output;
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

    private Feature findFeature(String action) {
        Feature output = null;

        for(Feature feature: featureList) {
            if (feature.getAction().equals(action)) {
                output = feature;
                break;
            }
        }

        return output;
    }

    private Feature findFeature(Request request) {
        Feature output = null;

        for(Feature feature: featureList) {
            if (feature.getRequestType().equals(request.getClass())) {
                output = feature;
                break;
            }
        }

        return output;
    }

    public CompletableFuture<Confirmation> send(Request request) {
        Feature feature = findFeature(request);
        String id = storeRequest(request);
        CompletableFuture<Confirmation> promise = createPromise(id);

        transmitter.send(createCallMessage(id, feature.getAction(), request));
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

    private String createCallMessage(String uniqueId, String action, Request payload) {
        return String.format(CALLFORMAT, uniqueId, action, communicator.pack(payload));
    }
}
