package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientSetVariableMonitoringRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.SetVariableMonitoringRequest;
import extrawest.ocpp.model.response.SetVariableMonitoringResponse;

import java.util.UUID;

public class SetVariableMonitoringFeature implements Feature {
    private final IClientSetVariableMonitoringRequestHandler handler;

    public SetVariableMonitoringFeature(IClientSetVariableMonitoringRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleSetVariableMonitoringRequest((SetVariableMonitoringRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return SetVariableMonitoringRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetVariableMonitoringResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.SET_VARIABLE_MONITORING.value();
    }
}
