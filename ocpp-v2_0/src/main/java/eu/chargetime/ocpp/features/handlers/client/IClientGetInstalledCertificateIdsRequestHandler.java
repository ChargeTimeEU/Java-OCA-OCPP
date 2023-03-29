package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.GetInstalledCertificateIdsRequest;
import eu.chargetime.ocpp.model.response.GetInstalledCertificateIdsResponse;

/** Charging Station handler of {@link GetInstalledCertificateIdsRequest} */
public interface IClientGetInstalledCertificateIdsRequestHandler {
    GetInstalledCertificateIdsResponse handleGetInstalledCertificateIdsRequest(GetInstalledCertificateIdsRequest request);
}
