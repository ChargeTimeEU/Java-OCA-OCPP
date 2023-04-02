package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerSecurityEventNotificationRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.SecurityEventNotificationRequest;
import eu.chargetime.ocpp.model.response.SecurityEventNotificationResponse;

import java.util.UUID;

public class SecurityEventNotificationFeature implements Feature {
    private final IServerSecurityEventNotificationRequestHandler handler;

    public SecurityEventNotificationFeature(IServerSecurityEventNotificationRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleSecurityEventNotificationRequest(sessionIndex, (SecurityEventNotificationRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return SecurityEventNotificationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SecurityEventNotificationResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.SECURITY_EVENT_NOTIFICATION.value();
    }
}
