package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.UnpublishFirmwareRequest;
import eu.chargetime.ocpp.model.response.UnpublishFirmwareResponse;

/** Charging Station handler of {@link UnpublishFirmwareRequest} */
public interface IClientUnpublishFirmwareRequestHandler {
    UnpublishFirmwareResponse handleUnpublishFirmwareRequest(UnpublishFirmwareRequest request);
}
