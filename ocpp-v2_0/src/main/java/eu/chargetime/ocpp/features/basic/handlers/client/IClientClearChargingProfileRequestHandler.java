package eu.chargetime.ocpp.features.basic.handlers.client;

import eu.chargetime.ocpp.model.request.ClearChargingProfileRequest;
import eu.chargetime.ocpp.model.response.ClearChargingProfileResponse;

/** Charging Station handler of {@link ClearChargingProfileRequest} */
public interface IClientClearChargingProfileRequestHandler {
    ClearChargingProfileResponse handleGetVariablesRequest(ClearChargingProfileRequest request);
}
