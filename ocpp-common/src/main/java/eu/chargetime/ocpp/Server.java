package eu.chargetime.ocpp;
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

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.SessionInformation;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles basic server logic: Holds a list of supported features. Keeps track of outgoing requests.
 * Calls back when a confirmation is received.
 */
public class Server {

  private static final Logger logger = LoggerFactory.getLogger(Server.class);

  public static final int INITIAL_SESSIONS_NUMBER = 1000;

  private final Map<UUID, ISession> sessions;
  private final Listener listener;
  private final IPromiseRepository promiseRepository;

  /**
   * Constructor. Handles the required injections.
   *
   * @param listener injected listener.
   * @param promiseRepository injected promise repository.
   */
  public Server(
      Listener listener,
      IPromiseRepository promiseRepository) {
    this.listener = listener;
    this.promiseRepository = promiseRepository;
    this.sessions = new ConcurrentHashMap<>(INITIAL_SESSIONS_NUMBER);
  }

  /**
   * Start listening for clients.
   *
   * @param hostname Url or IP of the server as String.
   * @param port the port number of the server.
   * @param serverEvents Callback handler for server specific events.
   */
  public void open(String hostname, int port, ServerEvents serverEvents) {

    listener.open(
        hostname,
        port,
        new ListenerEvents() {

          @Override
          public void authenticateSession(
              SessionInformation information, String username, byte[] password)
              throws AuthenticationException {
            serverEvents.authenticateSession(information, username, password);
          }

          @Override
          public void newSession(ISession session, SessionInformation information) {
            session.accept(
                new SessionEvents() {
                  @Override
                  public void handleConfirmation(String uniqueId, Confirmation confirmation) {

                    Optional<CompletableFuture<Confirmation>> promiseOptional =
                        promiseRepository.getPromise(uniqueId);
                    if (promiseOptional.isPresent()) {
                      promiseOptional.get().complete(confirmation);
                      promiseRepository.removePromise(uniqueId);
                    } else {
                      logger.debug("Promise not found for confirmation {}", confirmation);
                    }
                  }

                  @Override
                  public Confirmation handleRequest(Request request)
                      throws UnsupportedFeatureException {
                    Optional<Feature> featureOptional = session.getFeatureRepository().findFeature(request);
                    if (featureOptional.isPresent()) {
                      Optional<UUID> sessionIdOptional = getSessionID(session);
                      if (sessionIdOptional.isPresent()) {
                        return featureOptional
                            .get()
                            .handleRequest(sessionIdOptional.get(), request);
                      } else {
                        logger.error(
                            "Unable to handle request ({}), the active session was not found for {}.",
                            request, session.getSessionId());
                        throw new IllegalStateException("Active session not found");
                      }
                    } else {
                      throw new UnsupportedFeatureException();
                    }
                  }

                  @Override
                  public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) throws UnsupportedFeatureException, OccurenceConstraintException {
                    return session.completePendingPromise(uniqueId, confirmation);
                  }

                  @Override
                  public void handleError(
                      String uniqueId, String errorCode, String errorDescription, Object payload) {
                    Optional<CompletableFuture<Confirmation>> promiseOptional =
                        promiseRepository.getPromise(uniqueId);
                    if (promiseOptional.isPresent()) {
                      promiseOptional
                          .get()
                          .completeExceptionally(
                              new CallErrorException(errorCode, errorDescription, payload));
                      promiseRepository.removePromise(uniqueId);
                    } else {
                      logger.debug("Promise not found for error {}", errorDescription);
                    }
                  }

                  @Override
                  public void handleConnectionClosed() {
                    Optional<UUID> sessionIdOptional = getSessionID(session);
                    if (sessionIdOptional.isPresent()) {
                      serverEvents.lostSession(sessionIdOptional.get());
                      sessions.remove(sessionIdOptional.get());
                    } else {
                      logger.warn("Active session not found for {}", session.getSessionId());
                    }
                  }

                  @Override
                  public void handleConnectionOpened() {}
                });

            sessions.put(session.getSessionId(), session);

            Optional<UUID> sessionIdOptional = getSessionID(session);
            if (sessionIdOptional.isPresent()) {
              serverEvents.newSession(sessionIdOptional.get(), information);
              logger.debug("Session created: {}", session.getSessionId());
            } else {
              throw new IllegalStateException("Failed to create a session");
            }
          }
        });
  }

  private Optional<UUID> getSessionID(ISession session) {
    if (!sessions.containsKey(session.getSessionId())) {
      return Optional.empty();
    }

    return Optional.of(session.getSessionId());
  }

  /** Close all connections and stop listening for clients. */
  public void close() {
    listener.close();
  }

  /**
   * Send a message to a client.
   *
   * @param sessionIndex Session index of the client.
   * @param request Request for the client.
   * @return Callback handler for when the client responds.
   * @throws UnsupportedFeatureException Thrown if the feature isn't among the list of supported
   *     featured.
   * @throws OccurenceConstraintException Thrown if the request isn't valid.
   */
  public CompletableFuture<Confirmation> send(UUID sessionIndex, Request request)
      throws UnsupportedFeatureException, OccurenceConstraintException, NotConnectedException {
    ISession session = sessions.get(sessionIndex);

    if (session == null) {
      logger.warn("Session not found by index: {}", sessionIndex);

      // No session found means client disconnected and request should be cancelled
      throw new NotConnectedException();
    }

    Optional<Feature> featureOptional = session.getFeatureRepository().findFeature(request);
    if (!featureOptional.isPresent()) {
      throw new UnsupportedFeatureException();
    }

    if (!request.validate()) {
      throw new OccurenceConstraintException();
    }

    String id = session.storeRequest(request);
    CompletableFuture<Confirmation> promise = promiseRepository.createPromise(id);
    session.sendRequest(featureOptional.get().getAction(), request, id);
    return promise;
  }

  /**
   * Indicate completion of a pending request.
   *
   * @param sessionIndex Session index of the client.
   * @param uniqueId the unique id used for the original {@link Request}.
   * @param confirmation the {@link Confirmation} to the original {@link Request}.
   * @return a boolean indicating if pending request was found.
   * @throws NotConnectedException Thrown if session with passed sessionIndex is not found
   */
  public boolean asyncCompleteRequest(UUID sessionIndex, String uniqueId, Confirmation confirmation) throws NotConnectedException, UnsupportedFeatureException, OccurenceConstraintException {
    ISession session = sessions.get(sessionIndex);

    if (session == null) {
      logger.warn("Session not found by index: {}", sessionIndex);

      // No session found means client disconnected and request should be cancelled
      throw new NotConnectedException();
    }

    return session.completePendingPromise(uniqueId, confirmation);
  }


  public boolean isSessionOpen(UUID sessionIndex) {
    return sessions.containsKey(sessionIndex);
  }

  /**
   * Close connection to a client
   *
   * @param sessionIndex Session index of the client.
   */
  public void closeSession(UUID sessionIndex) {
    ISession session = sessions.get(sessionIndex);
    if (session != null) {
      session.close();
    }
  }
}
