package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientClearCacheRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ClearCacheRequest;
import eu.chargetime.ocpp.model.response.ClearCacheResponse;

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
