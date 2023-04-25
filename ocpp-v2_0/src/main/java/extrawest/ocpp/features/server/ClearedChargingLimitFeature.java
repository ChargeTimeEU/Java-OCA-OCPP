package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerClearedChargingLimitRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ClearedChargingLimitRequest;
import extrawest.ocpp.model.response.ClearedChargingLimitResponse;

import java.util.UUID;

public class ClearedChargingLimitFeature implements Feature {
    private final IServerClearedChargingLimitRequestHandler handler;

    public ClearedChargingLimitFeature(IServerClearedChargingLimitRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleClearedChargingLimitRequest(sessionIndex, (ClearedChargingLimitRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ClearedChargingLimitRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearedChargingLimitResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.CLEARED_CHARGING_LIMIT.value();
    }
}
