package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.*;

import java.util.Calendar;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

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
public class FakeCentralSystem
{
    private Request receivedRequest;
    private Confirmation receivedConfirmation;
    private JSONServer server;

    private int sessionIndex;

    private static FakeCentralSystem instance;
    public static FakeCentralSystem getInstance () {
        if (instance == null)
            instance = new FakeCentralSystem();
        return instance;
    }

    private FakeCentralSystem() { }

    public void started() throws Exception
    {
        if (server != null)
            return;

        server = new JSONServer(new ServerCoreProfile(new ServerCoreEventHandler() {
            @Override
            public AuthorizeConfirmation handleAuthorizeRequest(int sessionIndex, AuthorizeRequest request) {
                receivedRequest = request;
                AuthorizeConfirmation confirmation = new AuthorizeConfirmation();
                IdTagInfo tagInfo = new IdTagInfo();
                tagInfo.setStatus(AuthorizationStatus.Accepted);
                confirmation.setIdTagInfo(tagInfo);
                return confirmation;
            }

            @Override
            public BootNotificationConfirmation handleBootNotificationRequest(int sessionIndex, BootNotificationRequest request) {
                receivedRequest = request;
                BootNotificationConfirmation confirmation = new BootNotificationConfirmation();
                try {
                    confirmation.setInterval(1);
                } catch (Exception e) {
                }
                confirmation.setCurrentTime(Calendar.getInstance());
                confirmation.setStatus(RegistrationStatus.Accepted);
                return confirmation;
            }

            @Override
            public DataTransferConfirmation handleDataTransferRequest(int sessionIndex, DataTransferRequest request) {
                receivedRequest = request;
                DataTransferConfirmation confirmation = new DataTransferConfirmation();
                confirmation.setStatus(DataTransferStatus.Accepted);
                return confirmation;
            }

            @Override
            public HeartbeatConfirmation handleHeartbeatRequest(int sessionIndex, HeartbeatRequest request) {
                receivedRequest = request;
                HeartbeatConfirmation confirmation = new HeartbeatConfirmation();
                confirmation.setCurrentTime(Calendar.getInstance());
                return confirmation;
            }

            @Override
            public MeterValuesConfirmation handleMeterValuesRequest(int sessionIndex, MeterValuesRequest request) {
                receivedRequest = request;
                return new MeterValuesConfirmation();
            }
        }));
        server.open("localhost", 8887, new ServerEvents() {
            @Override
            public void newSession(int identity) {
                sessionIndex = identity;
            }

            @Override
            public void lostSession(int identity) {
                sessionIndex = -1;
            }
        });
    }

    public boolean hasHandledAuthorizeRequest() {
        return receivedRequest instanceof AuthorizeRequest;
    }

    public void stopped() {
        server.close();
    }

    public boolean hasHandledBootNotification(String vendor, String model) {
        boolean result = receivedRequest instanceof BootNotificationRequest;
        if (result) {
            BootNotificationRequest request = (BootNotificationRequest) this.receivedRequest;
            result &= request.getChargePointVendor().equals(vendor);
            result &= request.getChargePointModel().equals(model);
        }
        return result;
    }

    public void sendChangeAvailabilityRequest(int connectorId, AvailabilityType type) throws Exception {
        ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();
        request.setType(type);
        request.setConnectorId(connectorId);
        server.send(sessionIndex, request).whenComplete((confirmation, throwable) -> receivedConfirmation = confirmation);

    }

    public boolean hasReceivedChangeAvailabilityConfirmation(String status) {
        boolean result = receivedConfirmation instanceof ChangeAvailabilityConfirmation;
        if (result)
            result &= ((ChangeAvailabilityConfirmation) receivedConfirmation).getStatus().equals(status);
        return result;
    }

