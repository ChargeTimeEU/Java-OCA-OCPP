package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetChargingProfilesRequest;
import extrawest.ocpp.model.response.GetChargingProfilesResponse;

/** Charging Station handler of {@link GetChargingProfilesRequest} */
public interface IClientGetChargingProfilesRequestHandler {
    GetChargingProfilesResponse handleGetChargingProfilesRequest(GetChargingProfilesRequest request);
}
