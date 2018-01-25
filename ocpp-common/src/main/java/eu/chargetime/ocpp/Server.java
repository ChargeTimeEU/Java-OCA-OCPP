package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>

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
public class Server {

    private HashMap<UUID, ISession> sessions;
    private Listener listener;
    private final IFeatureRepository featureRepository;
    private final IPromiseRepository promiseRepository;

    /**
     * Constructor. Handles the required injections.
     *
     * @param listener injected listener.
     */
    public Server(Listener listener, IFeatureRepository featureRepository, IPromiseRepository promiseRepository) {
        this.listener = listener;
        this.featureRepository = featureRepository;
        this.promiseRepository = promiseRepository;
        this.sessions = new HashMap();
    }

    /**
     * Start listening for clients.
     * @param hostname Url or IP of the server as String.
     * @param port the port number of the server.
     * @param serverEvents Callback handler for server specific events.
     */
    public void open(String hostname, int port, ServerEvents serverEvents) {

        listener.open(hostname, port, (session, information) -> {
            session.accept(new SessionEvents() {
                @Override
                public void handleConfirmation(String uniqueId, Confirmation confirmation) {
                    promiseRepository.getPromise(uniqueId).complete(confirmation);
                    promiseRepository.removePromise(uniqueId);
                }

                @Override
                public Confirmation handleRequest(Request request) {
                    Feature feature = featureRepository.findFeature(request);
                    return feature.handleRequest(getSessionID(session), request);
                }

                @Override
                public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {
                    promiseRepository.getPromise(uniqueId).completeExceptionally(new CallErrorException(errorCode, errorCode, payload));
                    promiseRepository.removePromise(uniqueId);
                }

                @Override
                public void handleConnectionClosed() {
                    serverEvents.lostSession(getSessionID(session));
                    sessions.remove(session);
                }

                @Override
                public void handleConnectionOpened() {

                }
            });
            sessions.put(UUID.randomUUID(), session);
            serverEvents.newSession(getSessionID(session), information);
        });
    }

    private UUID getSessionID(ISession session) {

        if (!sessions.containsValue(session))
            return null;

        for (Map.Entry<UUID, ISession> entry : sessions.entrySet()) {
            if (entry.getValue() == session)
                return entry.getKey();
        }

        return null;
    }

    /**
     * Close all connections and stop listening for clients.
     */
    public void close() {
        listener.close();
    }

    /**
     * Send a message to a client.
     *
     * @param sessionIndex Session index of the client.
     * @param request      Request for the client.
     * @return Callback handler for when the client responds.
     * @throws UnsupportedFeatureException Thrown if the feature isn't among the list of supported featured.
     * @throws OccurenceConstraintException Thrown if the request isn't valid.
     */
    public CompletableFuture<Confirmation> send(UUID sessionIndex, Request request) throws UnsupportedFeatureException, OccurenceConstraintException {
        Feature feature = featureRepository.findFeature(request);
        if (feature == null)
            throw new UnsupportedFeatureException();

        if (!request.validate())
            throw new OccurenceConstraintException();

        ISession session = sessions.get(sessionIndex);
        String id = session.storeRequest(request);
        CompletableFuture<Confirmation> promise = promiseRepository.createPromise(id);
        session.sendRequest(feature.getAction(), request, id);
        return promise;
    }

    /**
     * Close connection to a client
     *
     * @param sessionIndex Session index of the client.
     */
    public void closeSession(UUID sessionIndex) {
        ISession session = sessions.get(sessionIndex);
        if (session != null)
            session.close();
    }

    public void setAsyncRequestHandler(boolean asyncRequestHandler) {
        listener.setAsyncRequestHandler(asyncRequestHandler);
    }
}
