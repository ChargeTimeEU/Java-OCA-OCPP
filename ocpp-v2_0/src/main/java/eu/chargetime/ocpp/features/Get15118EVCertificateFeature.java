package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerGet15118EVCertificateRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.Get15118EVCertificateRequest;
import eu.chargetime.ocpp.model.response.Get15118EVCertificateResponse;

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
