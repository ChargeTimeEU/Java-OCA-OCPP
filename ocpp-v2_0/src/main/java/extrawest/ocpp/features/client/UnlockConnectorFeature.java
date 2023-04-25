package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientUnlockConnectorRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.UnlockConnectorRequest;
import extrawest.ocpp.model.response.UnlockConnectorResponse;

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
