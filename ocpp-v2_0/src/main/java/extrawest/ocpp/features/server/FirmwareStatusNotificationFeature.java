package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerFirmwareStatusNotificationRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.FirmwareStatusNotificationRequest;
import extrawest.ocpp.model.response.FirmwareStatusNotificationResponse;

import java.util.UUID;

public class FirmwareStatusNotificationFeature implements Feature {
    private final IServerFirmwareStatusNotificationRequestHandler handler;

    public FirmwareStatusNotificationFeature(IServerFirmwareStatusNotificationRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleFirmwareStatusNotification(sessionIndex, (FirmwareStatusNotificationRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return FirmwareStatusNotificationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return FirmwareStatusNotificationResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.FIRMWARE_STATUS_NOTIFICATION.value();
    }
}
