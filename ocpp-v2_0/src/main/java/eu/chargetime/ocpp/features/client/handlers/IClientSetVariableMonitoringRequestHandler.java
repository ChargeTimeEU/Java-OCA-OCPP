package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.SetVariableMonitoringRequest;
import eu.chargetime.ocpp.model.response.SetVariableMonitoringResponse;

/** Charging Station handler of {@link SetVariableMonitoringRequest} */
public interface IClientSetVariableMonitoringRequestHandler {
    SetVariableMonitoringResponse handleSetVariableMonitoringRequest(SetVariableMonitoringRequest request);
}
