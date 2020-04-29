package eu.chargetime.ocpp.feature.profile.test;
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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.feature.profile.ServerFirmwareManagementEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerFirmwareManagementProfile;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatus;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatusNotificationRequest;
import eu.chargetime.ocpp.model.firmware.FirmwareStatus;
import eu.chargetime.ocpp.model.firmware.FirmwareStatusNotificationRequest;
import java.util.UUID;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServerFirmwareManagementProfileTest extends ProfileTest {
  ServerFirmwareManagementProfile profile;

  @Mock private ServerFirmwareManagementEventHandler handler;

  @Before
  public void setup() {
    profile = new ServerFirmwareManagementProfile(handler);
  }

  @Test
  public void getFeatureList_containsGetDiagnosticsFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "GetDiagnostics"), Is.is(instanceOf(GetDiagnosticsFeature.class)));
  }

  @Test
  public void getFeatureList_containsDiagnosticsStatusNotificationFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "DiagnosticsStatusNotification"),
        Is.is(instanceOf(DiagnosticsStatusNotificationFeature.class)));
  }

  @Test
  public void getFeatureList_containsFirmwareStatusNotificationFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "FirmwareStatusNotification"),
        Is.is(instanceOf(FirmwareStatusNotificationFeature.class)));
  }

  @Test
  public void getFeatureList_containsUpdateFirmwareFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "UpdateFirmware"), Is.is(instanceOf(UpdateFirmwareFeature.class)));
  }

  @Test
  public void
      handleRequest_aDiagnosticsStatusNotificationRequest_callsHandleDiagnosticsStatusNotificationRequest() {
    // Given
    DiagnosticsStatusNotificationRequest request =
        new DiagnosticsStatusNotificationRequest(DiagnosticsStatus.Idle);
    UUID sessionId = UUID.randomUUID();

    // When
    profile.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1))
        .handleDiagnosticsStatusNotificationRequest(eq(sessionId), eq(request));
  }

  @Test
  public void
      handleRequest_aFirmwareStatusNotificationRequest_callsHandleFirmwareStatusNotificationRequest() {
    // Given
    FirmwareStatusNotificationRequest request =
        new FirmwareStatusNotificationRequest(FirmwareStatus.Downloaded);
    UUID sessionId = UUID.randomUUID();

    // When
    profile.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1)).handleFirmwareStatusNotificationRequest(eq(sessionId), eq(request));
  }
}
