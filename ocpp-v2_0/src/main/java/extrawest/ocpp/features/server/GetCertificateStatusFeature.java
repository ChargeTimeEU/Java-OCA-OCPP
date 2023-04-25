package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerGetCertificateStatusRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetCertificateStatusRequest;
import extrawest.ocpp.model.response.GetCertificateStatusResponse;

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
