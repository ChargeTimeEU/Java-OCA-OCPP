package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.RequestStopTransactionRequest;
import extrawest.ocpp.model.response.RequestStopTransactionResponse;

/** Charging Station handler of {@link RequestStopTransactionRequest} */
public interface IClientRequestStopTransactionRequestHandler {
    RequestStopTransactionResponse handleRequestStopTransactionRequest(RequestStopTransactionRequest request);
}
