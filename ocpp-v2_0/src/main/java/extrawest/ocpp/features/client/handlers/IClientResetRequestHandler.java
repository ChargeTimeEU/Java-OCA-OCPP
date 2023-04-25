package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.ResetRequest;
import extrawest.ocpp.model.response.ResetResponse;

/** Charging Station handler of {@link ResetRequest} */
public interface IClientResetRequestHandler {
    ResetResponse handleResetRequest(ResetRequest request);
}
