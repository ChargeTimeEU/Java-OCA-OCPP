package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetLogRequest;
import extrawest.ocpp.model.response.GetLogResponse;

/** Charging Station handler of {@link GetLogRequest} */
public interface IClientGetLogRequestHandler {
    GetLogResponse handleGetLogRequest(GetLogRequest request);
}
