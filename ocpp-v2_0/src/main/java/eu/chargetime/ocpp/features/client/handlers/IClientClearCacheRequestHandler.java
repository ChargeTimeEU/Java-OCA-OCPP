package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.ClearCacheRequest;
import eu.chargetime.ocpp.model.response.ClearCacheResponse;

/** Charging Station handler of {@link ClearCacheRequest} */
public interface IClientClearCacheRequestHandler {
    ClearCacheResponse handleClearCacheRequest(ClearCacheRequest request);
}
