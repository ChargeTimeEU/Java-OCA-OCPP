package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerReportChargingProfilesRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ReportChargingProfilesRequest;
import eu.chargetime.ocpp.model.response.ReportChargingProfilesResponse;

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
