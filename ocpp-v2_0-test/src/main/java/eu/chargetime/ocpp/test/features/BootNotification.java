package eu.chargetime.ocpp.test.features;
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

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.basic.BootNotificationFeature;
import eu.chargetime.ocpp.features.basic.handlers.IServerBootNotificationRequestHandler;
import eu.chargetime.ocpp.model.basic.BootNotificationConfirmation;
import eu.chargetime.ocpp.model.basic.BootNotificationRequest;
import eu.chargetime.ocpp.model.basic.types.BootReasonEnumType;
import eu.chargetime.ocpp.model.basic.types.ChargingStationType;
import eu.chargetime.ocpp.model.basic.types.RegistrationStatusEnumType;
import java.util.Calendar;
import java.util.UUID;

public class BootNotification implements IServerBootNotificationRequestHandler {
  private BootNotificationFeature feature;
  private BootNotificationConfirmation confirmation;

  public BootNotification() {
    feature = new BootNotificationFeature(this);

    confirmation = new BootNotificationConfirmation();
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MILLISECOND, 0); // Make it testable
    confirmation.setCurrentTime(calendar);
    confirmation.setInterval(42);
    confirmation.setStatus(RegistrationStatusEnumType.Accepted);
  }

  @Override
  public BootNotificationConfirmation handleBootNotificationRequest(
      UUID sessionIndex, BootNotificationRequest request) {
    return confirmation;
  }

  public BootNotificationConfirmation getConfirmation() {
    return confirmation;
  }

  public BootNotificationRequest createRequest() {
    BootNotificationRequest request = new BootNotificationRequest();
    request.setReason(BootReasonEnumType.Unknown);
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
