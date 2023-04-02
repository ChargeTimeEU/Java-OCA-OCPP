package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetMonitoringReportRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetMonitoringReportRequest;
import eu.chargetime.ocpp.model.response.GetMonitoringReportResponse;

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
