package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetReportRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetReportRequest;
import eu.chargetime.ocpp.model.response.GetReportResponse;

import java.util.UUID;

public class GetReportFeature implements Feature {
    private final IClientGetReportRequestHandler handler;

    public GetReportFeature(IClientGetReportRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetReportRequest((GetReportRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetReportRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetReportResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_REPORT.value();
    }
}
