package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientUpdateFirmwareRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.UpdateFirmwareRequest;
import extrawest.ocpp.model.response.UpdateFirmwareResponse;

import java.util.UUID;

public class UpdateFirmwareFeature implements Feature {
    private final IClientUpdateFirmwareRequestHandler handler;

    public UpdateFirmwareFeature(IClientUpdateFirmwareRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleUpdateFirmwareRequest((UpdateFirmwareRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return UpdateFirmwareRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return UpdateFirmwareResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.UPDATE_FIRMWARE.value();
    }
}
