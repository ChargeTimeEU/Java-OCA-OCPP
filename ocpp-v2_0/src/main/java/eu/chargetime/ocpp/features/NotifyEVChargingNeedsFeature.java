package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerNotifyEVChargingNeedsRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.NotifyEVChargingNeedsRequest;
import eu.chargetime.ocpp.model.response.NotifyEVChargingNeedsResponse;

import java.util.UUID;

public class NotifyEVChargingNeedsFeature implements Feature {
    private final IServerNotifyEVChargingNeedsRequestHandler handler;

    public NotifyEVChargingNeedsFeature(IServerNotifyEVChargingNeedsRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleNotifyEVChargingNeedsRequest(sessionIndex, (NotifyEVChargingNeedsRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyEVChargingNeedsRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyEVChargingNeedsResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.NOTIFY_EV_CHARGING_NEEDS.value();
    }
}
