package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.ClearChargingProfileRequest;
import extrawest.ocpp.model.response.ClearChargingProfileResponse;

/** Charging Station handler of {@link ClearChargingProfileRequest} */
public interface IClientClearChargingProfileRequestHandler {
    ClearChargingProfileResponse handleClearChargingProfileRequest(ClearChargingProfileRequest request);
}
