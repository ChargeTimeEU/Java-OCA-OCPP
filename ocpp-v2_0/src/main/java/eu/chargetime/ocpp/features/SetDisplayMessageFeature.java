package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientSetDisplayMessageRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.SetDisplayMessageRequest;
import eu.chargetime.ocpp.model.response.SetDisplayMessageResponse;

import java.util.UUID;

public class SetDisplayMessageFeature implements Feature {
    private final IClientSetDisplayMessageRequestHandler handler;

    public SetDisplayMessageFeature(IClientSetDisplayMessageRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleSetDisplayMessageRequest((SetDisplayMessageRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return SetDisplayMessageRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetDisplayMessageResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.SET_DISPLAY_MESSAGE.value();
    }
}