    public void sendChangeConfigurationRequest(String key, String value) throws Exception {
        ChangeConfigurationRequest request = new ChangeConfigurationRequest();
        request.setKey(key);
        request.setValue(value);
        server.send(sessionIndex, request).whenComplete((confirmation, throwable) -> receivedConfirmation = confirmation);
    }

    public boolean hasReceivedChangeConfigurationConfirmation() {
        return receivedConfirmation instanceof ChangeConfigurationConfirmation;
    }

    public void sendClearCacheRequest() throws Exception {
        ClearCacheRequest request = new ClearCacheRequest();
        server.send(sessionIndex, request).whenComplete((confirmation, throwable) -> receivedConfirmation = confirmation);
    }

    public boolean hasReceivedClearCacheConfirmation() {
        return receivedConfirmation instanceof ClearCacheConfirmation;
    }

    public void sendDataTransferRequest(String vendorId, String messageId, String data) throws Exception {
        DataTransferRequest request = new DataTransferRequest();
        request.setVendorId(vendorId);
        request.setMessageId(messageId);
        request.setData(data);
        server.send(sessionIndex, request).whenComplete((confirmation, throwable) -> receivedConfirmation = confirmation);
    }

    public boolean hasReceivedDataTransferConfirmation() {
        return receivedConfirmation instanceof DataTransferConfirmation;
    }

    public boolean hasHandledDataTransferRequest() {
        return receivedRequest instanceof DataTransferRequest;
    }

    public void sendGetConfigurationRequest(String... key) throws Exception {
        GetConfigurationRequest request = new GetConfigurationRequest();
        request.setKey(key);
        server.send(sessionIndex, request).whenComplete((confirmation, throwable) -> receivedConfirmation = confirmation);
    }

    public boolean hasReceivedGetConfigurationConfirmation() {
        return receivedConfirmation instanceof GetConfigurationConfirmation;
    }

    public boolean hasHandledHeartbeat() {
        return receivedRequest instanceof HeartbeatRequest;
    }

    public boolean hasHandledMeterValuesRequest() {
        return receivedRequest instanceof MeterValuesRequest;
    }

    public void sendRemoteStartTransactionRequest(int connectorId, String idTag) throws Exception {
        RemoteStartTransactionRequest request = new RemoteStartTransactionRequest();
        IdToken idToken = new IdToken();
        idToken.setIdToken(idTag);
        request.setIdTag(idToken);
        request.setConnectorId(connectorId);
        server.send(sessionIndex, request).whenComplete((confirmation, throwable) -> receivedConfirmation = confirmation);
    }

    public boolean hasReceivedRemoteStartTransactionConfirmation(String status) {
        boolean result = receivedConfirmation instanceof RemoteStartTransactionConfirmation;
        if (result)
            result &= ((RemoteStartTransactionConfirmation) receivedConfirmation).getStatus().equals(status);
        return result;
    }

    public void sendRemoteStopTransactionRequest(int transactionId) throws Exception {
        RemoteStopTransactionRequest request = new RemoteStopTransactionRequest();
        request.setTransactionId(transactionId);
        server.send(sessionIndex, request).whenComplete((confirmation, throwable) -> receivedConfirmation = confirmation);
    }

    public boolean hasReceivedRemoteStopTransactionConfirmation(String status) {
        boolean result = receivedConfirmation instanceof RemoteStopTransactionConfirmation;
        if (result)
            result &= ((RemoteStopTransactionConfirmation) receivedConfirmation).getStatus().equals(status);
        return result;
    }

    public void sendResetRequest(ResetType type) throws Exception {
        ResetRequest request = new ResetRequest();
        request.setType(type);
        server.send(sessionIndex, request).whenComplete((confirmation, throwable) -> receivedConfirmation = confirmation);
    }

    public boolean hasReceivedResetConfirmation(String status) {
        boolean result = receivedConfirmation instanceof ResetConfirmation;
        if (result)
            result &= ((ResetConfirmation) receivedConfirmation).getStatus().equals(status);
        return result;
    }

}
