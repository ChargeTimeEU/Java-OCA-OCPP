package eu.chargetime.ocpp.features.client.handlers;

import eu.chargetime.ocpp.model.request.UnpublishFirmwareRequest;
import eu.chargetime.ocpp.model.request.UpdateFirmwareRequest;
import eu.chargetime.ocpp.model.response.UpdateFirmwareResponse;

/** Charging Station handler of {@link UnpublishFirmwareRequest} */
public interface IClientUpdateFirmwareRequestHandler {
    UpdateFirmwareResponse handleUpdateFirmwareRequest(UpdateFirmwareRequest request);
}
