package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientReserveNowRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ReserveNowRequest;
import eu.chargetime.ocpp.model.response.ReserveNowResponse;

import java.util.UUID;

public class ReserveNowFeature implements Feature {
    private final IClientReserveNowRequestHandler handler;

    public ReserveNowFeature(IClientReserveNowRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleReserveNowRequest((ReserveNowRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ReserveNowRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ReserveNowResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.RESERVE_NOW.value();
    }
}
