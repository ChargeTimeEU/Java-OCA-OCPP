package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.Get15118EVCertificateRequest;
import extrawest.ocpp.model.response.Get15118EVCertificateResponse;

import java.util.UUID;

/** Central system handler of {@link Get15118EVCertificateRequest}s. */
public interface IServerGet15118EVCertificateRequestHandler {
    Get15118EVCertificateResponse handleGet15118EVCertificateRequest(
            UUID sessionIndex, Get15118EVCertificateRequest request);
}
