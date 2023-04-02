package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerSignCertificateRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.SignCertificateRequest;
import eu.chargetime.ocpp.model.response.SignCertificateResponse;

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
