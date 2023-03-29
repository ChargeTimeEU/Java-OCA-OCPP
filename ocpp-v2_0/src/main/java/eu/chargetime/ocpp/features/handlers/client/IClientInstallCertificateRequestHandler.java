package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.InstallCertificateRequest;
import eu.chargetime.ocpp.model.response.InstallCertificateResponse;

/** Charging Station handler of {@link InstallCertificateRequest} */
public interface IClientInstallCertificateRequestHandler {
    InstallCertificateResponse handleInstallCertificateRequest(InstallCertificateRequest request);
}
