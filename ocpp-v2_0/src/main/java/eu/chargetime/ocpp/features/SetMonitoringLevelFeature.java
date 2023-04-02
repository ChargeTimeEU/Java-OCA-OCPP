package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientSetMonitoringLevelRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.SetMonitoringLevelRequest;
import eu.chargetime.ocpp.model.response.SetMonitoringLevelResponse;

import java.util.UUID;

public class SetMonitoringLevelFeature implements Feature {
    private final IClientSetMonitoringLevelRequestHandler handler;

    public SetMonitoringLevelFeature(IClientSetMonitoringLevelRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleSetMonitoringLevelRequest((SetMonitoringLevelRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return SetMonitoringLevelRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetMonitoringLevelResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.SET_MONITORING_LEVEL.value();
    }
}
