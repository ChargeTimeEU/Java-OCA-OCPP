package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetChargingProfilesRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetChargingProfilesRequest;
import extrawest.ocpp.model.response.GetChargingProfilesResponse;

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
