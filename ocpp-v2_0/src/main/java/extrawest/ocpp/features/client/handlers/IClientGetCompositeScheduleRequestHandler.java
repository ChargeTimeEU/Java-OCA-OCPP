package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetCompositeScheduleRequest;
import extrawest.ocpp.model.response.GetCompositeScheduleResponse;

/** Charging Station handler of {@link GetCompositeScheduleRequest} */
public interface IClientGetCompositeScheduleRequestHandler {
    GetCompositeScheduleResponse handleGetCompositeScheduleRequest(GetCompositeScheduleRequest request);
}
