package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.PublishFirmwareStatusNotificationRequest;
import eu.chargetime.ocpp.model.response.PublishFirmwareStatusNotificationResponse;

import java.util.UUID;

/** Central system handler of {@link PublishFirmwareStatusNotificationRequest}s. */
public interface IServerPublishFirmwareStatusNotificationRequestHandler {
    PublishFirmwareStatusNotificationResponse handlePublishFirmwareStatusNotificationRequest(
            UUID sessionIndex, PublishFirmwareStatusNotificationRequest request);
}
