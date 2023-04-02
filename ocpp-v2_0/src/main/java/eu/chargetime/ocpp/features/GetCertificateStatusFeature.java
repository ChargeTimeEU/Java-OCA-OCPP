package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerGetCertificateStatusRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetCertificateStatusRequest;
import eu.chargetime.ocpp.model.response.GetCertificateStatusResponse;

import java.util.UUID;

public class GetCertificateStatusFeature implements Feature {
    private final IServerGetCertificateStatusRequestHandler handler;

    public GetCertificateStatusFeature(IServerGetCertificateStatusRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetCertificateStatusRequest(sessionIndex, (GetCertificateStatusRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetCertificateStatusRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetCertificateStatusResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_CERTIFICATE_STATUS.value();
    }
}
