package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.NotifyReportRequest;
import extrawest.ocpp.model.response.NotifyReportResponse;

import java.util.UUID;

/** Central system handler of {@link NotifyReportRequest}s. */
public interface IServerNotifyReportRequestHandler {
    NotifyReportResponse handleNotifyReportRequest(
            UUID sessionIndex, NotifyReportRequest request);
}
