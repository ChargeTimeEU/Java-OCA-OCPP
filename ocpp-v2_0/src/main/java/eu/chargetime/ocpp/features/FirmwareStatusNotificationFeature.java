package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerFirmwareStatusNotificationRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.FirmwareStatusNotificationRequest;
import eu.chargetime.ocpp.model.response.FirmwareStatusNotificationResponse;

import java.util.UUID;

public class FirmwareStatusNotificationFeature implements Feature {
    private final IServerFirmwareStatusNotificationRequestHandler handler;

    public FirmwareStatusNotificationFeature(IServerFirmwareStatusNotificationRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleAuthorizeRequest(sessionIndex, (FirmwareStatusNotificationRequest) request);
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
