package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientClearVariableMonitoringRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ClearVariableMonitoringRequest;
import extrawest.ocpp.model.response.ClearVariableMonitoringResponse;

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
