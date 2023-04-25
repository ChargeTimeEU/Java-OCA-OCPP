package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.SetVariableMonitoringRequest;
import extrawest.ocpp.model.response.SetVariableMonitoringResponse;

/** Charging Station handler of {@link SetVariableMonitoringRequest} */
public interface IClientSetVariableMonitoringRequestHandler {
    SetVariableMonitoringResponse handleSetVariableMonitoringRequest(SetVariableMonitoringRequest request);
}
