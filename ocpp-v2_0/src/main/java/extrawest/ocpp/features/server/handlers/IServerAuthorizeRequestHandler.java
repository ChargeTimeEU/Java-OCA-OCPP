package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.AuthorizeRequest;
import extrawest.ocpp.model.response.AuthorizeResponse;

import java.util.UUID;

/** Central system handler of {@link AuthorizeRequest}s. */
public interface IServerAuthorizeRequestHandler {
    AuthorizeResponse handleAuthorizeRequest(
            UUID sessionIndex, AuthorizeRequest request);
}
