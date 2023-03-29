package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.GetLogRequest;
import eu.chargetime.ocpp.model.response.GetLogResponse;

/** Charging Station handler of {@link GetLogRequest} */
public interface IClientGetLogRequestHandler {
    GetLogResponse handleGetLogRequest(GetLogRequest request);
}
