package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientCustomerInformationRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.CustomerInformationRequest;
import eu.chargetime.ocpp.model.response.CustomerInformationResponse;

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
