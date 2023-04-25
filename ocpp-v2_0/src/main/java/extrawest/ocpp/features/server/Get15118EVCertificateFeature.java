package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerGet15118EVCertificateRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.Get15118EVCertificateRequest;
import extrawest.ocpp.model.response.Get15118EVCertificateResponse;

import java.util.UUID;

public class Get15118EVCertificateFeature implements Feature {
    private final IServerGet15118EVCertificateRequestHandler handler;

    public Get15118EVCertificateFeature(IServerGet15118EVCertificateRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGet15118EVCertificateRequest(sessionIndex, (Get15118EVCertificateRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return Get15118EVCertificateRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return Get15118EVCertificateResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET15118EV_CERTIFICATE.value();
    }
}
