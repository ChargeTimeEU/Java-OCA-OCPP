package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.AuthorizeRequest;
import extrawest.ocpp.model.request.LogStatusNotificationRequest;
import extrawest.ocpp.model.response.LogStatusNotificationResponse;

import java.util.UUID;

/** Central system handler of {@link AuthorizeRequest}s. */
public interface IServerLogStatusNotificationRequestHandler {
    LogStatusNotificationResponse handleLogStatusNotificationRequest(
            UUID sessionIndex, LogStatusNotificationRequest request);
}
