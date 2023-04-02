package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerHeartbeatRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.HeartbeatRequest;
import eu.chargetime.ocpp.model.response.HeartbeatResponse;

import java.util.UUID;

public class HeartbeatFeature implements Feature {
    private final IServerHeartbeatRequestHandler handler;

    public HeartbeatFeature(IServerHeartbeatRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleHeartbeatRequest(sessionIndex, (HeartbeatRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return HeartbeatRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return HeartbeatResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.HEARTBEAT.value();
    }
}
