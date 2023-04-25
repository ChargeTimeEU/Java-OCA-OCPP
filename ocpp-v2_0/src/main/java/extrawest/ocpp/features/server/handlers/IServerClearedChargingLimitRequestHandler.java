package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.ClearedChargingLimitRequest;
import extrawest.ocpp.model.response.ClearedChargingLimitResponse;

import java.util.UUID;

/** Central system handler of {@link ClearedChargingLimitRequest}s. */
public interface IServerClearedChargingLimitRequestHandler {
    ClearedChargingLimitResponse handleClearedChargingLimitRequest(
            UUID sessionIndex, ClearedChargingLimitRequest request);
}
