package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientUnpublishFirmwareRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.UnpublishFirmwareRequest;
import eu.chargetime.ocpp.model.response.UnpublishFirmwareResponse;

import java.util.UUID;

public class UnpublishFirmwareFeature implements Feature {
    private final IClientUnpublishFirmwareRequestHandler handler;

    public UnpublishFirmwareFeature(IClientUnpublishFirmwareRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleUnpublishFirmwareRequest((UnpublishFirmwareRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return UnpublishFirmwareRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return UnpublishFirmwareResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.UNPUBLISH_FIRMWARE.value();
    }
}
