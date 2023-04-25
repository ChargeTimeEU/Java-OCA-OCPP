package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.InstallCertificateRequest;
import extrawest.ocpp.model.response.InstallCertificateResponse;

/** Charging Station handler of {@link InstallCertificateRequest} */
public interface IClientInstallCertificateRequestHandler {
    InstallCertificateResponse handleInstallCertificateRequest(InstallCertificateRequest request);
}
