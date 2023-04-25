package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientCertificateSignedRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.CertificateSignedRequest;
import extrawest.ocpp.model.response.CertificateSignedResponse;

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
