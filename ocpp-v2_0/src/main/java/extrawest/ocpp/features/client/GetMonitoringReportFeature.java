package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetMonitoringReportRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetMonitoringReportRequest;
import extrawest.ocpp.model.response.GetMonitoringReportResponse;

import java.util.UUID;

public class GetMonitoringReportFeature implements Feature {
    private final IClientGetMonitoringReportRequestHandler handler;

    public GetMonitoringReportFeature(IClientGetMonitoringReportRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetMonitoringReportRequest((GetMonitoringReportRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetMonitoringReportRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetMonitoringReportResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_MONITORING_REPORT.value();
    }
}
