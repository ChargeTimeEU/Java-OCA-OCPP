package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.MeterValuesRequest;
import eu.chargetime.ocpp.model.response.MeterValuesResponse;

import java.util.UUID;

/** Central system handler of {@link MeterValuesRequest}s. */
public interface IServerMeterValuesRequestHandler {
    MeterValuesResponse handleMeterValuesRequest(
            UUID sessionIndex, MeterValuesRequest request);
}
