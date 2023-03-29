package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.RequestStartTransactionRequest;
import eu.chargetime.ocpp.model.response.RequestStartTransactionResponse;

/** Charging Station handler of {@link RequestStartTransactionRequest} */
public interface IClientRequestStartTransactionRequestHandler {
    RequestStartTransactionResponse handleRequestStartTransactionRequest(RequestStartTransactionRequest request);
}
