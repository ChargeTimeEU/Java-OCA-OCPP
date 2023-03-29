package eu.chargetime.ocpp.features.handlers.server;

import eu.chargetime.ocpp.model.request.AuthorizeRequest;
import eu.chargetime.ocpp.model.request.NotifyCustomerInformationRequest;
import eu.chargetime.ocpp.model.response.NotifyCustomerInformationResponse;

import java.util.UUID;

/** Central system handler of {@link AuthorizeRequest}s. */
public interface IServerNotifyCustomerInformationRequestHandler {
    NotifyCustomerInformationResponse handleNotifyCustomerInformationRequest(
            UUID sessionIndex, NotifyCustomerInformationRequest request);
}
