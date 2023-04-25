package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.NotifyEVChargingNeedsRequest;
import extrawest.ocpp.model.response.NotifyEVChargingNeedsResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyEVChargingNeedsRequest}s. */
public interface IServerNotifyEVChargingNeedsRequestHandler {
    NotifyEVChargingNeedsResponse handleNotifyEVChargingNeedsRequest(
            UUID sessionIndex, NotifyEVChargingNeedsRequest request);
}
