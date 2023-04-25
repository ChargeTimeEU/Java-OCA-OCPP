package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.ReservationStatusUpdateRequest;
import extrawest.ocpp.model.response.ReservationStatusUpdateResponse;

import java.util.UUID;

/** Central system handler of {@link ReservationStatusUpdateRequest}s. */
public interface IServerReservationStatusUpdateRequestHandler {
    ReservationStatusUpdateResponse handleReservationStatusUpdateRequest(
            UUID sessionIndex, ReservationStatusUpdateRequest request);
}
