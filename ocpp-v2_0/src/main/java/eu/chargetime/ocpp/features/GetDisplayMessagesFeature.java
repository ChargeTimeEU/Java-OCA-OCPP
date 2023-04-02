package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetDisplayMessagesRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetDisplayMessagesRequest;
import eu.chargetime.ocpp.model.response.GetDisplayMessagesResponse;

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
