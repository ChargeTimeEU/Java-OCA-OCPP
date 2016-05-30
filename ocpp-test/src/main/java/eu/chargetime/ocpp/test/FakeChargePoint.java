package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.profile.ClientCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.CoreProfile;
import eu.chargetime.ocpp.model.*;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
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
    private CoreProfile core;

    public FakeChargePoint() {
        core = new CoreProfile(new ClientCoreEventHandler() {
            @Override
            public ChangeAvailabilityConfirmation handleChangeAvailabilityRequest(ChangeAvailabilityRequest request) {
                receivedRequest = request;
                return new ChangeAvailabilityConfirmation("Accepted");
            }

            @Override
            public GetConfigurationConfirmation handleGetConfigurationRequest(GetConfigurationRequest request) {
                receivedRequest = request;
                return new GetConfigurationConfirmation();
            }

            @Override
            public ChangeConfigurationConfirmation handleChangeConfigurationRequest(ChangeConfigurationRequest request) {
                receivedRequest = request;
                return new ChangeConfigurationConfirmation();
            }

            @Override
            public ClearCacheConfirmation handleClearCacheRequest(ClearCacheRequest request) {
                receivedRequest = request;
                return new ClearCacheConfirmation();
            }

            @Override
            public DataTransferConfirmation handleDataTransferRequest(DataTransferRequest request) {
                receivedRequest = request;
                return new DataTransferConfirmation();
            }

            @Override
            public RemoteStartTransactionConfirmation handleRemoteStartTransactionRequest(RemoteStartTransactionRequest request) {
                receivedRequest = request;
                return new RemoteStartTransactionConfirmation("Accepted");
            }

            @Override
            public RemoteStopTransactionConfirmation handleRemoteStopTransactionRequest(RemoteStopTransactionRequest request) {
                receivedRequest = request;
                return new RemoteStopTransactionConfirmation("Accepted");
            }
        });
        client = new Client(new Session(new JSONCommunicator(new WebSocketTransmitter()), new Queue()));
        client.addFeatureProfile(core);
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

    private void send(Request request) {
        client.send(request).whenComplete((s, ex) -> receivedConfirmation = s);
    }

    public void hasReceivedBootConfirmation(String status) {
        assertThat(receivedConfirmation, instanceOf(BootNotificationConfirmation.class));
        assertThat(((BootNotificationConfirmation)receivedConfirmation).getStatus(), is(status));
    }

    public void hasReceivedAuthorizeConfirmation(String status) {
        assertThat(receivedConfirmation, instanceOf(AuthorizeConfirmation.class));
        assertThat(((AuthorizeConfirmation)receivedConfirmation).getIdTagInfo().getStatus(), is(status));
    }

    public void hasReceivedDataTransferConfirmation(String status) {
        assertThat(receivedConfirmation, instanceOf(DataTransferConfirmation.class));
        assertThat(((DataTransferConfirmation)receivedConfirmation).getStatus(), equalTo(status));
    }

    public void hasReceivedHeartbeatConfirmation() {
        assertThat(receivedConfirmation, instanceOf(HeartbeatConfirmation.class));
    }

    public void hasReceivedMeterValuesConfirmation() {
        assertThat(receivedConfirmation, instanceOf(MeterValuesConfirmation.class));
    }

    public void disconnect() {
        client.disconnect();
    }

    private void confirmRequest(Class<?> type) {
        assertThat(receivedRequest, instanceOf(type));
    }

    public void hasHandledChangeAvailabilityRequest() {
        confirmRequest(ChangeAvailabilityRequest.class);
    }

    public void hasHandledGetConfigurationRequest() {
        confirmRequest(GetConfigurationRequest.class);
    }

    public void hasHandledChangeConfigurationRequest() {
        confirmRequest(ChangeConfigurationRequest.class);
    }

    public void hasHandledClearCacheRequest() {
        confirmRequest(ClearCacheRequest.class);
    }

    public void hasHandledDataTransferRequest() {
        confirmRequest(DataTransferRequest.class);
    }

    public void hasHandledRemoteStartTransactionRequest() {
        confirmRequest(RemoteStartTransactionRequest.class);
    }

    public void hasHandledRemoteStopTransactionRequest() {
        confirmRequest(RemoteStopTransactionRequest.class);
    }
}
