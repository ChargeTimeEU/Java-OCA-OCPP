package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.ReserveNowRequest;
import extrawest.ocpp.model.response.ReserveNowResponse;

/** Charging Station handler of {@link ReserveNowRequest} */
public interface IClientReserveNowRequestHandler {
    ReserveNowResponse handleReserveNowRequest(ReserveNowRequest request);
}
