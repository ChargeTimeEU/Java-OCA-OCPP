package eu.chargetime.ocpp.features.handlers.client;

import eu.chargetime.ocpp.model.request.CostUpdatedRequest;
import eu.chargetime.ocpp.model.response.CostUpdatedResponse;

/** Charging Station handler of {@link CostUpdatedRequest} */
public interface IClientCostUpdatedRequestHandler {
    CostUpdatedResponse handleCostUpdatedRequest(CostUpdatedRequest request);
}
