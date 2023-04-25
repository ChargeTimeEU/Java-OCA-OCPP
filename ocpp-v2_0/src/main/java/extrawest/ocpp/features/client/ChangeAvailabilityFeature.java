package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientChangeAvailabilityRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ChangeAvailabilityRequest;
import extrawest.ocpp.model.response.ChangeAvailabilityResponse;

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
