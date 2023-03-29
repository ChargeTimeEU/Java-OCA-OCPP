package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.AuthorizeRequest;
import eu.chargetime.ocpp.model.request.LogStatusNotificationRequest;
import eu.chargetime.ocpp.model.response.LogStatusNotificationResponse;

import java.util.UUID;

/** Central system handler of {@link AuthorizeRequest}s. */
public interface IServerLogStatusNotificationRequestHandler {
    LogStatusNotificationResponse handleLogStatusNotificationRequest(
            UUID sessionIndex, LogStatusNotificationRequest request);
}
