package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.FirmwareStatusNotificationRequest;
import extrawest.ocpp.model.response.FirmwareStatusNotificationResponse;

import java.util.UUID;

/** Central system handler of {@link FirmwareStatusNotificationRequest}s. */
public interface IServerFirmwareStatusNotificationRequestHandler {
    FirmwareStatusNotificationResponse handleFirmwareStatusNotification(
            UUID sessionIndex, FirmwareStatusNotificationRequest request);
}
