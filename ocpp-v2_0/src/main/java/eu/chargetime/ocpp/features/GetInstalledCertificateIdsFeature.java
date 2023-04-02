package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetInstalledCertificateIdsRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetInstalledCertificateIdsRequest;
import eu.chargetime.ocpp.model.response.GetInstalledCertificateIdsResponse;

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
