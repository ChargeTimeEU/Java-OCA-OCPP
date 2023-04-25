package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.SendLocalListRequest;
import extrawest.ocpp.model.request.SetDisplayMessageRequest;
import extrawest.ocpp.model.response.SetDisplayMessageResponse;

/** Charging Station handler of {@link SendLocalListRequest} */
public interface IClientSetDisplayMessageRequestHandler {
    SetDisplayMessageResponse handleSetDisplayMessageRequest(SetDisplayMessageRequest request);
}
