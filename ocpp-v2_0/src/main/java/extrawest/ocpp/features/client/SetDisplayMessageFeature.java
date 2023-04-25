package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientSetDisplayMessageRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.SetDisplayMessageRequest;
import extrawest.ocpp.model.response.SetDisplayMessageResponse;

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
