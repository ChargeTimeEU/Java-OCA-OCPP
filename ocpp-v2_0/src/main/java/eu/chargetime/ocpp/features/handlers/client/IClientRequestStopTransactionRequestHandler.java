package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.RequestStopTransactionRequest;
import eu.chargetime.ocpp.model.response.RequestStopTransactionResponse;

/** Charging Station handler of {@link RequestStopTransactionRequest} */
public interface IClientRequestStopTransactionRequestHandler {
    RequestStopTransactionResponse handleRequestStopTransactionRequest(RequestStopTransactionRequest request);
}
