package eu.chargetime.ocpp.features;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2021 John Michael Luy <johnmichael.luy@gmail.com>

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

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerStatusNotificationRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.StatusNotificationRequest;
import eu.chargetime.ocpp.model.response.StatusNotificationResponse;

import java.util.UUID;

public class StatusNotificationFeature implements Feature {

  private final IServerStatusNotificationRequestHandler handler;

  public StatusNotificationFeature(IServerStatusNotificationRequestHandler handler) {
    this.handler = handler;
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    return handler.handleStatusNotificationRequest(
        sessionIndex, (StatusNotificationRequest) request);
  }

  @Override
  public Class<? extends Request> getRequestType() {
    return StatusNotificationRequest.class;
  }

  @Override
  public Class<? extends Confirmation> getConfirmationType() {
    return StatusNotificationResponse.class;
  }

  @Override
  public String getAction() {
    return FeatureConstants.STATUS_NOTIFICATION.value();
  }
}
