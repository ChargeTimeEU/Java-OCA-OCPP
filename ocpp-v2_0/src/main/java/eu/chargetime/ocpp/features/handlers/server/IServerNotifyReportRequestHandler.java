package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.NotifyReportRequest;
import eu.chargetime.ocpp.model.response.NotifyReportResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyReportRequest}s. */
public interface IServerNotifyReportRequestHandler {
    NotifyReportResponse handleNotifyReportRequest(
            UUID sessionIndex, NotifyReportRequest request);
}
