package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerNotifyChargingLimitRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.NotifyChargingLimitRequest;
import eu.chargetime.ocpp.model.response.NotifyChargingLimitResponse;

import java.util.UUID;

public class NotifyChargingLimitFeature implements Feature {
    private final IServerNotifyChargingLimitRequestHandler handler;

    public NotifyChargingLimitFeature(IServerNotifyChargingLimitRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleMeterValuesRequest(sessionIndex, (NotifyChargingLimitRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyChargingLimitRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyChargingLimitResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.NOTIFY_CHARGING_LIMIT.value();
    }
}
