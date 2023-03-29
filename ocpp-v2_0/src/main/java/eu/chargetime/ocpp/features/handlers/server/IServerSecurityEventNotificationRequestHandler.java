package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.SecurityEventNotificationRequest;
import eu.chargetime.ocpp.model.response.SecurityEventNotificationResponse;

import java.util.UUID;

/** Central system handler of {@link SecurityEventNotificationRequest}s. */
public interface IServerSecurityEventNotificationRequestHandler {
    SecurityEventNotificationResponse handleSecurityEventNotificationRequest(
            UUID sessionIndex, SecurityEventNotificationRequest request);
}
