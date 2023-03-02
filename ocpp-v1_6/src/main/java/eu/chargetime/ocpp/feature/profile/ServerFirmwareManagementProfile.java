package eu.chargetime.ocpp.feature.profile;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
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

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatusNotificationRequest;
import eu.chargetime.ocpp.model.firmware.FirmwareStatusNotificationRequest;
import eu.chargetime.ocpp.model.firmware.GetDiagnosticsRequest;
import eu.chargetime.ocpp.model.firmware.UpdateFirmwareRequest;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.UUID;

public class ServerFirmwareManagementProfile implements Profile {

  private final ServerFirmwareManagementEventHandler eventHandler;
  private HashSet<Feature> features;

  public ServerFirmwareManagementProfile(ServerFirmwareManagementEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new HashSet<>();
    features.add(new GetDiagnosticsFeature(null));
    features.add(new DiagnosticsStatusNotificationFeature(this));
    features.add(new FirmwareStatusNotificationFeature(this));
    features.add(new UpdateFirmwareFeature(null));
  }

  @Override
  public ProfileFeature[] getFeatureList() {
    return features.toArray(new ProfileFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    Confirmation result = null;

    if (request instanceof DiagnosticsStatusNotificationRequest) {
      result =
          eventHandler.handleDiagnosticsStatusNotificationRequest(
              sessionIndex, (DiagnosticsStatusNotificationRequest) request);
    } else if (request instanceof FirmwareStatusNotificationRequest) {
      result =
          eventHandler.handleFirmwareStatusNotificationRequest(
              sessionIndex, (FirmwareStatusNotificationRequest) request);
    }

    return result;
  }

  /**
   * Create a client {@link GetDiagnosticsRequest} with required values.
   *
   * @param location String, the destination folder
   * @return an instance of {@link GetDiagnosticsRequest}
   * @see GetDiagnosticsRequest
   * @see GetDiagnosticsFeature
   */
  public GetDiagnosticsRequest createGetDiagnosticsRequest(String location) {
    return new GetDiagnosticsRequest(location);
  }

  /**
   * Create a client {@link UpdateFirmwareRequest} with required values.
   *
   * @param location String, a URI with the firmware * @param retrieveDate ZonedDateTime, date and
   *     time of retrieving
   * @return an instance of {@link UpdateFirmwareRequest}
   * @see UpdateFirmwareRequest
   * @see UpdateFirmwareFeature
   */
  public UpdateFirmwareRequest createUpdateFirmwareRequest(
      String location, ZonedDateTime retrieveDate) {
    return new UpdateFirmwareRequest(location, retrieveDate);
  }
}
