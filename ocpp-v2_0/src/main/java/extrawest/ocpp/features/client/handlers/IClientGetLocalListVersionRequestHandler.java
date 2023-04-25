package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetLocalListVersionRequest;
import extrawest.ocpp.model.response.GetLocalListVersionResponse;

/** Charging Station handler of {@link GetLocalListVersionRequest} */
public interface IClientGetLocalListVersionRequestHandler {
    GetLocalListVersionResponse handleGetLocalListVersionRequest(GetLocalListVersionRequest request);
}
