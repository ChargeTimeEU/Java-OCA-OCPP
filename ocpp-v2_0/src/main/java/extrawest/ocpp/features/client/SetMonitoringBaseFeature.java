package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientSetMonitoringBaseRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.SetMonitoringBaseRequest;
import extrawest.ocpp.model.response.SetMonitoringBaseResponse;

import java.util.UUID;

public class SetMonitoringBaseFeature implements Feature {
    private final IClientSetMonitoringBaseRequestHandler handler;

    public SetMonitoringBaseFeature(IClientSetMonitoringBaseRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleSetMonitoringBaseRequest((SetMonitoringBaseRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return SetMonitoringBaseRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetMonitoringBaseResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.SET_MONITORING_BASE.value();
    }
}
