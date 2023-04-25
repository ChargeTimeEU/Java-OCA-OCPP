package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.CertificateSignedRequest;
import extrawest.ocpp.model.response.CertificateSignedResponse;

/** Charging Station handler of {@link CertificateSignedRequest} */
public interface IClientCertificateSignedRequestHandler {
    CertificateSignedResponse handleCertificateSignedRequest(CertificateSignedRequest request);
}
