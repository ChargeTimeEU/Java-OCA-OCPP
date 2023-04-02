package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerClearedChargingLimitRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ClearedChargingLimitRequest;
import eu.chargetime.ocpp.model.response.ClearedChargingLimitResponse;

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
