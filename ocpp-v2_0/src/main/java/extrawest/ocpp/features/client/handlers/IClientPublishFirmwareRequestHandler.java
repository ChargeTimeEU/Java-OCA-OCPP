package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.PublishFirmwareRequest;
import extrawest.ocpp.model.response.PublishFirmwareResponse;

/** Charging Station handler of {@link PublishFirmwareRequest} */
public interface IClientPublishFirmwareRequestHandler {
    PublishFirmwareResponse handlePublishFirmwareRequest(PublishFirmwareRequest request);
}
