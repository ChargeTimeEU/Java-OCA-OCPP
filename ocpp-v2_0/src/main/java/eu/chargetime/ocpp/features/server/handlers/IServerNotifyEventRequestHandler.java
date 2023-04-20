package eu.chargetime.ocpp.features.server.handlers;

import eu.chargetime.ocpp.model.request.NotifyEventRequest;
import eu.chargetime.ocpp.model.response.NotifyEventResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyEventRequest}s. */
public interface IServerNotifyEventRequestHandler {
    NotifyEventResponse handleNotifyEventRequest(
            UUID sessionIndex, NotifyEventRequest request);
}
