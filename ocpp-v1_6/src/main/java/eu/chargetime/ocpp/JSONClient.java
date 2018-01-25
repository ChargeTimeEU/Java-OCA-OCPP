package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

import javax.net.ssl.SSLContext;
import java.io.IOException;
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

    private final WebSocketTransmitter transmitter;
    private final FeatureRepository featureRepository;
    private final Client client;


    /**
     * Application composite root for a json client.
     * The core feature profile is required as a minimum.
     *
     * @param coreProfile   implementation of the core feature profile.
     */
    public JSONClient(ClientCoreProfile coreProfile) {
        transmitter = new WebSocketTransmitter(new OcppDraft());
        JSONCommunicator communicator = new JSONCommunicator(transmitter);
        AsyncPromiseFulfilerDecorator promiseFulfiler = new AsyncPromiseFulfilerDecorator(new SimplePromiseFulfiller());
        featureRepository = new FeatureRepository();
        Session session = new Session(communicator, new Queue(), promiseFulfiler, featureRepository);
        client = new Client(session, featureRepository, new PromiseRepository());
        featureRepository.addFeatureProfile(coreProfile);
    }

    public void enableWSS(SSLContext sslContext) throws IOException {
        transmitter.enableWSS(sslContext);
    }

    @Override
    public void addFeatureProfile(Profile profile) {
        featureRepository.addFeatureProfile(profile);
    }

    @Override
    public void connect(String url, ClientEvents clientEvents) {
        client.connect(url, clientEvents);
    }

    @Override
    public CompletionStage<Confirmation> send(Request request) throws OccurenceConstraintException, UnsupportedFeatureException {
        return client.send(request);
    }

    @Override
    public void disconnect() {
        client.disconnect();
    }
}
