package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerNotifyEventRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.NotifyEventRequest;
import extrawest.ocpp.model.response.NotifyEventResponse;

import java.util.UUID;

public class NotifyEventFeature implements Feature {
    private final IServerNotifyEventRequestHandler handler;

    public NotifyEventFeature(IServerNotifyEventRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleNotifyEventRequest(sessionIndex, (NotifyEventRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyEventRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyEventResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.NOTIFY_EVENT.value();
    }
}
