package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.Client;
import eu.chargetime.ocpp.JSONClient;
import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.UnsupportedFeatureException;
import eu.chargetime.ocpp.feature.profile.ClientCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
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
public class FakeChargePoint
{
    private Client client;
    private Confirmation receivedConfirmation;
    private Request receivedRequest;
    private ClientCoreProfile core;

    public FakeChargePoint() {
        core = new ClientCoreProfile(new ClientCoreEventHandler() {
            @Override
            public ChangeAvailabilityConfirmation handleChangeAvailabilityRequest(ChangeAvailabilityRequest request) {
                receivedRequest = request;
                return new ChangeAvailabilityConfirmation(AvailabilityStatus.Accepted);
            }

            @Override
            public GetConfigurationConfirmation handleGetConfigurationRequest(GetConfigurationRequest request) {
                receivedRequest = request;
                return new GetConfigurationConfirmation();
            }

            @Override
            public ChangeConfigurationConfirmation handleChangeConfigurationRequest(ChangeConfigurationRequest request) {
                receivedRequest = request;
                ChangeConfigurationConfirmation confirmation = new ChangeConfigurationConfirmation();
                confirmation.setStatus(ConfigurationStatus.Accepted);
                return confirmation;
            }

            @Override
            public ClearCacheConfirmation handleClearCacheRequest(ClearCacheRequest request) {
                receivedRequest = request;
                ClearCacheConfirmation confirmation = new ClearCacheConfirmation();
                confirmation.setStatus(ClearCacheStatus.Accepted);
                return confirmation;
            }

            @Override
            public DataTransferConfirmation handleDataTransferRequest(DataTransferRequest request) {
                receivedRequest = request;
                DataTransferConfirmation confirmation = new DataTransferConfirmation();
                confirmation.setStatus(DataTransferStatus.Accepted);
                return confirmation;
            }

            @Override
            public RemoteStartTransactionConfirmation handleRemoteStartTransactionRequest(RemoteStartTransactionRequest request) {
                receivedRequest = request;
                return new RemoteStartTransactionConfirmation(RemoteStartStopStatus.Accepted);
            }

            @Override
            public RemoteStopTransactionConfirmation handleRemoteStopTransactionRequest(RemoteStopTransactionRequest request) {
                receivedRequest = request;
                return new RemoteStopTransactionConfirmation(RemoteStartStopStatus.Accepted);
            }

            @Override
            public ResetConfirmation handleResetRequest(ResetRequest request) {
                receivedRequest = request;
                return new ResetConfirmation(ResetStatus.Accepted);
            }

            @Override
            public UnlockConnectorConfirmation handleUnlockConnectorRequest(UnlockConnectorRequest request) {
                receivedRequest = request;
                return new UnlockConnectorConfirmation(UnlockStatus.Unlocked);
            }
        });
        client = new JSONClient(core);
    }

    public void connect() {
        try {
            client.connect("ws://localhost:8887");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendBootNotification(String vendor, String model) {
        Request request = core.createBootNotificationRequest(vendor, model);
        send(request);
    }

    public void sendAuthorizeRequest(String idToken) {
        try {
            Request request = core.createAuthorizeRequest(idToken);
            send(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendHeartbeatRequest() {
        Request request = core.createHeartbeatRequest();
        send(request);
    }

    public void sendMeterValuesRequest() {
        try {
            Request request = core.createMeterValuesRequest(42, Calendar.getInstance(), "42");
            send(request);
        } catch (PropertyConstraintException ex) {
            ex.printStackTrace();
        }
    }

    public void sendStartTransactionRequest() {
        try {
            Request request = core.createStartTransactionRequest(41, "some id", 42, Calendar.getInstance());
            send(request);
        } catch (PropertyConstraintException ex) {
            ex.printStackTrace();
        }
    }

    public void sendStopTransactionRequest() {
        StopTransactionRequest request = core.createStopTransactionRequest(42, Calendar.getInstance(), 42);
        send(request);
    }

    public void sendDataTransferRequest(String vendorId, String messageId, String data) {
        try {
            DataTransferRequest request = core.createDataTransferRequest(vendorId);
            request.setMessageId(messageId);
            request.setData(data);

            send(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendStatusNotificationRequest() {
        try {
            StatusNotificationRequest request = core.createStatusNotificationRequest(42, ChargePointErrorCode.NoError, ChargePointStatus.Available);
            send(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void send(Request request) {
        try {
            client.send(request).whenComplete((s, ex) -> receivedConfirmation = s);
        } catch (UnsupportedFeatureException ex) {
            ex.printStackTrace();
        }
    }

    public boolean hasReceivedBootConfirmation(String status) {
        if (receivedConfirmation instanceof BootNotificationConfirmation)
            return ((BootNotificationConfirmation) receivedConfirmation).getStatus().equals(status);
        return false;
    }

    public boolean hasReceivedAuthorizeConfirmation(String status) {
        if (receivedConfirmation instanceof AuthorizeConfirmation)
            return ((AuthorizeConfirmation) receivedConfirmation).getIdTagInfo().getStatus().equals(status);
        return false;
    }

    public boolean hasReceivedDataTransferConfirmation(String status) {
        if (receivedConfirmation instanceof DataTransferConfirmation)
            return ((DataTransferConfirmation) receivedConfirmation).getStatus().equals(status);
        return false;
    }

    public boolean hasReceivedHeartbeatConfirmation() {
        return (receivedConfirmation instanceof HeartbeatConfirmation);
    }

    public boolean hasReceivedMeterValuesConfirmation() {
        return (receivedConfirmation instanceof MeterValuesConfirmation);
    }

    public boolean hasReceivedStartTransactionConfirmation() {
        return (receivedConfirmation instanceof StartTransactionConfirmation);
    }

    public boolean hasReceivedStatusNotificationConfirmation() {
        return (receivedConfirmation instanceof StatusNotificationConfirmation);
    }

    public boolean hasReceivedStopTransactionConfirmation() {
        return (receivedConfirmation instanceof StopTransactionConfirmation);
    }

    public void disconnect() {
        client.disconnect();
    }

    public boolean hasHandledChangeAvailabilityRequest() {
        return receivedRequest instanceof ChangeAvailabilityRequest;
    }

    public boolean hasHandledGetConfigurationRequest() {
        return receivedRequest instanceof GetConfigurationRequest;
    }

    public boolean hasHandledChangeConfigurationRequest() {
        return receivedRequest instanceof ChangeConfigurationRequest;
    }

    public boolean hasHandledClearCacheRequest() {
        return receivedRequest instanceof ClearCacheRequest;
    }

    public boolean hasHandledDataTransferRequest() {
        return receivedRequest instanceof DataTransferRequest;
    }

    public boolean hasHandledRemoteStartTransactionRequest() {
        return receivedRequest instanceof RemoteStartTransactionRequest;
    }

    public boolean hasHandledRemoteStopTransactionRequest() {
        return receivedRequest instanceof RemoteStopTransactionRequest;
    }

    public boolean hasHandledResetRequest() {
        return receivedRequest instanceof ResetRequest;
    }

    public boolean hasHandledUnlockConnectorRequest() {
        return receivedRequest instanceof UnlockConnectorRequest;
    }
}
