package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientClearChargingProfileRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ClearChargingProfileRequest;
import eu.chargetime.ocpp.model.response.ClearChargingProfileResponse;

import java.util.UUID;

public class ClearChargingProfileFeature implements Feature {
    private final IClientClearChargingProfileRequestHandler handler;

    public ClearChargingProfileFeature(IClientClearChargingProfileRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleClearChargingProfileRequest((ClearChargingProfileRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ClearChargingProfileRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearChargingProfileResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.CLEAR_CHARGING_PROFILE.value();
    }
}
