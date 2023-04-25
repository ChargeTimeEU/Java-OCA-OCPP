package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientSetNetworkProfileRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.SetNetworkProfileRequest;
import extrawest.ocpp.model.response.SetNetworkProfileResponse;

import java.util.UUID;

public class SetNetworkProfileFeature implements Feature {
    private final IClientSetNetworkProfileRequestHandler handler;

    public SetNetworkProfileFeature(IClientSetNetworkProfileRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleSetNetworkProfileRequest((SetNetworkProfileRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return SetNetworkProfileRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetNetworkProfileResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.SET_NETWORK_PROFILE.value();
    }
}
