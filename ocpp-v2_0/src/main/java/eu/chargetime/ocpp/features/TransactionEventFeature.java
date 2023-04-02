package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerTransactionEventRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.TransactionEventRequest;
import eu.chargetime.ocpp.model.response.TransactionEventResponse;

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
