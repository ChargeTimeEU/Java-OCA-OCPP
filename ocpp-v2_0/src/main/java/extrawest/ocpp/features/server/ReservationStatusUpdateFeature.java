package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerReservationStatusUpdateRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ReservationStatusUpdateRequest;
import extrawest.ocpp.model.response.ReservationStatusUpdateResponse;

import java.util.UUID;

public class ReservationStatusUpdateFeature implements Feature {
    private final IServerReservationStatusUpdateRequestHandler handler;

    public ReservationStatusUpdateFeature(IServerReservationStatusUpdateRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleReservationStatusUpdateRequest(sessionIndex, (ReservationStatusUpdateRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ReservationStatusUpdateRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ReservationStatusUpdateResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.RESERVATION_STATUS_UPDATE.value();
    }
}
