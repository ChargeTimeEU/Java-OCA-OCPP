package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerLogStatusNotificationRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.LogStatusNotificationRequest;
import extrawest.ocpp.model.response.LogStatusNotificationResponse;

import java.util.UUID;

public class LogStatusNotificationFeature implements Feature {
    private final IServerLogStatusNotificationRequestHandler handler;

    public LogStatusNotificationFeature(IServerLogStatusNotificationRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleLogStatusNotificationRequest(sessionIndex, (LogStatusNotificationRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return LogStatusNotificationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return LogStatusNotificationResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.LOG_STATUS_NOTIFICATION.value();
    }
}
