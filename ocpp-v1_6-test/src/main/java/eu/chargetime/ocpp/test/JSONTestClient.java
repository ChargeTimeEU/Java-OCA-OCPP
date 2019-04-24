package eu.chargetime.ocpp.test;
/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import java.util.Collections;
import java.util.concurrent.CompletionStage;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.protocols.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** OCA OCPP version 1.6 JSON Web Socket implementation of the client. */
public class JSONTestClient implements IClientAPI {

  private static final Logger logger = LoggerFactory.getLogger(JSONTestClient.class);
  private static final String identity = "testdummy";

  public final Draft draftOcppOnly;
  private final WebSocketTransmitter transmitter;
  private final FeatureRepository featureRepository;
  private final Client client;

  public JSONTestClient(ClientCoreProfile coreProfile) {
    draftOcppOnly =
        new Draft_6455(Collections.emptyList(), Collections.singletonList(new Protocol("ocpp1.6")));
    transmitter = new WebSocketTransmitter(JSONConfiguration.get(), draftOcppOnly);
    JSONCommunicator communicator = new JSONCommunicator(transmitter);
    featureRepository = new FeatureRepository();
    ISession session = new TestSessionFactory(featureRepository).createSession(communicator);
    client = new Client(session, featureRepository, new PromiseRepository());
    featureRepository.addFeatureProfile(coreProfile);
  }

  @Override
  public void addFeatureProfile(Profile profile) {
    featureRepository.addFeatureProfile(profile);
  }

  @Override
  public void connect(String url, ClientEvents clientEvents) {
    logger.debug("Feature repository: {}", featureRepository);

    String identityUrl = String.format("%s/%s", url, identity);
    client.connect(identityUrl, clientEvents);
  }

  @Override
  public CompletionStage<Confirmation> send(Request request)
      throws OccurenceConstraintException, UnsupportedFeatureException {
    return client.send(request);
  }

  @Override
  public void disconnect() {
    client.disconnect();
  }

  @Override
  public boolean isClosed() {
    return transmitter.isClosed();
  }
}
