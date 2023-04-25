package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.ClearVariableMonitoringRequest;
import extrawest.ocpp.model.response.ClearVariableMonitoringResponse;

/** Charging Station handler of {@link ClearVariableMonitoringRequest} */
public interface IClientClearVariableMonitoringRequestHandler {
    ClearVariableMonitoringResponse handleClearVariableMonitoringRequest(ClearVariableMonitoringRequest request);
}
