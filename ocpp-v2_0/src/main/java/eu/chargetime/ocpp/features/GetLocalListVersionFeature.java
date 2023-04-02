package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetLocalListVersionRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetLocalListVersionRequest;
import eu.chargetime.ocpp.model.response.GetLocalListVersionResponse;

import java.util.UUID;

public class GetLocalListVersionFeature implements Feature {
    private final IClientGetLocalListVersionRequestHandler handler;

    public GetLocalListVersionFeature(IClientGetLocalListVersionRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetLocalListVersionRequest((GetLocalListVersionRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetLocalListVersionRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetLocalListVersionResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_LOCAL_LIST_VERSION.value();
    }
}
