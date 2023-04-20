package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.GetChargingProfilesRequest;
import eu.chargetime.ocpp.model.response.GetChargingProfilesResponse;

/** Charging Station handler of {@link GetChargingProfilesRequest} */
public interface IClientGetChargingProfilesRequestHandler {
    GetChargingProfilesResponse handleGetChargingProfilesRequest(GetChargingProfilesRequest request);
}
