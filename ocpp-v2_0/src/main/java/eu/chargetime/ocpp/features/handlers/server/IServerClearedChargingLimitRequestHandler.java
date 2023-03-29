package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.ClearedChargingLimitRequest;
import eu.chargetime.ocpp.model.response.ClearedChargingLimitResponse;

import java.util.UUID;

/** Central system handler of {@link ClearedChargingLimitRequest}s. */
public interface IServerClearedChargingLimitRequestHandler {
    ClearedChargingLimitResponse handleClearedChargingLimitRequest(
            UUID sessionIndex, ClearedChargingLimitRequest request);
}
