package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetInstalledCertificateIdsRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetInstalledCertificateIdsRequest;
import extrawest.ocpp.model.response.GetInstalledCertificateIdsResponse;

import java.util.UUID;

public class GetInstalledCertificateIdsFeature implements Feature {
    private final IClientGetInstalledCertificateIdsRequestHandler handler;

    public GetInstalledCertificateIdsFeature(IClientGetInstalledCertificateIdsRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetInstalledCertificateIdsRequest((GetInstalledCertificateIdsRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetInstalledCertificateIdsRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetInstalledCertificateIdsResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_INSTALLED_CERTIFICATE_IDS.value();
    }
}
