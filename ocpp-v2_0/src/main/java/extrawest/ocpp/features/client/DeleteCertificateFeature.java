package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientDeleteCertificateRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.DeleteCertificateRequest;
import extrawest.ocpp.model.response.DeleteCertificateResponse;

import java.util.UUID;

public class DeleteCertificateFeature implements Feature {
    private final IClientDeleteCertificateRequestHandler handler;

    public DeleteCertificateFeature(IClientDeleteCertificateRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleCancelDeleteCertificateRequest((DeleteCertificateRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return DeleteCertificateRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return DeleteCertificateResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.DELETE_CERTIFICATE.value();
    }
}
