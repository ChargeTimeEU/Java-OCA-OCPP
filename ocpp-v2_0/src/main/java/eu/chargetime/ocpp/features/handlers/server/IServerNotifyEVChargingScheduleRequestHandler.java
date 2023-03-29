package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.NotifyEVChargingScheduleRequest;
import eu.chargetime.ocpp.model.response.NotifyEVChargingScheduleResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyEVChargingScheduleRequest}s. */
public interface IServerNotifyEVChargingScheduleRequestHandler {
    NotifyEVChargingScheduleResponse handleNotifyEVChargingScheduleRequest(
            UUID sessionIndex, NotifyEVChargingScheduleRequest request);
}
