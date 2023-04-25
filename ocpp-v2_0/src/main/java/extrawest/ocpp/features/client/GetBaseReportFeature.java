package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetBaseReportRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetBaseReportRequest;
import extrawest.ocpp.model.response.GetBaseReportResponse;

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
