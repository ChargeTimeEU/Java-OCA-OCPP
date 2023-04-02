package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientChangeAvailabilityRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ChangeAvailabilityRequest;
import eu.chargetime.ocpp.model.response.ChangeAvailabilityResponse;

import java.util.UUID;

public class ChangeAvailabilityFeature implements Feature {
    private final IClientChangeAvailabilityRequestHandler handler;

    public ChangeAvailabilityFeature(IClientChangeAvailabilityRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleChangeAvailabilityRequest((ChangeAvailabilityRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ChangeAvailabilityRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ChangeAvailabilityResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.CHANGE_AVAILABILITY.value();
    }
}
