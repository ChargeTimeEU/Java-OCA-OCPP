package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetTransactionStatusRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetTransactionStatusRequest;
import eu.chargetime.ocpp.model.response.GetTransactionStatusResponse;

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
