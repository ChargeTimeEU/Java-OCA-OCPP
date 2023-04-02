package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerAuthorizeRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.AuthorizeRequest;
import eu.chargetime.ocpp.model.response.AuthorizeResponse;

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
