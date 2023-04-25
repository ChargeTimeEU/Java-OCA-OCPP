package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientDataTransferRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.DataTransferRequest;
import extrawest.ocpp.model.response.DataTransferResponse;

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
