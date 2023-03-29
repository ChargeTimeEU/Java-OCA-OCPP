package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.GetReportRequest;
import eu.chargetime.ocpp.model.response.GetReportResponse;

/** Charging Station handler of {@link GetReportRequest} */
public interface IClientGetReportRequestHandler {
    GetReportResponse handleGetReportRequest(GetReportRequest request);
}
