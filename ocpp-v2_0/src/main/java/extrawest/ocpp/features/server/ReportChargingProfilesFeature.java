package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerReportChargingProfilesRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ReportChargingProfilesRequest;
import extrawest.ocpp.model.response.ReportChargingProfilesResponse;

import java.util.UUID;

public class ReportChargingProfilesFeature implements Feature {
    private final IServerReportChargingProfilesRequestHandler handler;

    public ReportChargingProfilesFeature(IServerReportChargingProfilesRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleReportChargingProfilesRequest(sessionIndex, (ReportChargingProfilesRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ReportChargingProfilesRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ReportChargingProfilesResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.REPORT_CHARGING_PROFILES.value();
    }
}
