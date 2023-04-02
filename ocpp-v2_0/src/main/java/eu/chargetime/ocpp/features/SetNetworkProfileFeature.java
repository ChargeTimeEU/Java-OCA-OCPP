package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientSetNetworkProfileRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.SetNetworkProfileRequest;
import eu.chargetime.ocpp.model.response.SetNetworkProfileResponse;

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
