package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.ReserveNowRequest;
import eu.chargetime.ocpp.model.response.ReserveNowResponse;

/** Charging Station handler of {@link ReserveNowRequest} */
public interface IClientReserveNowRequestHandler {
    ReserveNowResponse handleReserveNowRequest(ReserveNowRequest request);
}
