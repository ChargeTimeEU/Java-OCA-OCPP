package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerPublishFirmwareStatusNotificationRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.PublishFirmwareStatusNotificationRequest;
import eu.chargetime.ocpp.model.response.PublishFirmwareStatusNotificationResponse;

import java.util.UUID;

public class PublishFirmwareStatusNotificationFeature implements Feature {
    private final IServerPublishFirmwareStatusNotificationRequestHandler handler;

    public PublishFirmwareStatusNotificationFeature(IServerPublishFirmwareStatusNotificationRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handlePublishFirmwareStatusNotificationRequest(sessionIndex, (PublishFirmwareStatusNotificationRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return PublishFirmwareStatusNotificationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return PublishFirmwareStatusNotificationResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.PUBLISH_FIRMWARE_STATUS_NOTIFICATION.value();
    }
}
