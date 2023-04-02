package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientPublishFirmwareRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.PublishFirmwareRequest;
import eu.chargetime.ocpp.model.response.PublishFirmwareResponse;

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
