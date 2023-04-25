package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerAuthorizeRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.AuthorizeRequest;
import extrawest.ocpp.model.response.AuthorizeResponse;

import java.util.UUID;

public class AuthorizeFeature implements Feature {
    private final IServerAuthorizeRequestHandler handler;

    public AuthorizeFeature(IServerAuthorizeRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleAuthorizeRequest(sessionIndex, (AuthorizeRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return AuthorizeRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return AuthorizeResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.AUTHORIZE.value();
    }
}
