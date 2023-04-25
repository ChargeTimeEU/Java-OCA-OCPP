package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerNotifyMonitoringReportRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.NotifyMonitoringReportRequest;
import extrawest.ocpp.model.response.NotifyMonitoringReportResponse;

import java.util.UUID;

public class NotifyMonitoringReportFeature implements Feature {
    private final IServerNotifyMonitoringReportRequestHandler handler;

    public NotifyMonitoringReportFeature(IServerNotifyMonitoringReportRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleNotifyMonitoringReportRequest(sessionIndex, (NotifyMonitoringReportRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyMonitoringReportRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyMonitoringReportResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.NOTIFY_MONITORING_REPORT.value();
    }
}
