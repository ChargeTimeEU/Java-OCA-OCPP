package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.CustomerInformationRequest;
import extrawest.ocpp.model.response.CustomerInformationResponse;

/** Charging Station handler of {@link CustomerInformationRequest} */
public interface IClientCustomerInformationRequestHandler {
    CustomerInformationResponse handleCustomerInformationRequest(CustomerInformationRequest request);
}
