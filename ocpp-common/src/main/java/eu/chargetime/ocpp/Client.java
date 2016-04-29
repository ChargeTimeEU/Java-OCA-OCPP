package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Thomas Volden on 20-Apr-16.
 */
public class Client
{
    private final int INDEX_UNIQUEID = 1;

    private HashMap<String, CompletableFuture<Confirmation>> promises;
    private ArrayList<Feature> featureList;
    private Session session;

    public Client(Session session) {
        this.promises = new HashMap<>();
        this.featureList = new ArrayList<>();
        this.session = session;
    }

    public void addFeatureProfile(Profile profile) {
        Feature[] features = profile.getFeatureList();
        for (Feature feature: features)
            featureList.add(feature);
    }

    public void connect(String uri)
    {
        session.open(uri, new SessionEvents() {
            @Override
            public Feature findFeatureByAction(String action) {
                return findFeature(action);
            }

            @Override
            public Feature findFeatureByRequest(Request request) {
                return findFeature(request);
            }

            @Override
            public Feature findFeatureByConfirmation(Confirmation confirmation) {
                return findFeature(confirmation);
            }

            @Override
            public void handleConfirmation(String uniqueId, Confirmation confirmation) {
                promises.get(uniqueId).complete(confirmation);
            }

            @Override
            public Confirmation handleRequest(Request request) {
                return null;
            }
        });
    }

    public void disconnect()
    {
        try {
            //session.disconnect();
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
    }

    private Feature findFeature(Object needle) {
        Feature output = null;

        for(Feature feature: featureList) {
            if (featureContains(feature, needle)) {
                output = feature;
                break;
            }
        }

        return output;
    }

    private boolean featureContains(Feature feature, Object object) {
        boolean contains = false;
        contains |= object instanceof String && feature.getAction().equals(object);
        contains |= object instanceof Request && feature.getRequestType() == object.getClass();
        contains |= object instanceof Confirmation && feature.getConfirmationType() == object.getClass();
        return contains;
    }

    public CompletableFuture<Confirmation> send(Request request) {
        Feature feature = findFeature(request);
        String id = session.sendRequest(feature.getAction(), request);
        CompletableFuture<Confirmation> promise = createPromise(id);
        return promise;
    }

    private CompletableFuture<Confirmation> createPromise(String uniqueId) {
        CompletableFuture<Confirmation> promise = new CompletableFuture<>();
        promises.put(uniqueId, promise);
        return promise;
    }

}
