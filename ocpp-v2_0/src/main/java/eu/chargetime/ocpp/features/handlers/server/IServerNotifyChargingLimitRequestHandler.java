package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.NotifyChargingLimitRequest;
import eu.chargetime.ocpp.model.response.NotifyChargingLimitResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyChargingLimitRequest}s. */
public interface IServerNotifyChargingLimitRequestHandler {
    NotifyChargingLimitResponse handleMeterValuesRequest(
            UUID sessionIndex, NotifyChargingLimitRequest request);
}
