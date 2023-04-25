package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetTransactionStatusRequest;
import extrawest.ocpp.model.response.GetTransactionStatusResponse;

/** Charging Station handler of {@link GetTransactionStatusRequest} */
public interface IClientGetTransactionStatusRequestHandler {
    GetTransactionStatusResponse handleGetTransactionRequest(GetTransactionStatusRequest request);
}
