package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientSetMonitoringBaseRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.SetMonitoringBaseRequest;
import eu.chargetime.ocpp.model.response.SetMonitoringBaseResponse;

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
