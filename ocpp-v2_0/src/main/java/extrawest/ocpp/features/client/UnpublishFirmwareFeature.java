package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientUnpublishFirmwareRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.UnpublishFirmwareRequest;
import extrawest.ocpp.model.response.UnpublishFirmwareResponse;

import java.util.UUID;

public class UnpublishFirmwareFeature implements Feature {
    private final IClientUnpublishFirmwareRequestHandler handler;

    public UnpublishFirmwareFeature(IClientUnpublishFirmwareRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleUnpublishFirmwareRequest((UnpublishFirmwareRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return UnpublishFirmwareRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return UnpublishFirmwareResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.UNPUBLISH_FIRMWARE.value();
    }
}
