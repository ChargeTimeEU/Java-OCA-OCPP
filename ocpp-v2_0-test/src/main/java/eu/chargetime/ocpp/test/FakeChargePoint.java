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
import java.util.concurrent.CompletionStage;

public class FakeChargePoint {
  private final String url = "ws://127.0.0.1:8887";
  private IClientAPI client;
  private Confirmation receivedConfirmation = null;
  private Request handletReuqest;

  public FakeChargePoint() {
    client = new JSONClient();
  }

  public void connect() {
    client.connect(
        url,
        new ClientEvents() {
          @Override
          public void connectionOpened() {}

          @Override
          public void connectionClosed() {}
        });
  }

  public void addFeature(Feature feature) {
    FeatureTestDecorator monitoredFeature =
        new FeatureTestDecorator(feature, request -> handletReuqest = request);
    client.addFeature(monitoredFeature);
  }

  public void disconnect() {
    client.disconnect();
  }

  public void send(Request request)
      throws OccurenceConstraintException, UnsupportedFeatureException {
    CompletionStage<Confirmation> send = client.send(request);
    send.whenComplete((confirmation, throwable) -> receivedConfirmation = confirmation);
  }

  public boolean recieved(Confirmation confirmation) {
    if (receivedConfirmation != null && confirmation != null)
      return receivedConfirmation.equals(confirmation);
    return false;
  }

  public boolean hasHandled(Request request) {
    if (handletReuqest != null && request != null) return handletReuqest.equals(request);
    return false;
  }
}
