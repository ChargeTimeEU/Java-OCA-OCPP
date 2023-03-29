package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.NotifyDisplayMessagesRequest;
import eu.chargetime.ocpp.model.response.NotifyDisplayMessagesResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyDisplayMessagesRequest}s. */
public interface IServerNotifyDisplayMessagesRequestHandler {
    NotifyDisplayMessagesResponse handleNotifyDisplayMessagesRequest(
            UUID sessionIndex, NotifyDisplayMessagesRequest request);
}
