package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.NotifyEVChargingScheduleRequest;
import extrawest.ocpp.model.response.NotifyEVChargingScheduleResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyEVChargingScheduleRequest}s. */
public interface IServerNotifyEVChargingScheduleRequestHandler {
    NotifyEVChargingScheduleResponse handleNotifyEVChargingScheduleRequest(
            UUID sessionIndex, NotifyEVChargingScheduleRequest request);
}
