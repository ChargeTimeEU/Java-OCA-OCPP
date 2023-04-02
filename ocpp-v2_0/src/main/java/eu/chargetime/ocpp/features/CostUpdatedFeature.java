package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientCostUpdatedRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.CostUpdatedRequest;
import eu.chargetime.ocpp.model.response.CostUpdatedResponse;

import java.util.UUID;

public class CostUpdatedFeature implements Feature {
    private final IClientCostUpdatedRequestHandler handler;

    public CostUpdatedFeature(IClientCostUpdatedRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleCostUpdatedRequest((CostUpdatedRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return CostUpdatedRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CostUpdatedResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.COST_UPDATED.value();
    }
}
