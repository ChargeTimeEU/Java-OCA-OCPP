package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientResetRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ResetRequest;
import extrawest.ocpp.model.response.ResetResponse;

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
