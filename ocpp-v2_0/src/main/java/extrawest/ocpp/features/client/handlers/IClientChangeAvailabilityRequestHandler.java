package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.ChangeAvailabilityRequest;
import extrawest.ocpp.model.response.ChangeAvailabilityResponse;

/** Charging Station handler of {@link ChangeAvailabilityRequest} */
public interface IClientChangeAvailabilityRequestHandler {
    ChangeAvailabilityResponse handleChangeAvailabilityRequest(ChangeAvailabilityRequest request);
}
