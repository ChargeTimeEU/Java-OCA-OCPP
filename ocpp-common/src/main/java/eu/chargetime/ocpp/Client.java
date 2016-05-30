package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
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
            public void handleConfirmation(String uniqueId, Confirmation confirmation) {
                promises.get(uniqueId).complete(confirmation);
            }

            @Override
            public Confirmation handleRequest(Request request) {
                Feature feature = findFeatureByRequest(request);
                return feature.handleRequest(request);
            }
        });
    }

    public void disconnect()
    {
        try {
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public CompletableFuture<Confirmation> send(Request request) throws UnsupportedFeatureException {
        Feature feature = findFeature(request);
        if (feature == null)
            throw new UnsupportedFeatureException();

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
