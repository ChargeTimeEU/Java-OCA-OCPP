package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientTriggerMessageRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.TriggerMessageRequest;
import eu.chargetime.ocpp.model.response.TriggerMessageResponse;

import java.util.UUID;

public class TriggerMessageFeature implements Feature {
    private final IClientTriggerMessageRequestHandler handler;

    public TriggerMessageFeature(IClientTriggerMessageRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleTriggerMessageRequest((TriggerMessageRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return TriggerMessageRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return TriggerMessageResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.TRIGGER_MESSAGE.value();
    }
}
