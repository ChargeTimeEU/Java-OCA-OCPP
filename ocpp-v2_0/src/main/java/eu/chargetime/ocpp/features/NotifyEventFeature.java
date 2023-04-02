package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerNotifyEventRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.NotifyEventRequest;
import eu.chargetime.ocpp.model.response.NotifyEventResponse;

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
