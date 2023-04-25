package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientRequestStopTransactionRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.RequestStopTransactionRequest;
import extrawest.ocpp.model.response.RequestStopTransactionResponse;

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
