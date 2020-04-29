package eu.chargetime.ocpp.test;
/*
ChargeTime.eu - Java-OCA-OCPP
Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

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

import eu.chargetime.ocpp.IServerAPI;
import eu.chargetime.ocpp.JSONConfiguration;
import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.feature.profile.*;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.*;
import eu.chargetime.ocpp.model.firmware.*;
import eu.chargetime.ocpp.model.localauthlist.*;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequest;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequestType;
import eu.chargetime.ocpp.model.reservation.CancelReservationConfirmation;
import eu.chargetime.ocpp.model.reservation.CancelReservationRequest;
import eu.chargetime.ocpp.model.reservation.ReserveNowConfirmation;
import eu.chargetime.ocpp.model.reservation.ReserveNowRequest;
import eu.chargetime.ocpp.model.smartcharging.SetChargingProfileConfirmation;
import eu.chargetime.ocpp.model.smartcharging.SetChargingProfileRequest;
import eu.chargetime.ocpp.test.FakeCentral.serverType;
import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeCentralSystem {
  private static final Logger logger = LoggerFactory.getLogger(FakeCentralSystem.class);

  private IServerAPI server;

  private DummyHandlers dummyHandlers;
  private boolean isStarted;

  FakeCentralSystem(serverType type) {
    dummyHandlers = new DummyHandlers();

    ServerCoreProfile serverCoreProfile =
        new ServerCoreProfile(dummyHandlers.createServerCoreEventHandler());

    if (type == serverType.JSON) {
      JSONConfiguration configuration =
          JSONConfiguration.get().setParameter(JSONConfiguration.REUSE_ADDR_PARAMETER, true);
      server = new JSONTestServer(serverCoreProfile, configuration);
    } else {
      server = new SOAPTestServer(serverCoreProfile);
    }

    initializeServer();
    isStarted = false;
  }

  private void initializeServer() {
    ServerSmartChargingProfile smartChargingProfile = new ServerSmartChargingProfile();
    server.addFeatureProfile(smartChargingProfile);

    ServerRemoteTriggerProfile remoteTriggerProfile = new ServerRemoteTriggerProfile();
    server.addFeatureProfile(remoteTriggerProfile);

    ServerFirmwareManagementProfile firmwareManagementProfile =
        new ServerFirmwareManagementProfile(
            dummyHandlers.createServerFirmwareManagementEventHandler());
    server.addFeatureProfile(firmwareManagementProfile);

    ServerLocalAuthListProfile localAuthListProfile = new ServerLocalAuthListProfile();
    server.addFeatureProfile(localAuthListProfile);

    ServerReservationProfile serverReservationProfile = new ServerReservationProfile();
    server.addFeatureProfile(serverReservationProfile);
  }

  public boolean isClosed() {
    return server.isClosed();
  }

  public boolean connected() {
    return dummyHandlers.getCurrentIdentifier() != null;
  }

  public void clientLost() {
    server.closeSession(dummyHandlers.getCurrentSessionIndex());
  }

  public void started() throws Exception {
    final String host = "127.0.0.1";

    if (!isStarted) {
      int port = 8890;
      if (server instanceof JSONTestServer) {
        port = 8887;
      }

      server.open(host, port, dummyHandlers.generateServerEventsHandler());
      logger.info("Server started on host: {}, port: {}", host, port);
      isStarted = true;
    }
  }

  public void stopped() {
    server.close();
    logger.info("Server stopped");
    isStarted = false;
  }

  public boolean hasHandledDiagnosticsStatusNotificationRequest() {
    return dummyHandlers.wasLatestRequest(DiagnosticsStatusNotificationRequest.class);
  }

  public boolean hasHandledFirmwareStatusNotificationRequest() {
    return dummyHandlers.wasLatestRequest(FirmwareStatusNotificationRequest.class);
  }

  public boolean hasHandledAuthorizeRequest() {
    return dummyHandlers.wasLatestRequest(AuthorizeRequest.class);
  }

  public boolean hasHandledBootNotification(String vendor, String model) {
    boolean result = false;
    BootNotificationRequest request =
        dummyHandlers.getReceivedRequest(new BootNotificationRequest());
    if (request != null) {
      result = request.getChargePointVendor().equals(vendor);
      result &= request.getChargePointModel().equals(model);
    }
    return result;
  }

  public void sendChangeAvailabilityRequest(int connectorId, AvailabilityType type)
      throws Exception {
    ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();
    request.setType(type);
    request.setConnectorId(connectorId);
    send(request);
  }

  public boolean hasReceivedGetDiagnosticsConfirmation() {
    return dummyHandlers.wasLatestConfirmation(GetDiagnosticsConfirmation.class);
  }

  public boolean hasReceivedReserveNowConfirmation() {
    return dummyHandlers.wasLatestConfirmation(ReserveNowConfirmation.class);
  }

  public boolean hasReceivedCancelReservationConfirmation() {
    return dummyHandlers.wasLatestConfirmation(CancelReservationConfirmation.class);
  }

  public boolean hasReceivedSendLocalListConfirmation() {
    return dummyHandlers.wasLatestConfirmation(SendLocalListConfirmation.class);
  }

  public boolean hasReceivedGetLocalListVersionConfirmation() {
    return dummyHandlers.wasLatestConfirmation(GetLocalListVersionConfirmation.class);
  }

  public boolean hasReceivedUpdateFirmwareConfirmation() {
    return dummyHandlers.wasLatestConfirmation(UpdateFirmwareConfirmation.class);
  }

  public boolean hasReceivedSetChargingProfileConfirmation() {
    return dummyHandlers.wasLatestConfirmation(SetChargingProfileConfirmation.class);
  }

  public boolean hasReceivedChangeAvailabilityConfirmation(String status) {
    boolean result = false;
    ChangeAvailabilityConfirmation confirmation =
        dummyHandlers.getReceivedConfirmation(new ChangeAvailabilityConfirmation());
    if (confirmation != null) result = confirmation.getStatus().toString().equals(status);
    return result;
  }

  public void sendChangeConfigurationRequest(String key, String value) throws Exception {
    ChangeConfigurationRequest request = new ChangeConfigurationRequest();
    request.setKey(key);
    request.setValue(value);
    send(request);
  }

  public boolean hasReceivedChangeConfigurationConfirmation() {
    return dummyHandlers.wasLatestConfirmation(ChangeConfigurationConfirmation.class);
  }

  public void sendClearCacheRequest() throws Exception {
    ClearCacheRequest request = new ClearCacheRequest();
    send(request);
  }

  public boolean hasReceivedClearCacheConfirmation() {
    return dummyHandlers.wasLatestConfirmation(ClearCacheConfirmation.class);
  }

  public void sendDataTransferRequest(String vendorId, String messageId, String data)
      throws Exception {
    DataTransferRequest request = new DataTransferRequest();
    request.setVendorId(vendorId);
    request.setMessageId(messageId);
    request.setData(data);
    send(request);
  }

  public boolean hasReceivedDataTransferConfirmation() {
    return dummyHandlers.wasLatestConfirmation(DataTransferConfirmation.class);
  }

  public boolean hasHandledDataTransferRequest() {
    return dummyHandlers.wasLatestRequest(DataTransferRequest.class);
  }

  public void sendGetConfigurationRequest(String... key) throws Exception {
    GetConfigurationRequest request = new GetConfigurationRequest();
    request.setKey(key);
    send(request);
  }

  public boolean hasReceivedGetConfigurationConfirmation() {
    return dummyHandlers.wasLatestConfirmation(GetConfigurationConfirmation.class);
  }

  public boolean hasHandledHeartbeat() {
    return dummyHandlers.wasLatestRequest(HeartbeatRequest.class);
  }

  public boolean hasHandledMeterValuesRequest() {
    return dummyHandlers.wasLatestRequest(MeterValuesRequest.class);
  }

  public void sendRemoteStartTransactionRequest(int connectorId, String idTag) throws Exception {
    RemoteStartTransactionRequest request = new RemoteStartTransactionRequest();
    request.setIdTag(idTag);
    request.setConnectorId(connectorId);
    send(request);
  }

  public void sendRemoteStartTransactionWithProfileRequest(int connectorId, String idTag)
      throws Exception {
    RemoteStartTransactionRequest request = new RemoteStartTransactionRequest();
    request.setIdTag(idTag);
    request.setConnectorId(connectorId);

    ChargingSchedule schedule =
        new ChargingSchedule(
            ChargingRateUnitType.A,
            new ChargingSchedulePeriod[] {new ChargingSchedulePeriod(1, 32d)});
    ChargingProfile profile =
        new ChargingProfile(
            1,
            1,
            ChargingProfilePurposeType.ChargePointMaxProfile,
            ChargingProfileKindType.Recurring,
            schedule);
    request.setChargingProfile(profile);
    send(request);
  }

  public boolean hasReceivedRemoteStartTransactionConfirmation(String status) {
    boolean result = false;
    RemoteStartTransactionConfirmation confirmation =
        dummyHandlers.getReceivedConfirmation(new RemoteStartTransactionConfirmation());
    if (confirmation != null) result = confirmation.getStatus().toString().equals(status);
    return result;
  }

  public void sendRemoteStopTransactionRequest(int transactionId) throws Exception {
    RemoteStopTransactionRequest request = new RemoteStopTransactionRequest();
    request.setTransactionId(transactionId);
    send(request);
  }

  public boolean hasReceivedRemoteStopTransactionConfirmation(String status) {
    boolean result = false;
    RemoteStopTransactionConfirmation confirmation =
        dummyHandlers.getReceivedConfirmation(new RemoteStopTransactionConfirmation());
    if (confirmation != null) result = confirmation.getStatus().toString().equals(status);
    return result;
  }

  public void sendResetRequest(ResetType type) throws Exception {
    ResetRequest request = new ResetRequest();
    request.setType(type);
    send(request);
  }

  public void sendGetDiagnosticsRequest(String location) throws Exception {
    GetDiagnosticsRequest request = new GetDiagnosticsRequest();
    request.setLocation(location);
    send(request);
  }

  public void sendUpdateFirmwareRequest(String location, ZonedDateTime retrieveDate)
      throws Exception {
    UpdateFirmwareRequest request = new UpdateFirmwareRequest(location, retrieveDate);
    send(request);
  }

  public void sendReserveNowRequest(
      Integer connectorId, ZonedDateTime expiryDate, String idTag, Integer reservationId)
      throws Exception {
    ReserveNowRequest request =
        new ReserveNowRequest(connectorId, expiryDate, idTag, reservationId);
    send(request);
  }

  public void sendCancelReservationRequest(Integer reservationId) throws Exception {
    CancelReservationRequest request = new CancelReservationRequest(reservationId);
    send(request);
  }

  public void sendGetLocalListVersionRequest() throws Exception {
    GetLocalListVersionRequest request = new GetLocalListVersionRequest();
    send(request);
  }

  public void sendSendLocalListRequest(int listVersion, UpdateType updateType) throws Exception {
    SendLocalListRequest request = new SendLocalListRequest(listVersion, updateType);
    send(request);
  }

  public void sendSetChargingProfileRequest(Integer connectorId, ChargingProfile csChargingProfiles)
      throws Exception {
    SetChargingProfileRequest request =
        new SetChargingProfileRequest(connectorId, csChargingProfiles);
    send(request);
  }

  public boolean hasReceivedResetConfirmation(String status) {
    boolean result = false;
    ResetConfirmation confirmation = dummyHandlers.getReceivedConfirmation(new ResetConfirmation());
    if (confirmation != null) result = confirmation.getStatus().toString().equals(status);
    return result;
  }

  public boolean hasHandledStartTransactionRequest() {
    return dummyHandlers.wasLatestRequest(StartTransactionRequest.class);
  }

  public boolean hasHandledStatusNotificationRequest() {
    return dummyHandlers.wasLatestRequest(StatusNotificationRequest.class);
  }

  public boolean hasHandledStopTransactionRequest() {
    return dummyHandlers.wasLatestRequest(StopTransactionRequest.class);
  }

  public void sendUnlockConnectorRequest(int connectorId) throws Exception {
    UnlockConnectorRequest request = new UnlockConnectorRequest();
    request.setConnectorId(connectorId);
    send(request);
  }

  public boolean hasReceivedUnlockConnectorConfirmation(String status) {
    boolean result = false;
    UnlockConnectorConfirmation confirmation =
        dummyHandlers.getReceivedConfirmation(new UnlockConnectorConfirmation());
    if (confirmation != null) result = confirmation.getStatus().toString().equals(status);
    return result;
  }

  public void rigNextRequestToFail() {
    dummyHandlers.setRiggedToFail(true);
  }

  public void clearRiggedToFailFlag() {
    dummyHandlers.setRiggedToFail(false);
  }

  public void sendTriggerMessage(TriggerMessageRequestType type, Integer connectorId)
      throws Exception {
    TriggerMessageRequest request = new TriggerMessageRequest(type);
    try {
      request.setConnectorId(connectorId);
    } catch (PropertyConstraintException e) {
      e.printStackTrace();
    }

    send(request);
  }

  private void send(Request request) throws Exception {
    server
        .send(dummyHandlers.getCurrentSessionIndex(), request)
        .whenComplete(dummyHandlers.generateWhenCompleteHandler());
  }
}
