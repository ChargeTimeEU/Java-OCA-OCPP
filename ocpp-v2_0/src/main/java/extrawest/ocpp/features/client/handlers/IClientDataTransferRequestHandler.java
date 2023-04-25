package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.DataTransferRequest;
import extrawest.ocpp.model.response.DataTransferResponse;

/** Charging Station handler of {@link DataTransferRequest} */
public interface IClientDataTransferRequestHandler {
    DataTransferResponse handleCancelDataTransferRequest(DataTransferRequest request);
}
