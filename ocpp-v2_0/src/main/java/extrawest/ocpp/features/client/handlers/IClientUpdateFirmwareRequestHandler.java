package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.UnpublishFirmwareRequest;
import extrawest.ocpp.model.request.UpdateFirmwareRequest;
import extrawest.ocpp.model.response.UpdateFirmwareResponse;

/** Charging Station handler of {@link UnpublishFirmwareRequest} */
public interface IClientUpdateFirmwareRequestHandler {
    UpdateFirmwareResponse handleUpdateFirmwareRequest(UpdateFirmwareRequest request);
}
