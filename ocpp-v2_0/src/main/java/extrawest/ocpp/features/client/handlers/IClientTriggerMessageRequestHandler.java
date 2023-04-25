package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.TriggerMessageRequest;
import extrawest.ocpp.model.response.TriggerMessageResponse;

/** Charging Station handler of {@link TriggerMessageRequest} */
public interface IClientTriggerMessageRequestHandler {
    TriggerMessageResponse handleTriggerMessageRequest(TriggerMessageRequest request);
}
