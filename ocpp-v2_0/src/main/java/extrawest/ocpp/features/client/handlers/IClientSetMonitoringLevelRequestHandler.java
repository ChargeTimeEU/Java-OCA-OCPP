package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.SetMonitoringLevelRequest;
import extrawest.ocpp.model.response.SetMonitoringLevelResponse;

/** Charging Station handler of {@link SetMonitoringLevelRequest} */
public interface IClientSetMonitoringLevelRequestHandler {
    SetMonitoringLevelResponse handleSetMonitoringLevelRequest(SetMonitoringLevelRequest request);
}
