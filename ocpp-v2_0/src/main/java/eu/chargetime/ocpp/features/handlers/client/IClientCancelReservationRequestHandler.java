package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.CancelReservationRequest;
import eu.chargetime.ocpp.model.response.CancelReservationResponse;

/** Charging Station handler of {@link CancelReservationRequest} */
public interface IClientCancelReservationRequestHandler {
    CancelReservationResponse handleCancelReservationRequest(CancelReservationRequest request);
}
