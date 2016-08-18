package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>

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
 * Handles basic server logic:
 * Holds a list of supported features.
 * Keeps track of outgoing requests.
 * Calls back when a confirmation is received.
 */
public abstract class Server extends FeatureHandler {

    private ArrayList<Session> sessions;

    /**
     *
     */
    public Server() {
        this.sessions = new ArrayList<>();
    }

    /**
     * Start listening for clients.
     *
     * @param listener     Inject the listener.
     * @param serverEvents Callback handler for server specific events.
     */
    public void open(Listener listener, ServerEvents serverEvents) {
        listener.open(session -> {
            session.accept(new SessionEvents() {
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
                    getPromise(uniqueId).complete(confirmation);
                }

                @Override
                public Confirmation handleRequest(Request request) {
                    Feature feature = findFeatureByRequest(request);
                    return feature.handleRequest(sessions.indexOf(session), request);
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
            sessions.add(session);
            serverEvents.newSession(sessions.indexOf(session));
        });
    }

    /**
     * Send a message to a client.
     *
     * @param sessionIndex Session index of the client.
     * @param request      Request for the client.
     * @return Callback handler for when the client responds.
     * @throws UnsupportedFeatureException Thrown if the feature isn't amoung the list of supported featured.
     */
    public CompletableFuture<Confirmation> send(int sessionIndex, Request request) throws UnsupportedFeatureException {
        Feature feature = findFeature(request);
        if (feature == null)
            throw new UnsupportedFeatureException();

        String id = sessions.get(sessionIndex).sendRequest(feature.getAction(), request);
        CompletableFuture<Confirmation> promise = createPromise(id);
        return promise;
    }
}
