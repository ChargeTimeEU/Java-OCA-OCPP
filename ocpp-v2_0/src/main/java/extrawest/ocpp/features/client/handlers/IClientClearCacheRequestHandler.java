package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.ClearCacheRequest;
import extrawest.ocpp.model.response.ClearCacheResponse;

/** Charging Station handler of {@link ClearCacheRequest} */
public interface IClientClearCacheRequestHandler {
    ClearCacheResponse handleClearCacheRequest(ClearCacheRequest request);
}
