package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientRequestStartTransactionRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.RequestStartTransactionRequest;
import extrawest.ocpp.model.response.RequestStartTransactionResponse;

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
