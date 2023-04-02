package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerLogStatusNotificationRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.LogStatusNotificationRequest;
import eu.chargetime.ocpp.model.response.LogStatusNotificationResponse;

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
