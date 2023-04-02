package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientSetVariableMonitoringRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.SetVariableMonitoringRequest;
import eu.chargetime.ocpp.model.response.SetVariableMonitoringResponse;

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
