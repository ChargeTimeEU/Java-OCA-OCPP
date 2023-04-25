package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientInstallCertificateRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.InstallCertificateRequest;
import extrawest.ocpp.model.response.InstallCertificateResponse;

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
