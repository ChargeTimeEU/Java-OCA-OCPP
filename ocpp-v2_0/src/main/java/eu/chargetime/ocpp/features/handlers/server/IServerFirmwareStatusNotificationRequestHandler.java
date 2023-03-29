package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.FirmwareStatusNotificationRequest;
import eu.chargetime.ocpp.model.response.FirmwareStatusNotificationResponse;

import java.util.UUID;

/** Central system handler of {@link FirmwareStatusNotificationRequest}s. */
public interface IServerFirmwareStatusNotificationRequestHandler {
    FirmwareStatusNotificationResponse handleAuthorizeRequest(
            UUID sessionIndex, FirmwareStatusNotificationRequest request);
}
