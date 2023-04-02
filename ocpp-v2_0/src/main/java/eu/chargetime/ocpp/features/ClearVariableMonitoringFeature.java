package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientClearVariableMonitoringRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.ClearVariableMonitoringRequest;
import eu.chargetime.ocpp.model.response.ClearVariableMonitoringResponse;

import java.util.UUID;

public class ClearVariableMonitoringFeature implements Feature {
    private final IClientClearVariableMonitoringRequestHandler handler;

    public ClearVariableMonitoringFeature(IClientClearVariableMonitoringRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleClearVariableMonitoringRequest((ClearVariableMonitoringRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ClearVariableMonitoringRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearVariableMonitoringResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.CLEAR_VARIABLE_MONITORING.value();
    }
}
