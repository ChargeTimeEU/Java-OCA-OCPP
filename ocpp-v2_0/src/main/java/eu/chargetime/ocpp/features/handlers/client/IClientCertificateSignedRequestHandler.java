package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.CertificateSignedRequest;
import eu.chargetime.ocpp.model.response.CertificateSignedResponse;

/** Charging Station handler of {@link CertificateSignedRequest} */
public interface IClientCertificateSignedRequestHandler {
    CertificateSignedResponse handleCertificateSignedRequest(CertificateSignedRequest request);
}
