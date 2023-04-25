package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetBaseReportRequest;
import extrawest.ocpp.model.response.GetBaseReportResponse;

/** Charging Station handler of {@link GetBaseReportRequest} */
public interface IClientGetBaseReportRequestHandler {
    GetBaseReportResponse handleGetBaseReportRequest(GetBaseReportRequest request);
}
