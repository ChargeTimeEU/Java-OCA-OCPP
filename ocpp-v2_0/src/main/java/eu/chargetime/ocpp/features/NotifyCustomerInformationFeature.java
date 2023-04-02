package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerNotifyCustomerInformationRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.NotifyCustomerInformationRequest;
import eu.chargetime.ocpp.model.response.NotifyCustomerInformationResponse;

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
