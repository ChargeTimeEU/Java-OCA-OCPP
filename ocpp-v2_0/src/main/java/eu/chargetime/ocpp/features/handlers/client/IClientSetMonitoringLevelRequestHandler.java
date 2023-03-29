package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.SetMonitoringLevelRequest;
import eu.chargetime.ocpp.model.response.SetMonitoringLevelResponse;

/** Charging Station handler of {@link SetMonitoringLevelRequest} */
public interface IClientSetMonitoringLevelRequestHandler {
    SetMonitoringLevelResponse handleSetMonitoringLevelRequest(SetMonitoringLevelRequest request);
}
