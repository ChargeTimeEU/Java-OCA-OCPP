package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.NotifyEVChargingNeedsRequest;
import eu.chargetime.ocpp.model.response.NotifyEVChargingNeedsResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyEVChargingNeedsRequest}s. */
public interface IServerNotifyEVChargingNeedsRequestHandler {
    NotifyEVChargingNeedsResponse handleNotifyEVChargingNeedsRequest(
            UUID sessionIndex, NotifyEVChargingNeedsRequest request);
}
