package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.NotifyDisplayMessagesRequest;
import extrawest.ocpp.model.response.NotifyDisplayMessagesResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyDisplayMessagesRequest}s. */
public interface IServerNotifyDisplayMessagesRequestHandler {
    NotifyDisplayMessagesResponse handleNotifyDisplayMessagesRequest(
            UUID sessionIndex, NotifyDisplayMessagesRequest request);
}
