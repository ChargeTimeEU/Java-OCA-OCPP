package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerNotifyEVChargingNeedsRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.NotifyEVChargingNeedsRequest;
import extrawest.ocpp.model.response.NotifyEVChargingNeedsResponse;

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
