package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerReservationStatusUpdateRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ReservationStatusUpdateRequest;
import eu.chargetime.ocpp.model.response.ReservationStatusUpdateResponse;

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
