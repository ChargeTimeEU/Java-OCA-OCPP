package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientClearCacheRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ClearCacheRequest;
import extrawest.ocpp.model.response.ClearCacheResponse;

import java.util.UUID;

public class ClearCacheFeature implements Feature {
    private final IClientClearCacheRequestHandler handler;

    public ClearCacheFeature(IClientClearCacheRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleClearCacheRequest((ClearCacheRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ClearCacheRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearCacheResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.CLEAR_CACHE.value();
    }
}
