package eu.chargetime.ocpp.feature.profile.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import eu.chargetime.ocpp.feature.AuthorizeFeature;
import eu.chargetime.ocpp.feature.BootNotificationFeature;
import eu.chargetime.ocpp.feature.ChangeAvailabilityFeature;
import eu.chargetime.ocpp.feature.ChangeConfigurationFeature;
import eu.chargetime.ocpp.feature.ClearCacheFeature;
import eu.chargetime.ocpp.feature.DataTransferFeature;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.GetConfigurationFeature;
import eu.chargetime.ocpp.feature.HeartbeatFeature;
import eu.chargetime.ocpp.feature.MeterValuesFeature;
import eu.chargetime.ocpp.feature.RemoteStartTransactionFeature;
import eu.chargetime.ocpp.feature.RemoteStopTransactionFeature;
import eu.chargetime.ocpp.feature.ResetFeature;
import eu.chargetime.ocpp.feature.StartTransactionFeature;
import eu.chargetime.ocpp.feature.StatusNotificationFeature;
import eu.chargetime.ocpp.feature.StopTransactionFeature;
import eu.chargetime.ocpp.feature.UnlockConnectorFeature;
import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import eu.chargetime.ocpp.model.core.AuthorizeRequest;
import eu.chargetime.ocpp.model.core.AvailabilityType;
import eu.chargetime.ocpp.model.core.BootNotificationRequest;
import eu.chargetime.ocpp.model.core.ChangeAvailabilityRequest;
import eu.chargetime.ocpp.model.core.ChangeConfigurationRequest;
import eu.chargetime.ocpp.model.core.ClearCacheRequest;
import eu.chargetime.ocpp.model.core.DataTransferRequest;
import eu.chargetime.ocpp.model.core.GetConfigurationRequest;
import eu.chargetime.ocpp.model.core.HeartbeatRequest;
import eu.chargetime.ocpp.model.core.MeterValuesRequest;
import eu.chargetime.ocpp.model.core.RemoteStartTransactionRequest;
import eu.chargetime.ocpp.model.core.RemoteStopTransactionRequest;
import eu.chargetime.ocpp.model.core.ResetRequest;
import eu.chargetime.ocpp.model.core.ResetType;
import eu.chargetime.ocpp.model.core.StartTransactionRequest;
import eu.chargetime.ocpp.model.core.StatusNotificationRequest;
import eu.chargetime.ocpp.model.core.StopTransactionRequest;
import eu.chargetime.ocpp.model.core.UnlockConnectorRequest;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>

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

@RunWith(MockitoJUnitRunner.class)
public class ServerCoreProfileTest extends ProfileTest {

  private ServerCoreProfile core;

  @Mock private ServerCoreEventHandler handler;

  @Before
  public void setup() {
    core = new ServerCoreProfile(handler);
  }

