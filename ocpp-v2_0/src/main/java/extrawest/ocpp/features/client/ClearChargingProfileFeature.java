package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientClearChargingProfileRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ClearChargingProfileRequest;
import extrawest.ocpp.model.response.ClearChargingProfileResponse;

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
