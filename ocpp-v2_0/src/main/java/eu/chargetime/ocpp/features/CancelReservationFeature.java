package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientCancelReservationRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.CancelReservationRequest;
import eu.chargetime.ocpp.model.response.CancelReservationResponse;

import java.util.UUID;

public class CancelReservationFeature implements Feature {
    private final IClientCancelReservationRequestHandler handler;

    public CancelReservationFeature(IClientCancelReservationRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleCancelReservationRequest((CancelReservationRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return CancelReservationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CancelReservationResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.CANCEL_RESERVATION.value();
    }
}
