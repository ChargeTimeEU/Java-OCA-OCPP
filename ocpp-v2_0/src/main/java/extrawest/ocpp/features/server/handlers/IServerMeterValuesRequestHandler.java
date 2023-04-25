package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.MeterValuesRequest;
import extrawest.ocpp.model.response.MeterValuesResponse;

import java.util.UUID;

/** Central system handler of {@link MeterValuesRequest}s. */
public interface IServerMeterValuesRequestHandler {
    MeterValuesResponse handleMeterValuesRequest(
            UUID sessionIndex, MeterValuesRequest request);
}
