package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetMonitoringReportRequest;
import extrawest.ocpp.model.response.GetMonitoringReportResponse;

/** Charging Station handler of {@link GetMonitoringReportRequest} */
public interface IClientGetMonitoringReportRequestHandler {
    GetMonitoringReportResponse handleGetMonitoringReportRequest(GetMonitoringReportRequest request);
}
