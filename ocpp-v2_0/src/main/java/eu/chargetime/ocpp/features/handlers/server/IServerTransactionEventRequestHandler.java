package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.TransactionEventRequest;
import eu.chargetime.ocpp.model.response.TransactionEventResponse;

import java.util.UUID;

/** Central system handler of {@link TransactionEventRequest}s. */
public interface IServerTransactionEventRequestHandler {
    TransactionEventResponse handleTransactionEventRequest(
            UUID sessionIndex, TransactionEventRequest request);
}
