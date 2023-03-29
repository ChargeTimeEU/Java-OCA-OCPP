package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.ClearDisplayMessageRequest;
import eu.chargetime.ocpp.model.response.ClearDisplayMessageResponse;

/** Charging Station handler of {@link ClearDisplayMessageRequest} */
public interface IClientClearDisplayMessageRequestHandler {
    ClearDisplayMessageResponse handleClearDisplayMessageRequest(ClearDisplayMessageRequest request);
}
