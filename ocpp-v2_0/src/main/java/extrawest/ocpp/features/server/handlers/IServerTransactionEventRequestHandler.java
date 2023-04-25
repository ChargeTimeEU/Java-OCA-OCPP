package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.TransactionEventRequest;
import extrawest.ocpp.model.response.TransactionEventResponse;

import java.util.UUID;

/** Central system handler of {@link TransactionEventRequest}s. */
public interface IServerTransactionEventRequestHandler {
    TransactionEventResponse handleTransactionEventRequest(
            UUID sessionIndex, TransactionEventRequest request);
}
