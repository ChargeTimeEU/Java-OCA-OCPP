package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.SecurityEventNotificationRequest;
import extrawest.ocpp.model.response.SecurityEventNotificationResponse;

import java.util.UUID;

/** Central system handler of {@link SecurityEventNotificationRequest}s. */
public interface IServerSecurityEventNotificationRequestHandler {
    SecurityEventNotificationResponse handleSecurityEventNotificationRequest(
            UUID sessionIndex, SecurityEventNotificationRequest request);
}
