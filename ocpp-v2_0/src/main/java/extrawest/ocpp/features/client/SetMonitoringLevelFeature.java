package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientSetMonitoringLevelRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.SetMonitoringLevelRequest;
import extrawest.ocpp.model.response.SetMonitoringLevelResponse;

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
