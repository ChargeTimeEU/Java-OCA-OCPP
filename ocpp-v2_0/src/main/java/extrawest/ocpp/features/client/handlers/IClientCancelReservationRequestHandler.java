package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.CancelReservationRequest;
import extrawest.ocpp.model.response.CancelReservationResponse;

/** Charging Station handler of {@link CancelReservationRequest} */
public interface IClientCancelReservationRequestHandler {
    CancelReservationResponse handleCancelReservationRequest(CancelReservationRequest request);
}
