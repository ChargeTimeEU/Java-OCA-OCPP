package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientUnlockConnectorRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.UnlockConnectorRequest;
import eu.chargetime.ocpp.model.response.UnlockConnectorResponse;

import java.util.UUID;

public class UnlockConnectorFeature implements Feature {
    private final IClientUnlockConnectorRequestHandler handler;

    public UnlockConnectorFeature(IClientUnlockConnectorRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleUnlockConnectorRequest((UnlockConnectorRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return UnlockConnectorRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return UnlockConnectorResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.UNLOCK_CONNECTOR.value();
    }
}
