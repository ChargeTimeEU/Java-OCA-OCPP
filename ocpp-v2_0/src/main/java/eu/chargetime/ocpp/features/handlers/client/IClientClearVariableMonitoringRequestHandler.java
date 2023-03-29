package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.ClearVariableMonitoringRequest;
import eu.chargetime.ocpp.model.response.ClearVariableMonitoringResponse;

/** Charging Station handler of {@link ClearVariableMonitoringRequest} */
public interface IClientClearVariableMonitoringRequestHandler {
    ClearVariableMonitoringResponse handleClearVariableMonitoringRequest(ClearVariableMonitoringRequest request);
}
