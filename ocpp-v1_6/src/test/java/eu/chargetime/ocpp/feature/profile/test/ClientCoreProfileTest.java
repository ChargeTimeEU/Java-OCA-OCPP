package eu.chargetime.ocpp.feature.profile.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.feature.profile.ClientCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.*;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/*
ChargeTime.eu - Java-OCA-OCPP
Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>
Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>

MIT License

Copyright (C) 2016-2018 Thomas Volden
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
@RunWith(MockitoJUnitRunner.class)
public class ClientCoreProfileTest extends ProfileTest {
  private static final UUID SESSION_NULL = null;

  private ClientCoreProfile core;

  @Mock private ClientCoreEventHandler handler;

  @Before
  public void setup() {
    core = new ClientCoreProfile(handler);
  }

  @Test
  public void createAuthorizeRequest_withIdToken_returnsAuthorizeRequestWithIdTag() {
    // Given
    String legalIdTag = "test123";

    // When
    AuthorizeRequest result = core.createAuthorizeRequest(legalIdTag);

    // Then
    assertThat(result.getIdTag(), is(legalIdTag));
  }

  @Test
  public void
      createBootNotificationRequest_withVendorAndModel_returnsBootNotificationRequestWithVendorAndModel() {
    // Given
    String legalVendor = "vendor";
    String legalModel = "model";

    // When
    BootNotificationRequest result = core.createBootNotificationRequest(legalVendor, legalModel);

    // Then
    assertThat(result.getChargePointVendor(), is(legalVendor));
    assertThat(result.getChargePointModel(), is(legalModel));
  }

  @Test
  public void createHeartbeatRequest_returnsHeartbeatRequest() {
    // When
    Request result = core.createHeartbeatRequest();

    // Then
    assertThat(result, instanceOf(HeartbeatRequest.class));
  }

  @Test
  public void createMeterValuesRequest_returnsMeterValuesRequest() {
    // When
    Request result = core.createMeterValuesRequest(42, ZonedDateTime.now(), "42");

    // Then
    assertThat(result, instanceOf(MeterValuesRequest.class));
  }

  @Test
  public void createStartTransactionRequest_returnsStartTransactionRequest() {
    // When
    Request result = core.createStartTransactionRequest(42, "some token", 42, ZonedDateTime.now());

    // Then
    assertThat(result, instanceOf(StartTransactionRequest.class));
  }

  @Test
  public void createStatusNotificationRequest_returnsStatusNotificationRequest() {
    // When
    Request result =
        core.createStatusNotificationRequest(
            42, ChargePointErrorCode.NoError, ChargePointStatus.Available);

    // Then
    assertThat(result, instanceOf(StatusNotificationRequest.class));
  }

  @Test
  public void createStopTransactionRequest_returnsStopTransactionRequest() {
    // When
    Request result = core.createStopTransactionRequest(42, ZonedDateTime.now(), 42);

    // Then
    assertThat(result, instanceOf(StopTransactionRequest.class));
  }

  @Test
  public void getFeatureList_containsAuthorizeFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // then
    assertThat(findFeature(features, "Authorize"), is(instanceOf(AuthorizeFeature.class)));
  }

  @Test
  public void getFeatureList_containsBootNotificationFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // then
    assertThat(
        findFeature(features, "BootNotification"), is(instanceOf(BootNotificationFeature.class)));
  }

  @Test
  public void getFeatureList_containsChangeAvailabilityFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // then
    assertThat(
        findFeature(features, "ChangeAvailability"),
        is(instanceOf(ChangeAvailabilityFeature.class)));
  }

  @Test
  public void getFeatureList_containsChangeConfigurationFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // then
    assertThat(
        findFeature(features, "ChangeConfiguration"),
        is(instanceOf(ChangeConfigurationFeature.class)));
  }

  @Test
  public void getFeatureList_containsClearCacheFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // then
    assertThat(findFeature(features, "ClearCache"), is(instanceOf(ClearCacheFeature.class)));
  }

  @Test
  public void getFeatureList_containsDataTransfer() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "DataTransfer"), is(instanceOf(DataTransferFeature.class)));
  }

  @Test
  public void getFeatureList_containsGetConfigurationFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // then
    assertThat(
        findFeature(features, "GetConfiguration"), is(instanceOf(GetConfigurationFeature.class)));
  }

  @Test
  public void getFeatureList_containsHeartbeatFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "Heartbeat"), is(instanceOf(HeartbeatFeature.class)));
  }

  @Test
  public void getFeatureList_containsMeterValuesFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "MeterValues"), is(instanceOf(MeterValuesFeature.class)));
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
  public void getFeatureList_containsRemoteStopTransactionFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "RemoteStopTransaction"),
        is(instanceOf(RemoteStopTransactionFeature.class)));
  }

  @Test
  public void getFeatureList_containsResetFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(findFeature(features, "Reset"), is(instanceOf(ResetFeature.class)));
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
  public void getFeatureList_containsStatusNotificationFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "StatusNotification"),
        is(instanceOf(StatusNotificationFeature.class)));
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
  public void getFeatureList_containsUnlockConnectorFeature() {
    // When
    Feature[] features = core.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "UnlockConnector"), is(instanceOf(UnlockConnectorFeature.class)));
  }

  @Test
  public void handleRequest_aChangeAvailabilityRequest_callsHandleChangeAvailabilityRequest() {
    // Given
    ChangeAvailabilityRequest request =
        new ChangeAvailabilityRequest(0, AvailabilityType.Operative);

    // When
    core.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleChangeAvailabilityRequest(eq(request));
  }

  @Test
  public void
      handleRequest_aRemoteStartTransactionRequest_callsHandleRemoteStartTransactionRequest() {
    // Given
    RemoteStartTransactionRequest request = new RemoteStartTransactionRequest("test123");

    // When
    core.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleRemoteStartTransactionRequest(eq(request));
  }

  @Test
  public void
      handleRequest_aRemoteStopTransactionRequest_callsHandleRemoteStopTransactionRequest() {
    // Given
    RemoteStopTransactionRequest request = new RemoteStopTransactionRequest(0);

    // When
    core.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleRemoteStopTransactionRequest(eq(request));
  }

  @Test
  public void handleRequest_aResetRequest_callsHandleResetRequest() {
    // Given
    ResetRequest request = new ResetRequest(ResetType.Hard);

    // When
    core.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleResetRequest(eq(request));
  }

  @Test
  public void handleRequest_anUnlockConnectorRequest_callsHandleUnlockConnectorRequest() {
    // Given
    UnlockConnectorRequest request = new UnlockConnectorRequest(1);

    // When
    core.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleUnlockConnectorRequest(eq(request));
  }

  @Test
  public void handleRequest_aChangeAvailabilityRequest_returnsChangeAvailabilityConfirmation() {
    // Given
    when(handler.handleChangeAvailabilityRequest(any()))
        .thenReturn(new ChangeAvailabilityConfirmation(AvailabilityStatus.Accepted));
    ChangeAvailabilityRequest request =
        new ChangeAvailabilityRequest(0, AvailabilityType.Operative);

    // When
    Confirmation conf = core.handleRequest(SESSION_NULL, request);

    // Then
    assertThat(conf, instanceOf(ChangeAvailabilityConfirmation.class));
  }

  @Test
  public void handleRequest_aGetConfigurationRequest_callsHandleGetConfigurationRequest() {
    // Given
    GetConfigurationRequest request = new GetConfigurationRequest();

    // When
    core.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleGetConfigurationRequest(eq(request));
  }

  @Test
  public void handleRequest_aGetConfigurationRequest_returnGetConfigurationConfirmation() {
    // Given
    when(handler.handleGetConfigurationRequest(any()))
        .thenReturn(new GetConfigurationConfirmation());
    GetConfigurationRequest request = new GetConfigurationRequest();

    // When
    Confirmation conf = core.handleRequest(SESSION_NULL, request);

    // Then
    assertThat(conf, instanceOf(GetConfigurationConfirmation.class));
  }

  @Test
  public void handleRequest_aDataTransferRequest_callsHandleDataTransferRequest() {
    // Given
    DataTransferRequest request = new DataTransferRequest("vendorId");

    // When
    core.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleDataTransferRequest(eq(request));
  }

  @Test
  public void handleRequest_aDataTransferRequest_returnDataTransferConfirmation() {
    // Given
    when(handler.handleDataTransferRequest(any())).thenReturn(new DataTransferConfirmation());
    DataTransferRequest request = new DataTransferRequest("vendorId");

    // When
    Confirmation conf = core.handleRequest(SESSION_NULL, request);

    // Then
    assertThat(conf, instanceOf(DataTransferConfirmation.class));
  }

  @Test
  public void handleRequest_aChangeConfigurationRequest_callsHandleChangeConfigurationRequest() {
    // Given
    ChangeConfigurationRequest request = new ChangeConfigurationRequest("key", "value");

    // When
    core.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleChangeConfigurationRequest(eq(request));
  }

  @Test
  public void handleRequest_aChangeConfigurationRequest_returnsChangeConfigurationConfirmation() {
    // Given
    when(handler.handleChangeConfigurationRequest(any()))
        .thenReturn(new ChangeConfigurationConfirmation(ConfigurationStatus.Accepted));
    ChangeConfigurationRequest request = new ChangeConfigurationRequest("key", "value");

    // When
    Confirmation conf = core.handleRequest(SESSION_NULL, request);

    // Then
    assertThat(conf, instanceOf(ChangeConfigurationConfirmation.class));
  }

  @Test
  public void handleRequest_aClearCacheRequest_callsHandleClearCacheRequest() {
    // Given
    ClearCacheRequest request = new ClearCacheRequest();

    // When
    core.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleClearCacheRequest(request);
  }

  @Test
  public void handleRequest_aClearCacheRequest_returnsClearCacheConfirmation() {
    // Given
    when(handler.handleClearCacheRequest(any())).thenReturn(new ClearCacheConfirmation());
    ClearCacheRequest request = new ClearCacheRequest();

    // When
    Confirmation conf = core.handleRequest(SESSION_NULL, request);

    // Then
    assertThat(conf, instanceOf(ClearCacheConfirmation.class));
  }
}
