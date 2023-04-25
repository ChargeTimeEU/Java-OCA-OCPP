package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.ReportChargingProfilesRequest;
import extrawest.ocpp.model.response.ReportChargingProfilesResponse;

import java.util.UUID;

/** Central system handler of {@link ReportChargingProfilesRequest}s. */
public interface IServerReportChargingProfilesRequestHandler {
    ReportChargingProfilesResponse handleReportChargingProfilesRequest(
            UUID sessionIndex, ReportChargingProfilesRequest request);
}
