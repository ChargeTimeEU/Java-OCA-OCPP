package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientSetChargingProfileRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.SetChargingProfileRequest;
import extrawest.ocpp.model.response.SetChargingProfileResponse;

import java.util.UUID;

public class SetChargingProfileFeature implements Feature {
    private final IClientSetChargingProfileRequestHandler handler;

    public SetChargingProfileFeature(IClientSetChargingProfileRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleSetChargingProfileRequest((SetChargingProfileRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return SetChargingProfileRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetChargingProfileResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.SET_CHARGING_PROFILE.value();
    }
}
