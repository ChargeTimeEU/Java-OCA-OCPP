package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientTriggerMessageRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.TriggerMessageRequest;
import extrawest.ocpp.model.response.TriggerMessageResponse;

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
