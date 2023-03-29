package eu.chargetime.ocpp.features.basic.handlers.server;

import eu.chargetime.ocpp.model.request.SignCertificateRequest;
import eu.chargetime.ocpp.model.response.SignCertificateResponse;

import java.util.UUID;

/** Central system handler of {@link SignCertificateRequest}s. */
public interface IServerSignCertificateRequestHandler {
    SignCertificateResponse handleSignCertificateRequest(
            UUID sessionIndex, SignCertificateRequest request);
}
