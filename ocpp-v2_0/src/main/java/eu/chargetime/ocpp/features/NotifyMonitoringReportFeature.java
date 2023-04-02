package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerNotifyMonitoringReportRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.NotifyMonitoringReportRequest;
import eu.chargetime.ocpp.model.response.NotifyMonitoringReportResponse;

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
