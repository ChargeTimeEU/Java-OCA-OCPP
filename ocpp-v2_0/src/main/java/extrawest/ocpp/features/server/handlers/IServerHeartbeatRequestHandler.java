package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.HeartbeatRequest;
import extrawest.ocpp.model.response.HeartbeatResponse;

import java.util.UUID;

/** Central system handler of {@link HeartbeatRequest}s. */
public interface IServerHeartbeatRequestHandler {
    HeartbeatResponse handleHeartbeatRequest(
            UUID sessionIndex, HeartbeatRequest request);
}
