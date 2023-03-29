package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.ResetRequest;
import eu.chargetime.ocpp.model.response.ResetResponse;

/** Charging Station handler of {@link ResetRequest} */
public interface IClientResetRequestHandler {
    ResetResponse handleResetRequest(ResetRequest request);
}
