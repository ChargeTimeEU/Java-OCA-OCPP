package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.GetDisplayMessagesRequest;
import eu.chargetime.ocpp.model.response.GetDisplayMessagesResponse;

/** Charging Station handler of {@link GetDisplayMessagesRequest} */
public interface IClientGetDisplayMessagesRequestHandler {
    GetDisplayMessagesResponse handleGetDisplayMessagesRequest(GetDisplayMessagesRequest request);
}
