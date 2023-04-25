package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetReportRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetReportRequest;
import extrawest.ocpp.model.response.GetReportResponse;

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
