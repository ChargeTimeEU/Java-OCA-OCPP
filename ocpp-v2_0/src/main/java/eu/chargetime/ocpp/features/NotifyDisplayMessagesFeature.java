package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerNotifyDisplayMessagesRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.NotifyDisplayMessagesRequest;
import eu.chargetime.ocpp.model.response.NotifyDisplayMessagesResponse;

import java.util.UUID;

public class NotifyDisplayMessagesFeature implements Feature {
    private final IServerNotifyDisplayMessagesRequestHandler handler;

    public NotifyDisplayMessagesFeature(IServerNotifyDisplayMessagesRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleNotifyDisplayMessagesRequest(sessionIndex, (NotifyDisplayMessagesRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyDisplayMessagesRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyDisplayMessagesResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.NOTIFY_DISPLAY_MESSAGES.value();
    }
}
