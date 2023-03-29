package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.ReportChargingProfilesRequest;
import eu.chargetime.ocpp.model.response.ReportChargingProfilesResponse;

import java.util.UUID;

/** Central system handler of {@link ReportChargingProfilesRequest}s. */
public interface IServerReportChargingProfilesRequestHandler {
    ReportChargingProfilesResponse handleReportChargingProfilesRequest(
            UUID sessionIndex, ReportChargingProfilesRequest request);
}
