package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.SetNetworkProfileRequest;
import eu.chargetime.ocpp.model.response.SetNetworkProfileResponse;

/** Charging Station handler of {@link SetNetworkProfileRequest} */
public interface IClientSetNetworkProfileRequestHandler {
    SetNetworkProfileResponse handleSetNetworkProfileRequest(SetNetworkProfileRequest request);
}
