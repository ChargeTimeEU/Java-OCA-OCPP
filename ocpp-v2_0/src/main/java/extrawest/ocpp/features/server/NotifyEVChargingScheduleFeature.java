package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerNotifyEVChargingScheduleRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.NotifyEVChargingScheduleRequest;
import extrawest.ocpp.model.response.NotifyEVChargingScheduleResponse;

import java.util.UUID;

public class NotifyEVChargingScheduleFeature implements Feature {
    private final IServerNotifyEVChargingScheduleRequestHandler handler;

    public NotifyEVChargingScheduleFeature(IServerNotifyEVChargingScheduleRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleNotifyEVChargingScheduleRequest(sessionIndex, (NotifyEVChargingScheduleRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyEVChargingScheduleRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyEVChargingScheduleResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.NOTIFY_EV_CHARGING_SCHEDULE.value();
    }
}
