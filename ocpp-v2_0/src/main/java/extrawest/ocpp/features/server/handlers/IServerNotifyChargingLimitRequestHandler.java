package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.NotifyChargingLimitRequest;
import extrawest.ocpp.model.response.NotifyChargingLimitResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyChargingLimitRequest}s. */
public interface IServerNotifyChargingLimitRequestHandler {
    NotifyChargingLimitResponse handleNotifyChargingLimitRequest(
            UUID sessionIndex, NotifyChargingLimitRequest request);
}
