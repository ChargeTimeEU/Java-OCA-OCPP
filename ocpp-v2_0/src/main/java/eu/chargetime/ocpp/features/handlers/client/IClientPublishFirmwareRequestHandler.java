package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.PublishFirmwareRequest;
import eu.chargetime.ocpp.model.response.PublishFirmwareResponse;

/** Charging Station handler of {@link PublishFirmwareRequest} */
public interface IClientPublishFirmwareRequestHandler {
    PublishFirmwareResponse handlePublishFirmwareRequest(PublishFirmwareRequest request);
}
