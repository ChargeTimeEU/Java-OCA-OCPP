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
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.feature.profile.ClientFirmwareManagementEventHandler;
import eu.chargetime.ocpp.feature.profile.ClientFirmwareManagementProfile;
import eu.chargetime.ocpp.model.firmware.*;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientFirmwareManagementProfileTest extends ProfileTest {
  private static final UUID SESSION_NULL = null;
  ClientFirmwareManagementProfile profile;

  @Mock private ClientFirmwareManagementEventHandler handler;

  @Before
  public void setup() {
    profile = new ClientFirmwareManagementProfile(handler);
  }

  @Test
  public void getFeatureList_containsGetDiagnosticsFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "GetDiagnostics"), is(instanceOf(GetDiagnosticsFeature.class)));
  }

  @Test
  public void getFeatureList_containsDiagnosticsStatusNotificationFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "DiagnosticsStatusNotification"),
        is(instanceOf(DiagnosticsStatusNotificationFeature.class)));
  }

  @Test
  public void getFeatureList_containsFirmwareStatusNotificationFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "FirmwareStatusNotification"),
        is(instanceOf(FirmwareStatusNotificationFeature.class)));
  }

  @Test
  public void getFeatureList_containsUpdateFirmwareFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "UpdateFirmware"), is(instanceOf(UpdateFirmwareFeature.class)));
  }

  @Test
  public void handleRequest_aGetDiagnosticsRequest_callsHandleGetDiagnosticsRequest() {
    // Given
    GetDiagnosticsRequest request = new GetDiagnosticsRequest("location");

    // When
    profile.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleGetDiagnosticsRequest(eq(request));
  }

  @Test
  public void handleRequest_aUpdateFirmwareRequest_callsHandleUpdateFirmwareRequest() {
    // Given
    UpdateFirmwareRequest request = new UpdateFirmwareRequest("location", ZonedDateTime.now());

    // When
    profile.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleUpdateFirmwareRequest(eq(request));
  }

  @Test
  public void
      createDiagnosticsStatusNotificationRequest_withStatus_returnsDiagnosticsStatusNotificationRequestWithStatus() {
    // Given
    DiagnosticsStatus status = DiagnosticsStatus.Idle;

    // When
    DiagnosticsStatusNotificationRequest result =
        profile.createDiagnosticsStatusNotificationRequest(status);

    // Then
    assertThat(result.getStatus(), is(status));
  }

  @Test
  public void
      createFirmwareStatusNotificationRequest_withStatus_returnsFirmwareStatusNotificationRequestWithStatus() {
    // Given
    FirmwareStatus status = FirmwareStatus.Idle;

    // When
    FirmwareStatusNotificationRequest result =
        profile.createFirmwareStatusNotificationRequest(status);

    // Then
    assertThat(result.getStatus(), is(status));
  }
}
