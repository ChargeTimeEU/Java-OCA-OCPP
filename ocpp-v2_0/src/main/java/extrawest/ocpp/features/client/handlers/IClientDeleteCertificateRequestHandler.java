package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.DeleteCertificateRequest;
import extrawest.ocpp.model.response.DeleteCertificateResponse;

/** Charging Station handler of {@link DeleteCertificateRequest} */
public interface IClientDeleteCertificateRequestHandler {
    DeleteCertificateResponse handleCancelDeleteCertificateRequest(DeleteCertificateRequest request);
}
