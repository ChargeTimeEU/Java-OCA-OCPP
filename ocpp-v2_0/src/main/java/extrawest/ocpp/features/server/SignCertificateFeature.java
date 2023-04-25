package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerSignCertificateRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.SignCertificateRequest;
import extrawest.ocpp.model.response.SignCertificateResponse;

import java.util.UUID;

public class SignCertificateFeature implements Feature {
    private final IServerSignCertificateRequestHandler handler;

    public SignCertificateFeature(IServerSignCertificateRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleSignCertificateRequest(sessionIndex, (SignCertificateRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return SignCertificateRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SignCertificateResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.SIGN_CERTIFICATE.value();
    }
}
