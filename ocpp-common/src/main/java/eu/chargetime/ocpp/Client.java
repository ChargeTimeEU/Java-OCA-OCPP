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
    private final int INDEX_UNIQUEID = 1;

    private Queue queue;
    private HashMap<String, CompletableFuture<Confirmation>> promises;
    private Communicator communicator;
    private ArrayList<Feature> featureList;

    public Client(Queue queue, Communicator communicator) {
        this.promises = new HashMap<>();
        this.featureList = new ArrayList<>();

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
        communicator.connect(uri, new CommunicatorEvents() {
            @Override
            public void onCallResult(String id, String payload) {
                Confirmation conf = communicator.unpackPayload(payload, getConfirmationType(id));
                promises.get(id).complete(conf);
            }

            @Override
            public void onCall(String id, String action, String payload) {

            }

            @Override
            public void onError(String id, String payload) { }

            @Override
            public void onDisconnected() { }

            @Override
            public void onConnected() { }
        });
    }

    public void disconnect()
    {
        try {
            communicator.disconnect();
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

        communicator.sendCall(id, feature.getAction(), request);
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
}
