package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.GetCertificateStatusRequest;
import eu.chargetime.ocpp.model.response.GetCertificateStatusResponse;

import java.util.UUID;

/** Central system handler of {@link GetCertificateStatusRequest}s. */
public interface IServerGetCertificateStatusRequestHandler {
    GetCertificateStatusResponse handleGetCertificateStatusRequest(
            UUID sessionIndex, GetCertificateStatusRequest request);
}
