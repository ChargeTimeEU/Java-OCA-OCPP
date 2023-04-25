package extrawest.ocpp.features.server.handlers;

import extrawest.ocpp.model.request.AuthorizeRequest;
import extrawest.ocpp.model.request.NotifyCustomerInformationRequest;
import extrawest.ocpp.model.response.NotifyCustomerInformationResponse;

import java.util.UUID;

/** Central system handler of {@link AuthorizeRequest}s. */
public interface IServerNotifyCustomerInformationRequestHandler {
    NotifyCustomerInformationResponse handleNotifyCustomerInformationRequest(
            UUID sessionIndex, NotifyCustomerInformationRequest request);
}
