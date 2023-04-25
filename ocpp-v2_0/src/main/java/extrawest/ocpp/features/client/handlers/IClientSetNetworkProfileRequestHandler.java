package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.SetNetworkProfileRequest;
import extrawest.ocpp.model.response.SetNetworkProfileResponse;

/** Charging Station handler of {@link SetNetworkProfileRequest} */
public interface IClientSetNetworkProfileRequestHandler {
    SetNetworkProfileResponse handleSetNetworkProfileRequest(SetNetworkProfileRequest request);
}
