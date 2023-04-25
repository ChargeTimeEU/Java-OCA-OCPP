package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.SetMonitoringBaseRequest;
import extrawest.ocpp.model.response.SetMonitoringBaseResponse;

/** Charging Station handler of {@link SetMonitoringBaseRequest} */
public interface IClientSetMonitoringBaseRequestHandler {
    SetMonitoringBaseResponse handleSetMonitoringBaseRequest(SetMonitoringBaseRequest request);
}
