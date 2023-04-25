package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientCostUpdatedRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.CostUpdatedRequest;
import extrawest.ocpp.model.response.CostUpdatedResponse;

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
