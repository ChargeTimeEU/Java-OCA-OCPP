package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.NotifyMonitoringReportRequest;
import extrawest.ocpp.model.response.NotifyMonitoringReportResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyMonitoringReportRequest}s. */
public interface IServerNotifyMonitoringReportRequestHandler {
    NotifyMonitoringReportResponse handleNotifyMonitoringReportRequest(
            UUID sessionIndex, NotifyMonitoringReportRequest request);
}
