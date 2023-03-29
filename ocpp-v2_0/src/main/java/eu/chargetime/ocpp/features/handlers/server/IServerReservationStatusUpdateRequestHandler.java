package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.ReservationStatusUpdateRequest;
import eu.chargetime.ocpp.model.response.ReservationStatusUpdateResponse;

import java.util.UUID;

/** Central system handler of {@link ReservationStatusUpdateRequest}s. */
public interface IServerReservationStatusUpdateRequestHandler {
    ReservationStatusUpdateResponse handleReservationStatusUpdateRequest(
            UUID sessionIndex, ReservationStatusUpdateRequest request);
}
