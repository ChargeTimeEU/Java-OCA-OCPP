package extrawest.ocpp.features.client.handlers;

import extrawest.ocpp.model.request.CostUpdatedRequest;
import extrawest.ocpp.model.response.CostUpdatedResponse;

/** Charging Station handler of {@link CostUpdatedRequest} */
public interface IClientCostUpdatedRequestHandler {
    CostUpdatedResponse handleCostUpdatedRequest(CostUpdatedRequest request);
}
