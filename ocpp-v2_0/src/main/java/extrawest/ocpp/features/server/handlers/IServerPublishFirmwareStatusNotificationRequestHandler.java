package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.PublishFirmwareStatusNotificationRequest;
import extrawest.ocpp.model.response.PublishFirmwareStatusNotificationResponse;

import java.util.UUID;

/** Central system handler of {@link PublishFirmwareStatusNotificationRequest}s. */
public interface IServerPublishFirmwareStatusNotificationRequestHandler {
    PublishFirmwareStatusNotificationResponse handlePublishFirmwareStatusNotificationRequest(
            UUID sessionIndex, PublishFirmwareStatusNotificationRequest request);
}
