package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientCertificateSignedRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.CertificateSignedRequest;
import eu.chargetime.ocpp.model.response.CertificateSignedResponse;

import java.util.UUID;

public class CertificateSignedFeature implements Feature {
    private final IClientCertificateSignedRequestHandler handler;

    public CertificateSignedFeature(IClientCertificateSignedRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleCertificateSignedRequest((CertificateSignedRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return CertificateSignedRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CertificateSignedResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.CERTIFICATE_SIGNED.value();
    }
}
