package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientRequestStartTransactionRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.RequestStartTransactionRequest;
import eu.chargetime.ocpp.model.response.RequestStartTransactionResponse;

import java.util.UUID;

public class RequestStartTransactionFeature implements Feature {
    private final IClientRequestStartTransactionRequestHandler handler;

    public RequestStartTransactionFeature(IClientRequestStartTransactionRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleRequestStartTransactionRequest((RequestStartTransactionRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return RequestStartTransactionRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return RequestStartTransactionResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.REQUEST_START_TRANSACTION.value();
    }
}
