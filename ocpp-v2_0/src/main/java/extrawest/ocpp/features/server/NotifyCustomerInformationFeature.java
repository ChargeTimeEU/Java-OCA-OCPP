package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerNotifyCustomerInformationRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.NotifyCustomerInformationRequest;
import extrawest.ocpp.model.response.NotifyCustomerInformationResponse;

import java.util.UUID;

public class NotifyCustomerInformationFeature implements Feature {
    private final IServerNotifyCustomerInformationRequestHandler handler;

    public NotifyCustomerInformationFeature(IServerNotifyCustomerInformationRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleNotifyCustomerInformationRequest(sessionIndex, (NotifyCustomerInformationRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyCustomerInformationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyCustomerInformationResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.NOTIFY_CUSTOMER_INFORMATION.value();
    }
}
