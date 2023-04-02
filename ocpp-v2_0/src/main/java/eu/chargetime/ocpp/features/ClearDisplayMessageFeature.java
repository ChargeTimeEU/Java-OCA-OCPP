package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientClearDisplayMessageRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ClearDisplayMessageRequest;
import eu.chargetime.ocpp.model.response.ClearDisplayMessageResponse;

import java.util.UUID;

public class ClearDisplayMessageFeature implements Feature {
    private final IClientClearDisplayMessageRequestHandler handler;

    public ClearDisplayMessageFeature(IClientClearDisplayMessageRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleClearDisplayMessageRequest((ClearDisplayMessageRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ClearDisplayMessageRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearDisplayMessageResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.CLEAR_DISPLAY_MESSAGE.value();
    }
}
