package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.SignCertificateRequest;
import extrawest.ocpp.model.response.SignCertificateResponse;

import java.util.UUID;

/** Central system handler of {@link SignCertificateRequest}s. */
public interface IServerSignCertificateRequestHandler {
    SignCertificateResponse handleSignCertificateRequest(
            UUID sessionIndex, SignCertificateRequest request);
}
