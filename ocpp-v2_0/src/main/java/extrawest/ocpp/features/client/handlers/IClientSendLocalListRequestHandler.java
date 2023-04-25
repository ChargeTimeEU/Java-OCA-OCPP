package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.SendLocalListRequest;
import extrawest.ocpp.model.response.SendLocalListResponse;

/** Charging Station handler of {@link SendLocalListRequest} */
public interface IClientSendLocalListRequestHandler {
    SendLocalListResponse handleSendLocalListRequest(SendLocalListRequest request);
}
