package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.UnlockConnectorRequest;
import extrawest.ocpp.model.response.UnlockConnectorResponse;

/** Charging Station handler of {@link UnlockConnectorRequest} */
public interface IClientUnlockConnectorRequestHandler {
    UnlockConnectorResponse handleUnlockConnectorRequest(UnlockConnectorRequest request);
}
