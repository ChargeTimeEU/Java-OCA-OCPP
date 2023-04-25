package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.UnpublishFirmwareRequest;
import extrawest.ocpp.model.response.UnpublishFirmwareResponse;

/** Charging Station handler of {@link UnpublishFirmwareRequest} */
public interface IClientUnpublishFirmwareRequestHandler {
    UnpublishFirmwareResponse handleUnpublishFirmwareRequest(UnpublishFirmwareRequest request);
}
