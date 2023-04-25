package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetDisplayMessagesRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetDisplayMessagesRequest;
import extrawest.ocpp.model.response.GetDisplayMessagesResponse;

import java.util.UUID;

public class GetDisplayMessagesFeature implements Feature {
    private final IClientGetDisplayMessagesRequestHandler handler;

    public GetDisplayMessagesFeature(IClientGetDisplayMessagesRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetDisplayMessagesRequest((GetDisplayMessagesRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetDisplayMessagesRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetDisplayMessagesResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_DISPLAY_MESSAGES.value();
    }
}
