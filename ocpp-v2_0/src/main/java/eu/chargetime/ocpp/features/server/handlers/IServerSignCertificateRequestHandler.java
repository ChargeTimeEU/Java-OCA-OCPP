package eu.chargetime.ocpp.features.server.handlers;

import eu.chargetime.ocpp.model.request.SignCertificateRequest;
import eu.chargetime.ocpp.model.response.SignCertificateResponse;

import java.util.UUID;

/** Central system handler of {@link SignCertificateRequest}s. */
public interface IServerSignCertificateRequestHandler {
    SignCertificateResponse handleSignCertificateRequest(
            UUID sessionIndex, SignCertificateRequest request);
}
