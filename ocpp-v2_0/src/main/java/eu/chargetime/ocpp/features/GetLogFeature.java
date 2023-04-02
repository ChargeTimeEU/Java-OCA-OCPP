package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetLogRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetLogRequest;
import eu.chargetime.ocpp.model.response.GetLogResponse;

import java.util.UUID;

public class GetLogFeature implements Feature {
    private final IClientGetLogRequestHandler handler;

    public GetLogFeature(IClientGetLogRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetLogRequest((GetLogRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetLogRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetLogResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_LOG.value();
    }
}
