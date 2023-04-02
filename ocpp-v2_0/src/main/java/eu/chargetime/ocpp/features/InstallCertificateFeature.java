package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientInstallCertificateRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.InstallCertificateRequest;
import eu.chargetime.ocpp.model.response.InstallCertificateResponse;

import java.util.UUID;

public class InstallCertificateFeature implements Feature {
    private final IClientInstallCertificateRequestHandler handler;

    public InstallCertificateFeature(IClientInstallCertificateRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleInstallCertificateRequest((InstallCertificateRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return InstallCertificateRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return InstallCertificateResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.INSTALL_CERTIFICATE.value();
    }
}
