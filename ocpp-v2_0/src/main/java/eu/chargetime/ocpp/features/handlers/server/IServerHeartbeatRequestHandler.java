package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.HeartbeatRequest;
import eu.chargetime.ocpp.model.response.HeartbeatResponse;

import java.util.UUID;

/** Central system handler of {@link HeartbeatRequest}s. */
public interface IServerHeartbeatRequestHandler {
    HeartbeatResponse handleHeartbeatRequest(
            UUID sessionIndex, HeartbeatRequest request);
}
