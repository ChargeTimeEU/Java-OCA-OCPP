package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetChargingProfilesRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetChargingProfilesRequest;
import eu.chargetime.ocpp.model.response.GetChargingProfilesResponse;

import java.util.UUID;

public class GetChargingProfilesFeature implements Feature {
    private final IClientGetChargingProfilesRequestHandler handler;

    public GetChargingProfilesFeature(IClientGetChargingProfilesRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetChargingProfilesRequest((GetChargingProfilesRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetChargingProfilesRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetChargingProfilesResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_CHARGING_PROFILES.value();
    }
}
