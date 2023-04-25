package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerPublishFirmwareStatusNotificationRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.PublishFirmwareStatusNotificationRequest;
import extrawest.ocpp.model.response.PublishFirmwareStatusNotificationResponse;

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