  @Test
  public void getFeatureList_containsAuthorizeFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "Authorize"), is(instanceOf(AuthorizeFeature.class)));
  }

  @Test
  public void handleRequest_anAuthorizeRequest_callsHandleAuthorizeRequest() {
    // Given
    AuthorizeRequest request = new AuthorizeRequest();
    UUID sessionId = UUID.randomUUID();

    // When
    core.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1)).handleAuthorizeRequest(eq(sessionId), eq(request));
  }

  @Test
  public void getFeatureList_containsBootNotificatonFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "BootNotification"), is(instanceOf(BootNotificationFeature.class)));
  }

  @Test
  public void handleRequest_aBootNotificationRequest_callsHandleBootNotificationRequest() {
    // Given
    BootNotificationRequest request = new BootNotificationRequest();
    UUID sessionId = UUID.randomUUID();

    // When
    core.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1)).handleBootNotificationRequest(eq(sessionId), eq(request));
  }

  @Test
  public void getFeatureList_containsChangeAvailabilityFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "ChangeAvailability"),
        is(instanceOf(ChangeAvailabilityFeature.class)));
  }

  @Test
  public void
      createChangeAvailabilityRequest_withTypeAndConnectorId_returnsValidChangeAvailabilityRequest() {
    // Given
    AvailabilityType type = AvailabilityType.Operative;
    int connectorId = 1;

    // When
    ChangeAvailabilityRequest result = core.createChangeAvailabilityRequest(type, connectorId);

    // Then
    assertThat(result.validate(), is(true));
  }

  @Test
  public void getFeatureList_containsChangeConfigurationFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "ChangeConfiguration"),
        is(instanceOf(ChangeConfigurationFeature.class)));
  }

  @Test
  public void
      createChangeConfigurationRequest_withKeyAndValue_returnsValidChangeConfigurationRequest() {
    // Given
    String key = "some key";
    String value = "some value";

    // When
    ChangeConfigurationRequest result = core.createChangeConfigurationRequest(key, value);

    // Then
    assertThat(result.validate(), is(true));
  }

  @Test
  public void getFeatureList_containsClearCacheFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "ClearCache"), is(instanceOf(ClearCacheFeature.class)));
  }

  @Test
  public void createClearCacheRequest_returnsValidClearCacheRequest() {
    // When
    ClearCacheRequest result = core.createClearCacheRequest();

    // Then
    assertThat(result.validate(), is(true));
  }

  @Test
  public void getFeatureList_containsDataTransferFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "DataTransfer"), is(instanceOf(DataTransferFeature.class)));
  }

  @Test
  public void createChangeConfigurationRequest_withVendorId_returnsValidDataTransferRequest() {
    // Given
    String vendorId = "some vendorId";

    // When
    DataTransferRequest result = core.createDataTransferRequest(vendorId);

    // Then
    assertThat(result.validate(), is(true));
  }

  @Test
  public void handleRequest_aDataTransferRequest_callsHandleDataTransferRequest() {
    // Given
    DataTransferRequest request = new DataTransferRequest();
    UUID sessionId = UUID.randomUUID();

    // When
    core.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1)).handleDataTransferRequest(eq(sessionId), eq(request));
  }

  @Test
  public void getFeatureList_containsGetConfigurationFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "GetConfiguration"), is(instanceOf(GetConfigurationFeature.class)));
  }

  @Test
  public void createGetConfigurationRequest_returnsValidGetConfigurationRequest() {

    // When
    GetConfigurationRequest result = core.createGetConfigurationRequest();

    // Then
    assertThat(result.validate(), is(true));
  }

  @Test
  public void getFeatureList_containsHeartbeatFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "Heartbeat"), is(instanceOf(HeartbeatFeature.class)));
  }

  @Test
  public void handleRequest_aHeartbeatRequest_callsHandleHeartbeatRequest() {
    // Given
    HeartbeatRequest request = new HeartbeatRequest();
    UUID sessionId = UUID.randomUUID();

    // When
    core.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1)).handleHeartbeatRequest(eq(sessionId), eq(request));
  }

  @Test
  public void getFeatureList_containsMeterValuesFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "MeterValues"), is(instanceOf(MeterValuesFeature.class)));
  }

  @Test
  public void handleRequest_aMeterValuesRequest_callsHandleMeterValuesRequest() {
    // Given
    MeterValuesRequest request = new MeterValuesRequest();
    UUID sessionId = UUID.randomUUID();

    // When
    core.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1)).handleMeterValuesRequest(eq(sessionId), eq(request));
  }

  @Test
  public void getFeatureList_containsRemoteStartTransactionFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "RemoteStartTransaction"),
        is(instanceOf(RemoteStartTransactionFeature.class)));
  }

  @Test
  public void
      createRemoteStartTransactionRequest_withIdToken_returnsValidRemoteStartTransactionRequest() {
    // Given
    String idToken = "Some IdToken";

    // When
    RemoteStartTransactionRequest result = core.createRemoteStartTransactionRequest(idToken);

    // Then
    assertThat(result.validate(), is(true));
  }

  @Test
  public void getFeatureList_containsRemoteStopTransactionFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "RemoteStopTransaction"),
        is(instanceOf(RemoteStopTransactionFeature.class)));
  }

  @Test
  public void
      createRemoteStopTransactionRequest_withTransactionId_returnsValidRemoteStopTransactionRequest() {
    // Given
    Integer transactionId = 42;

    // When
    RemoteStopTransactionRequest result = core.createRemoteStopTransactionRequest(transactionId);

    // Then
    assertThat(result.validate(), is(true));
  }

  @Test
  public void getFeatureList_containsResetFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "Reset"), is(instanceOf(ResetFeature.class)));
  }

  @Test
  public void createResetRequest_withType_returnsValidResetRequest() {
    // Given
    ResetType type = ResetType.Hard;

    // When
    ResetRequest result = core.createResetRequest(type);

    // Then
    assertThat(result.validate(), is(true));
  }

  @Test
  public void getFeatureList_containsStartTransactionFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "StartTransaction"), is(instanceOf(StartTransactionFeature.class)));
  }

  @Test
  public void handleRequest_aStartTransactionRequest_callsHandleStartTransactionRequest() {
    // Given
    StartTransactionRequest request = new StartTransactionRequest();
    UUID sessionId = UUID.randomUUID();

    // When
    core.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1)).handleStartTransactionRequest(eq(sessionId), eq(request));
  }

  @Test
  public void getFeatureList_containsStatusNotificationFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "StatusNotification"),
        is(instanceOf(StatusNotificationFeature.class)));
  }

  @Test
  public void handleRequest_aStatusNotificationRequest_callsHandleStatusNotificationRequest() {
    // Given
    StatusNotificationRequest request = new StatusNotificationRequest();
    UUID sessionId = UUID.randomUUID();

    // When
    core.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1)).handleStatusNotificationRequest(eq(sessionId), eq(request));
  }

  @Test
  public void getFeatureList_containsStopTransactionFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "StopTransaction"), is(instanceOf(StopTransactionFeature.class)));
  }

  @Test
  public void handleRequest_aStopTransactionRequest_callsHandleStopTransactionRequest() {
    // Given
    StopTransactionRequest request = new StopTransactionRequest();
    UUID sessionId = UUID.randomUUID();

    // When
    core.handleRequest(sessionId, request);

    // Then
    verify(handler, times(1)).handleStopTransactionRequest(eq(sessionId), eq(request));
  }

  @Test
  public void getFeatureList_containsUnlockConnectorFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "UnlockConnector"), is(instanceOf(UnlockConnectorFeature.class)));
  }

  @Test
  public void createUnlockConnectorRequest_withConnectorId_returnsValidUnlockConnectorRequest() {
    // Given
    int connectorId = 42;

    // When
    UnlockConnectorRequest result = core.createUnlockConnectorRequest(connectorId);

    // Then
    assertThat(result.validate(), is(true));
  }
}
