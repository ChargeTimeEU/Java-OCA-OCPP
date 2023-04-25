package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerTransactionEventRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.TransactionEventRequest;
import extrawest.ocpp.model.response.TransactionEventResponse;

import java.util.UUID;

public class TransactionEventFeature implements Feature {
    private final IServerTransactionEventRequestHandler handler;

    public TransactionEventFeature(IServerTransactionEventRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleTransactionEventRequest(sessionIndex, (TransactionEventRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return TransactionEventRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return TransactionEventResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.TRANSACTION_EVENT.value();
    }
}
