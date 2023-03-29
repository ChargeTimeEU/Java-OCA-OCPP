package eu.chargetime.ocpp.test.features;
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

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.basic.StatusNotificationFeature;
import eu.chargetime.ocpp.features.basic.handlers.server.IServerStatusNotificationRequestHandler;
import eu.chargetime.ocpp.model.response.StatusNotificationResponse;
import eu.chargetime.ocpp.model.request.StatusNotificationRequest;
import eu.chargetime.ocpp.model.dataTypes.enums.ConnectorStatusEnumType;
import java.time.ZonedDateTime;
import java.util.UUID;

public class StatusNotification implements IServerStatusNotificationRequestHandler {

  private StatusNotificationFeature feature;
  private StatusNotificationResponse confirmation;

  public StatusNotification() {
    feature = new StatusNotificationFeature(this);

    confirmation = new StatusNotificationResponse();
  }

  @Override
  public StatusNotificationResponse handleStatusNotificationRequest(
      UUID sessionIndex, StatusNotificationRequest request) {
    return confirmation;
  }

  public StatusNotificationResponse getConfirmation() {
    return confirmation;
  }

  public StatusNotificationRequest createRequest() {
    StatusNotificationRequest request = new StatusNotificationRequest();
    request.setTimestamp(ZonedDateTime.now());
    request.setConnectorStatus(ConnectorStatusEnumType.AVAILABLE);
    request.setEvseId(1);
    request.setConnectorId(1);
    return request;
  }

  public Feature getFeature() {
    return feature;
  }
}
