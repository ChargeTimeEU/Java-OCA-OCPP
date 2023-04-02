package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientUpdateFirmwareRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.UpdateFirmwareRequest;
import eu.chargetime.ocpp.model.response.UpdateFirmwareResponse;

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
