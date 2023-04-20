package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.ClearVariableMonitoringRequest;
import eu.chargetime.ocpp.model.response.ClearVariableMonitoringResponse;

/** Charging Station handler of {@link ClearVariableMonitoringRequest} */
public interface IClientClearVariableMonitoringRequestHandler {
    ClearVariableMonitoringResponse handleClearVariableMonitoringRequest(ClearVariableMonitoringRequest request);
}
