package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetBaseReportRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetBaseReportRequest;
import eu.chargetime.ocpp.model.response.GetBaseReportResponse;

import java.util.UUID;

public class GetBaseReportFeature implements Feature {
    private final IClientGetBaseReportRequestHandler handler;

    public GetBaseReportFeature(IClientGetBaseReportRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetBaseReportRequest((GetBaseReportRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetBaseReportRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetBaseReportResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_BASE_REPORT.value();
    }
}
