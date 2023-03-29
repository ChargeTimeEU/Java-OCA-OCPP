package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.DeleteCertificateRequest;
import eu.chargetime.ocpp.model.response.DeleteCertificateResponse;

/** Charging Station handler of {@link DeleteCertificateRequest} */
public interface IClientDeleteCertificateRequestHandler {
    DeleteCertificateResponse handleCancelDeleteCertificateRequest(DeleteCertificateRequest request);
}
