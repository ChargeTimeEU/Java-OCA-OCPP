package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientPublishFirmwareRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.PublishFirmwareRequest;
import extrawest.ocpp.model.response.PublishFirmwareResponse;

import java.util.UUID;

public class PublishFirmwareFeature implements Feature {
    private final IClientPublishFirmwareRequestHandler handler;

    public PublishFirmwareFeature(IClientPublishFirmwareRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handlePublishFirmwareRequest((PublishFirmwareRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return PublishFirmwareRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return PublishFirmwareResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.PUBLISH_FIRMWARE.value();
    }
}
