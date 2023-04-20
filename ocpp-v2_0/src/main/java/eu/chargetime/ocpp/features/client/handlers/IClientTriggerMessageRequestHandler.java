package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.TriggerMessageRequest;
import eu.chargetime.ocpp.model.response.TriggerMessageResponse;

/** Charging Station handler of {@link TriggerMessageRequest} */
public interface IClientTriggerMessageRequestHandler {
    TriggerMessageResponse handleTriggerMessageRequest(TriggerMessageRequest request);
}
