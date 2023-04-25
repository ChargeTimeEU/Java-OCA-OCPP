package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.GetCertificateStatusRequest;
import extrawest.ocpp.model.response.GetCertificateStatusResponse;

import java.util.UUID;

/** Central system handler of {@link GetCertificateStatusRequest}s. */
public interface IServerGetCertificateStatusRequestHandler {
    GetCertificateStatusResponse handleGetCertificateStatusRequest(
            UUID sessionIndex, GetCertificateStatusRequest request);
}
