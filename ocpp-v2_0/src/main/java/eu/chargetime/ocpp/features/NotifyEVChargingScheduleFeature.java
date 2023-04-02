package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerNotifyEVChargingScheduleRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.NotifyEVChargingScheduleRequest;
import eu.chargetime.ocpp.model.response.NotifyEVChargingScheduleResponse;

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
