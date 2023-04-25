package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.SendLocalListRequest;
import extrawest.ocpp.model.request.SetChargingProfileRequest;
import extrawest.ocpp.model.response.SetChargingProfileResponse;

/** Charging Station handler of {@link SendLocalListRequest} */
public interface IClientSetChargingProfileRequestHandler {
    SetChargingProfileResponse handleSetChargingProfileRequest(SetChargingProfileRequest request);
}
