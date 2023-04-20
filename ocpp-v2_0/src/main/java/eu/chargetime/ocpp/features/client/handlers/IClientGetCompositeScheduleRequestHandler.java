package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.GetCompositeScheduleRequest;
import eu.chargetime.ocpp.model.response.GetCompositeScheduleResponse;

/** Charging Station handler of {@link GetCompositeScheduleRequest} */
public interface IClientGetCompositeScheduleRequestHandler {
    GetCompositeScheduleResponse handleGetCompositeScheduleRequest(GetCompositeScheduleRequest request);
}
