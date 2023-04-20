package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.GetBaseReportRequest;
import eu.chargetime.ocpp.model.response.GetBaseReportResponse;

/** Charging Station handler of {@link GetBaseReportRequest} */
public interface IClientGetBaseReportRequestHandler {
    GetBaseReportResponse handleGetBaseReportRequest(GetBaseReportRequest request);
}
