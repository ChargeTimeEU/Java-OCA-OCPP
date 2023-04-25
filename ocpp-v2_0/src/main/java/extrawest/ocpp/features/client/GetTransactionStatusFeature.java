package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetTransactionStatusRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetTransactionStatusRequest;
import extrawest.ocpp.model.response.GetTransactionStatusResponse;

import java.util.UUID;

public class GetTransactionStatusFeature implements Feature {
    private final IClientGetTransactionStatusRequestHandler handler;

    public GetTransactionStatusFeature(IClientGetTransactionStatusRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetTransactionRequest((GetTransactionStatusRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetTransactionStatusRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetTransactionStatusResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_TRANSACTION_STATUS.value();
    }
}
