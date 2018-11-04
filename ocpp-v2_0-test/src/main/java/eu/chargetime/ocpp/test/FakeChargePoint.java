package eu.chargetime.ocpp.test;
/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>

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

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.test.features.TestRequest;

import java.util.concurrent.CompletionStage;

public class FakeChargePoint {
    private final String url = "ws://127.0.0.1:8887";
    private IClientAPI client;
    private Request receivedRequest = null;
    private Confirmation receivedConfirmation = null;

    public FakeChargePoint() {
        client = new JSONClient();
    }

    public void connect() {
        client.connect(url, new ClientEvents() {
            @Override
            public void connectionOpened() {

            }

            @Override
            public void connectionClosed() {

            }
        });
    }

    public void addFeature(Feature feature) {
        client.addFeature(feature);
    }

    public void disconnect() {
        client.disconnect();
    }

    public void send(TestRequest request) throws OccurenceConstraintException, UnsupportedFeatureException {
        client.addFeature(request.getFeature());
        CompletionStage<Confirmation> send = client.send(request.getRequest());
        send.whenComplete(request::receiveConfirmation);
    }
}
