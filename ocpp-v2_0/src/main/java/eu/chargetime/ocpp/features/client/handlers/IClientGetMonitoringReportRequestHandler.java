package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.GetMonitoringReportRequest;
import eu.chargetime.ocpp.model.response.GetMonitoringReportResponse;

/** Charging Station handler of {@link GetMonitoringReportRequest} */
public interface IClientGetMonitoringReportRequestHandler {
    GetMonitoringReportResponse handleGetMonitoringReportRequest(GetMonitoringReportRequest request);
}
