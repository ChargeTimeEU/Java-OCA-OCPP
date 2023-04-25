package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientClearDisplayMessageRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ClearDisplayMessageRequest;
import extrawest.ocpp.model.response.ClearDisplayMessageResponse;

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
