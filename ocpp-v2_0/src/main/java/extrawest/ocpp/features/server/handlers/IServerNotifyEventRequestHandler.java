package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.NotifyEventRequest;
import extrawest.ocpp.model.response.NotifyEventResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyEventRequest}s. */
public interface IServerNotifyEventRequestHandler {
    NotifyEventResponse handleNotifyEventRequest(
            UUID sessionIndex, NotifyEventRequest request);
}
