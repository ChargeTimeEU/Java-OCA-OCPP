package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientResetRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ResetRequest;
import eu.chargetime.ocpp.model.response.ResetResponse;

import java.util.UUID;

public class ResetFeature implements Feature {
    private final IClientResetRequestHandler handler;

    public ResetFeature(IClientResetRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleResetRequest((ResetRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ResetRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ResetResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.RESET.value();
    }
}
