package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerNotifyChargingLimitRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.NotifyChargingLimitRequest;
import extrawest.ocpp.model.response.NotifyChargingLimitResponse;

import java.util.UUID;

public class NotifyChargingLimitFeature implements Feature {
    private final IServerNotifyChargingLimitRequestHandler handler;

    public NotifyChargingLimitFeature(IServerNotifyChargingLimitRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleNotifyChargingLimitRequest(sessionIndex, (NotifyChargingLimitRequest) request);
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
