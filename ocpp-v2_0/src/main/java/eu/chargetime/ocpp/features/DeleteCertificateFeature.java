package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientDeleteCertificateRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.DeleteCertificateRequest;
import eu.chargetime.ocpp.model.response.DeleteCertificateResponse;

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
