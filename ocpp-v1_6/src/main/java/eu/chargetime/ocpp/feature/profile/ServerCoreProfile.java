package eu.chargetime.ocpp.feature.profile;/*
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

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.*;

import java.util.HashSet;
import java.util.UUID;

public class ServerCoreProfile implements Profile {

    private ServerCoreEventHandler handler;
    private HashSet<Feature> features;

    public ServerCoreProfile(ServerCoreEventHandler handler) {
        this.handler = handler;

        features = new HashSet<>();
        features.add(new AuthorizeFeature(this));
        features.add(new BootNotificationFeature(this));
        features.add(new ChangeAvailabilityFeature(this));
        features.add(new ChangeConfigurationFeature(this));
        features.add(new ClearCacheFeature(this));
        features.add(new DataTransferFeature(this));
        features.add(new GetConfigurationFeature(this));
        features.add(new HeartbeatFeature(this));
        features.add(new MeterValuesFeature(this));
        features.add(new RemoteStartTransactionFeature(this));
        features.add(new RemoteStopTransactionFeature(this));
        features.add(new ResetFeature(this));
        features.add(new StartTransactionFeature(this));
        features.add(new StatusNotificationFeature(this));
        features.add(new StopTransactionFeature(this));
        features.add(new UnlockConnectorFeature(this));
    }

    @Override
    public Feature[] getFeatureList() {
        return features.toArray(new Feature[0]);
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof AuthorizeRequest) {
            result = handler.handleAuthorizeRequest(sessionIndex, (AuthorizeRequest) request);
        } else if (request instanceof BootNotificationRequest) {
            result = handler.handleBootNotificationRequest(sessionIndex, (BootNotificationRequest) request);
        } else if (request instanceof DataTransferRequest) {
            result = handler.handleDataTransferRequest(sessionIndex, (DataTransferRequest) request);
        } else if (request instanceof HeartbeatRequest) {
            result = handler.handleHeartbeatRequest(sessionIndex, (HeartbeatRequest) request);
        } else if (request instanceof MeterValuesRequest) {
            result = handler.handleMeterValuesRequest(sessionIndex, (MeterValuesRequest) request);
        } else if (request instanceof StartTransactionRequest) {
            result = handler.handleStartTransactionRequest(sessionIndex, (StartTransactionRequest) request);
        } else if (request instanceof StatusNotificationRequest) {
            result = handler.handleStatusNotificationRequest(sessionIndex, (StatusNotificationRequest) request);
        } else if (request instanceof StopTransactionRequest) {
            result = handler.handleStopTransactionRequest(sessionIndex, (StopTransactionRequest) request);
        }

        return result;
    }

    public ChangeAvailabilityRequest createChangeAvailabilityRequest(AvailabilityType type, int connectorId) throws PropertyConstraintException {
        ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();
        request.setType(type);
        request.setConnectorId(connectorId);
        return request;
    }

    public ChangeConfigurationRequest createChangeConfigurationRequest(String key, String value) throws PropertyConstraintException {
        ChangeConfigurationRequest request = new ChangeConfigurationRequest();
        request.setKey(key);
        request.setValue(value);
        return request;
    }

    public ClearCacheRequest createClearCacheRequest() {
        return new ClearCacheRequest();
    }

    public DataTransferRequest createDataTransferRequest(String vendorId) throws PropertyConstraintException {
        DataTransferRequest request = new DataTransferRequest();
        request.setVendorId(vendorId);
        return request;
    }

    public GetConfigurationRequest createGetConfigurationRequest() {
        return new GetConfigurationRequest();
    }

    public RemoteStartTransactionRequest createRemoteStartTransactionRequest(String idToken) throws PropertyConstraintException {
        RemoteStartTransactionRequest request = new RemoteStartTransactionRequest();
        request.setIdTag(idToken);
        return request;
    }

    public RemoteStopTransactionRequest createRemoteStopTransactionRequest(Integer transactionId) {
        RemoteStopTransactionRequest request = new RemoteStopTransactionRequest();
        request.setTransactionId(transactionId);
        return request;
    }

    public ResetRequest createResetRequest(ResetType type) {
        ResetRequest request = new ResetRequest();
        request.setType(type);
        return request;
    }

    public UnlockConnectorRequest createUnlockConnectorRequest(int connectorId) throws PropertyConstraintException {
        UnlockConnectorRequest request = new UnlockConnectorRequest();
        request.setConnectorId(connectorId);
        return request;
    }
}
