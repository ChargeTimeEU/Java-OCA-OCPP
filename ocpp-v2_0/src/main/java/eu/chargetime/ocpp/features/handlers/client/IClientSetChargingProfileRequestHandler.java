package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.SendLocalListRequest;
import eu.chargetime.ocpp.model.request.SetChargingProfileRequest;
import eu.chargetime.ocpp.model.response.SetChargingProfileResponse;

/** Charging Station handler of {@link SendLocalListRequest} */
public interface IClientSetChargingProfileRequestHandler {
    SetChargingProfileResponse handleSetChargingProfileRequest(SetChargingProfileRequest request);
}
