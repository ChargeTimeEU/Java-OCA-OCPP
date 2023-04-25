package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetReportRequest;
import extrawest.ocpp.model.response.GetReportResponse;

/** Charging Station handler of {@link GetReportRequest} */
public interface IClientGetReportRequestHandler {
    GetReportResponse handleGetReportRequest(GetReportRequest request);
}
