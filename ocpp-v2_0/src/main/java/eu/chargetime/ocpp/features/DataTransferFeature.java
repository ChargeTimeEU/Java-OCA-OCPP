package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientDataTransferRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.DataTransferRequest;
import eu.chargetime.ocpp.model.response.DataTransferResponse;

import java.util.UUID;

public class DataTransferFeature implements Feature {
    private final IClientDataTransferRequestHandler handler;

    public DataTransferFeature(IClientDataTransferRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleCancelDataTransferRequest((DataTransferRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return DataTransferRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return DataTransferResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.DATA_TRANSFER.value();
    }
}
