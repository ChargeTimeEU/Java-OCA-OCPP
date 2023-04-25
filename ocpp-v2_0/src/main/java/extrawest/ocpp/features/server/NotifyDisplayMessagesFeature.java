package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerNotifyDisplayMessagesRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.NotifyDisplayMessagesRequest;
import extrawest.ocpp.model.response.NotifyDisplayMessagesResponse;

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
