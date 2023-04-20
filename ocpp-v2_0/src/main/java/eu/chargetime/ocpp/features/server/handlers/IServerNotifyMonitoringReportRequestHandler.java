package eu.chargetime.ocpp.features.server.handlers;

import eu.chargetime.ocpp.model.request.NotifyMonitoringReportRequest;
import eu.chargetime.ocpp.model.response.NotifyMonitoringReportResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyMonitoringReportRequest}s. */
public interface IServerNotifyMonitoringReportRequestHandler {
    NotifyMonitoringReportResponse handleNotifyMonitoringReportRequest(
            UUID sessionIndex, NotifyMonitoringReportRequest request);
}
