package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.wss.WssSocketBuilder;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.protocols.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CompletionStage;

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

/**
 * OCA OCPP version 1.6 JSON Web Socket implementation of the client.
 */
public class JSONClient implements IClientAPI {

    private static final Logger logger = LoggerFactory.getLogger(JSONClient.class);

    public final Draft draftOcppOnly =
            new Draft_6455(Collections.emptyList(),
                    Collections.singletonList(new Protocol("ocpp1.6")));
    private final WebSocketTransmitter transmitter;
    private final FeatureRepository featureRepository;
    private final Client client;
    private final String identity;

    public JSONClient(ClientCoreProfile coreProfile) {
        this(coreProfile, null);
    }

    /**
     * Application composite root for a json client.
     * The core feature profile is required as a minimum.
     *
     * @param coreProfile   implementation of the core feature profile.
     * @param identity      if set, will append identity to url.
     */
    public JSONClient(ClientCoreProfile coreProfile, String identity) {
        this.identity = identity;
        transmitter = new WebSocketTransmitter(draftOcppOnly);
        JSONCommunicator communicator = new JSONCommunicator(transmitter);
        AsyncPromiseFulfilerDecorator promiseFulfiler = new AsyncPromiseFulfilerDecorator(new SimplePromiseFulfiller());
        featureRepository = new FeatureRepository();
        Session session = new Session(communicator, new Queue(), promiseFulfiler, featureRepository);
        client = new Client(session, featureRepository, new PromiseRepository());
        featureRepository.addFeatureProfile(coreProfile);
    }

    public JSONClient enableWSS(WssSocketBuilder wssSocketBuilder) throws IOException {
        transmitter.enableWSS(wssSocketBuilder);
        return this;
    }

    public void setPingInterval(int interval) {
        // Set ping interval in seconds
        transmitter.setPingInterval(interval);
    }

    @Override
    public void addFeatureProfile(Profile profile) {
        featureRepository.addFeatureProfile(profile);
    }

    @Override
    public void connect(String url, ClientEvents clientEvents) {
        logger.debug("Feature repository: {}", featureRepository);

        String identityUrl = (identity != null) ? String.format("%s/%s", url, identity) : url;
        client.connect(identityUrl, clientEvents);
    }

    @Override
    public CompletionStage<Confirmation> send(Request request) throws OccurenceConstraintException, UnsupportedFeatureException {
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
