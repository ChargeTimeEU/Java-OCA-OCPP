package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.SetMonitoringBaseRequest;
import eu.chargetime.ocpp.model.response.SetMonitoringBaseResponse;

/** Charging Station handler of {@link SetMonitoringBaseRequest} */
public interface IClientSetMonitoringBaseRequestHandler {
    SetMonitoringBaseResponse handleSetMonitoringBaseRequest(SetMonitoringBaseRequest request);
}
