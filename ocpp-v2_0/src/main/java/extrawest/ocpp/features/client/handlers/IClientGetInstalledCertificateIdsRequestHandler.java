package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.GetInstalledCertificateIdsRequest;
import extrawest.ocpp.model.response.GetInstalledCertificateIdsResponse;

/** Charging Station handler of {@link GetInstalledCertificateIdsRequest} */
public interface IClientGetInstalledCertificateIdsRequestHandler {
    GetInstalledCertificateIdsResponse handleGetInstalledCertificateIdsRequest(GetInstalledCertificateIdsRequest request);
}
