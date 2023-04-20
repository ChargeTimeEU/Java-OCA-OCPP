package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.DeleteCertificateRequest;
import eu.chargetime.ocpp.model.response.DeleteCertificateResponse;

/** Charging Station handler of {@link DeleteCertificateRequest} */
public interface IClientDeleteCertificateRequestHandler {
    DeleteCertificateResponse handleCancelDeleteCertificateRequest(DeleteCertificateRequest request);
}
