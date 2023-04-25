package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.ClearDisplayMessageRequest;
import extrawest.ocpp.model.response.ClearDisplayMessageResponse;

/** Charging Station handler of {@link ClearDisplayMessageRequest} */
public interface IClientClearDisplayMessageRequestHandler {
    ClearDisplayMessageResponse handleClearDisplayMessageRequest(ClearDisplayMessageRequest request);
}
