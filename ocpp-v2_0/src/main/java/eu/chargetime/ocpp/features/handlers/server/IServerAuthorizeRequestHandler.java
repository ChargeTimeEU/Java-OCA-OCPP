package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.AuthorizeRequest;
import eu.chargetime.ocpp.model.response.AuthorizeResponse;

import java.util.UUID;

/** Central system handler of {@link AuthorizeRequest}s. */
public interface IServerAuthorizeRequestHandler {
    AuthorizeResponse handleAuthorizeRequest(
            UUID sessionIndex, AuthorizeRequest request);
}
