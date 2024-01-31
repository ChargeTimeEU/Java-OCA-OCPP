/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
 * Copyright (C) 2023 Robert Schlabbach <robert.schlabbach@ubitricity.com>

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

package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.function.Function;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.wss.BaseWssFactoryBuilder;
import eu.chargetime.ocpp.wss.WssFactoryBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import javax.net.ssl.SSLContext;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.protocols.IProtocol;
import org.java_websocket.protocols.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** multiple protocol versions capable JSON Web Socket implementation of the server. */
public class MultiProtocolJSONServer implements IMultiProtocolServerAPI {

  private static final Logger logger = LoggerFactory.getLogger(MultiProtocolJSONServer.class);

  private final MultiProtocolWebSocketListener listener;
  private final Server server;
  private final MultiProtocolFeatureRepository featureRepository;

  /**
   * The constructor creates WS-ready server.
   *
   * @param protocolVersions list of protocol versions to accept.
   * @param configuration network configuration for a json server.
   */
  public MultiProtocolJSONServer(
      List<ProtocolVersion> protocolVersions, JSONConfiguration configuration) {
    featureRepository = new MultiProtocolFeatureRepository(protocolVersions);
    MultiProtocolSessionFactory sessionFactory = new MultiProtocolSessionFactory(featureRepository);

    List<IProtocol> protocols = new ArrayList<>(protocolVersions.size());
    for (ProtocolVersion protocolVersion : protocolVersions) {
      protocols.add(new Protocol(protocolVersion.getSubProtocolName()));
    }
    Draft draft = new Draft_6455(Collections.emptyList(), protocols);

    if (configuration.getParameter(JSONConfiguration.HTTP_HEALTH_CHECK_ENABLED, true)) {
      logger.info("JSONServer with HttpHealthCheckDraft");
      listener =
          new MultiProtocolWebSocketListener(
              sessionFactory, configuration, draft, new Draft_HttpHealthCheck());
    } else {
      listener = new MultiProtocolWebSocketListener(sessionFactory, configuration, draft);
    }
    server = new Server(listener, new PromiseRepository());
  }

  /**
   * The constructor creates WS-ready server.
   *
   * @param protocolVersions list of protocol versions to accept.
   */
  public MultiProtocolJSONServer(List<ProtocolVersion> protocolVersions) {
    this(protocolVersions, JSONConfiguration.get());
  }

  /**
   * The constructor creates WSS-ready server.
   *
   * @param protocolVersions list of protocol versions to accept.
   * @param wssFactoryBuilder to build {@link org.java_websocket.WebSocketServerFactory} to support
   *     wss://.
   * @param configuration network configuration for a json server.
   */
  public MultiProtocolJSONServer(
      List<ProtocolVersion> protocolVersions,
      WssFactoryBuilder wssFactoryBuilder,
      JSONConfiguration configuration) {
    this(protocolVersions, configuration);
    enableWSS(wssFactoryBuilder);
  }

  /**
   * The constructor creates WSS-ready server.
   *
   * @param protocolVersions list of protocol versions to accept.
   * @param wssFactoryBuilder to build {@link org.java_websocket.WebSocketServerFactory} to support
   *     wss://.
   */
  public MultiProtocolJSONServer(
      List<ProtocolVersion> protocolVersions, WssFactoryBuilder wssFactoryBuilder) {
    this(protocolVersions, wssFactoryBuilder, JSONConfiguration.get());
  }

  // To ensure the exposed API is backward compatible
  public void enableWSS(SSLContext sslContext) throws IOException {
    WssFactoryBuilder builder = BaseWssFactoryBuilder.builder().sslContext(sslContext);
    enableWSS(builder);
  }

  /**
   * Enables server to accept WSS connections. The {@code wssFactoryBuilder} must be initialized at
   * that step (as required parameters set might vary depending on implementation the {@link
   * WssFactoryBuilder#verify()} is used to ensure initialization).
   *
   * @param wssFactoryBuilder builder to provide WebSocketServerFactory
   * @return instance of {@link MultiProtocolJSONServer}
   * @throws IllegalStateException in case if the server is already connected
   * @throws IllegalStateException in case {@code wssFactoryBuilder} not initialized properly
   */
  public MultiProtocolJSONServer enableWSS(WssFactoryBuilder wssFactoryBuilder) {
    wssFactoryBuilder.verify();
    listener.enableWSS(wssFactoryBuilder);
    return this;
  }

  @Override
  public void addFeatureProfile(Profile profile) {
    addFeatureProfile(ProtocolVersion.OCPP1_6, profile);
  }

  @Override
  public void addFeatureProfile(ProtocolVersion protocolVersion, Profile profile) {
    featureRepository.addFeatureProfile(protocolVersion, profile);
  }

  @Override
  public void addFunction(ProtocolVersion protocolVersion, Function function) {
    featureRepository.addFeatureFunction(protocolVersion, function);
  }

  @Override
  public int getPort() {
    return listener.getPort();
  }

  @Override
  public boolean isSessionOpen(UUID session) {
    return server.isSessionOpen(session);
  }

  @Override
  public void closeSession(UUID session) {
    server.closeSession(session);
  }

  @Override
  public void open(String host, int port, ServerEvents serverEvents) {
    logger.info("Feature repository: {}", featureRepository);
    server.open(host, port, serverEvents);
  }

  @Override
  public void close() {
    server.close();
  }

  @Override
  public boolean isClosed() {
    return listener.isClosed();
  }

  @Override
  public CompletionStage<Confirmation> send(UUID session, Request request)
      throws OccurenceConstraintException, UnsupportedFeatureException, NotConnectedException {
    return server.send(session, request);
  }

  @Override
  public boolean asyncCompleteRequest(UUID sessionIndex, String uniqueId, Confirmation confirmation)
      throws NotConnectedException, UnsupportedFeatureException, OccurenceConstraintException {
    return server.asyncCompleteRequest(sessionIndex, uniqueId, confirmation);
  }
}
