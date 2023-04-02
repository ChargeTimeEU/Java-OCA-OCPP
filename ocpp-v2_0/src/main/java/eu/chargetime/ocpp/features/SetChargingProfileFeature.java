package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientSetChargingProfileRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.SetChargingProfileRequest;
import eu.chargetime.ocpp.model.response.SetChargingProfileResponse;

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
