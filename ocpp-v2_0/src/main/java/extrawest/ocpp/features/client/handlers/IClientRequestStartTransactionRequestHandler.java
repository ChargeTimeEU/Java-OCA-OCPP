package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.RequestStartTransactionRequest;
import extrawest.ocpp.model.response.RequestStartTransactionResponse;

/** Charging Station handler of {@link RequestStartTransactionRequest} */
public interface IClientRequestStartTransactionRequestHandler {
    RequestStartTransactionResponse handleRequestStartTransactionRequest(RequestStartTransactionRequest request);
}
