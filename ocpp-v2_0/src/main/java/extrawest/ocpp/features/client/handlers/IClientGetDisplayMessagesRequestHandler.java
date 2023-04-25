package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetDisplayMessagesRequest;
import extrawest.ocpp.model.response.GetDisplayMessagesResponse;

/** Charging Station handler of {@link GetDisplayMessagesRequest} */
public interface IClientGetDisplayMessagesRequestHandler {
    GetDisplayMessagesResponse handleGetDisplayMessagesRequest(GetDisplayMessagesRequest request);
}
