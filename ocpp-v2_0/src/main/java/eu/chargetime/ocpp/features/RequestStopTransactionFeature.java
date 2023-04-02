package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientRequestStopTransactionRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.RequestStopTransactionRequest;
import eu.chargetime.ocpp.model.response.RequestStopTransactionResponse;

import java.util.UUID;

public class RequestStopTransactionFeature implements Feature {
    private final IClientRequestStopTransactionRequestHandler handler;

    public RequestStopTransactionFeature(IClientRequestStopTransactionRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleRequestStopTransactionRequest((RequestStopTransactionRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return RequestStopTransactionRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return RequestStopTransactionResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.REQUEST_STOP_TRANSACTION.value();
    }
}
