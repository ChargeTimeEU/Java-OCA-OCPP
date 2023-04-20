package eu.chargetime.ocpp.features.server.handlers;

import eu.chargetime.ocpp.model.request.NotifyChargingLimitRequest;
import eu.chargetime.ocpp.model.response.NotifyChargingLimitResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyChargingLimitRequest}s. */
public interface IServerNotifyChargingLimitRequestHandler {
    NotifyChargingLimitResponse handleNotifyChargingLimitRequest(
            UUID sessionIndex, NotifyChargingLimitRequest request);
}
