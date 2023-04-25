package extrawest.ocpp.test.features;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>
   Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>

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

import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.BootNotificationFeature;
import extrawest.ocpp.features.server.handlers.IServerBootNotificationRequestHandler;
import extrawest.ocpp.model.response.BootNotificationResponse;
import extrawest.ocpp.model.dataTypes.enums.BootReasonEnumType;
import extrawest.ocpp.model.dataTypes.enums.RegistrationStatusEnumType;
import extrawest.ocpp.model.request.BootNotificationRequest;
import extrawest.ocpp.model.dataTypes.ChargingStationType;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class BootNotification implements IServerBootNotificationRequestHandler {
  private BootNotificationFeature feature;
  private BootNotificationResponse confirmation;

  public BootNotification() {
    feature = new BootNotificationFeature(this);

    confirmation = new BootNotificationResponse();
    LocalDateTime calendar = LocalDateTime.now(ZoneOffset.UTC);
    confirmation.setCurrentTime(calendar);
    confirmation.setInterval(42);
    confirmation.setStatus(RegistrationStatusEnumType.ACCEPTED);
  }

  @Override
  public BootNotificationResponse handleBootNotificationRequest(
      UUID sessionIndex, BootNotificationRequest request) {
    return confirmation;
  }

  public BootNotificationResponse getConfirmation() {
    return confirmation;
  }

  public BootNotificationRequest createRequest() {
    BootNotificationRequest request = new BootNotificationRequest();
    request.setReason(BootReasonEnumType.UNKNOWN);
    ChargingStationType chargingStationType = new ChargingStationType();
    request.setChargingStation(chargingStationType);
    chargingStationType.setVendorName("ChargeTimeEU");
    chargingStationType.setModel("Test");
    return request;
  }

  public Feature getFeature() {
    return feature;
  }
}
