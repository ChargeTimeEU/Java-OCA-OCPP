package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientCustomerInformationRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.CustomerInformationRequest;
import extrawest.ocpp.model.response.CustomerInformationResponse;

import java.util.UUID;

public class CustomerInformationFeature implements Feature {
    private final IClientCustomerInformationRequestHandler handler;

    public CustomerInformationFeature(IClientCustomerInformationRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleCustomerInformationRequest((CustomerInformationRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return CustomerInformationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CustomerInformationResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.CUSTOMER_INFORMATION.value();
    }
}
