package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerSecurityEventNotificationRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.SecurityEventNotificationRequest;
import extrawest.ocpp.model.response.SecurityEventNotificationResponse;

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
