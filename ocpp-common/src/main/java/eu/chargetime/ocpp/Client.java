package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
/*
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

/**
 * Abstract class.
 * Handles basic client logic:
 * Hold s list of supported features.
 * Keeps track of outgoing request.
 * Calls back when a confirmation is received.
 * <p>
 * Must be overloaded in order to support specific protocols and formats.
 */
public abstract class Client
{
    private HashMap<String, CompletableFuture<Confirmation>> promises;
    private ArrayList<Feature> featureList;
    private Session session;

    /**
     * Handle required injections.
     *
     * @param   session     Inject session object
     * @see                 Session
     */
    public Client(Session session) {
        this.promises = new HashMap<>();
        this.featureList = new ArrayList<>();
        this.session = session;
    }

    /**
     * Add {@link Profile} to support a group of features.
     *
     * @param   profile     supported feature {@link Profile}
     * @see                 Profile
     */
    public void addFeatureProfile(Profile profile) {
        Feature[] features = profile.getFeatureList();
        for (Feature feature: features)
            featureList.add(feature);
    }

    /**
     * Connect to server
     *
     * @param   uri     url and port of the server
     */
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

            @Override
            public void handleError(String uniqueId) {

            }

            @Override
            public void handleConnectionClosed() {

            }

            @Override
            public void handleConnectionOpened() {

            }
        });
    }

    /**
     * Disconnect from server
     */
    public void disconnect()
    {
        try {
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Search for supported features added with the addProfile.
     * If no supported feature is found, null is returned
     *
     * Can take multiple inputs:
     * {@link String}, search for the action name of the feature.
     * {@link Request}/{@link Confirmation}, search for a feature that matches.
     * Anything else will return null.
     *
     * @param   needle  Object supports {@link String}, {@link Request} or {@link Confirmation}
     * @return Instance of the supported Feature
     */
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

    /**
     * Tries to match {@link Feature} with an {@link Object}.
     * Different outcome based on the type:
     * {@link String}, matches the action name of the feature.
     * {@link Request}, matches the request type of the feature.
     * {@link Confirmation}, matches the confirmation type of the feature.
     * Other wise returns false.
     *
     * @param   feature to match
     * @param   object  to match with, supports {@link String}, {@link Request} or {@link Confirmation}
     * @return true if the {@link Feature} matches the {@link Object}
     */
    private boolean featureContains(Feature feature, Object object) {
        boolean contains = false;
        contains |= object instanceof String && feature.getAction().equals(object);
        contains |= object instanceof Request && feature.getRequestType() == object.getClass();
        contains |= object instanceof Confirmation && feature.getConfirmationType() == object.getClass();
        return contains;
    }

    /**
     * Send a {@link Request} to the server.
     * Can only send {@link Request} that the client supports, see {@link #addFeatureProfile(Profile)}
     *
     * @param   request                         outgoing request
     * @return call back object, will be fulfilled with confirmation when received
     * @throws UnsupportedFeatureException     trying to send a request from an unsupported feature
     * @see                                     CompletableFuture
     */
    public CompletableFuture<Confirmation> send(Request request) throws UnsupportedFeatureException {
        Feature feature = findFeature(request);
        if (feature == null)
            throw new UnsupportedFeatureException();

        String id = session.sendRequest(feature.getAction(), request);
        CompletableFuture<Confirmation> promise = createPromise(id);
        return promise;
    }

    /**
     * Creates call back {@link CompletableFuture} for later use
     *
     * @param   uniqueId    identification for the {@link Request}
     * @return call back {@link CompletableFuture}
     */
    private CompletableFuture<Confirmation> createPromise(String uniqueId) {
        CompletableFuture<Confirmation> promise = new CompletableFuture<>();
        promises.put(uniqueId, promise);
        return promise;
    }

}
