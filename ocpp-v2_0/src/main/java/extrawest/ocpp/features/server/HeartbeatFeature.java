package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerHeartbeatRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.HeartbeatRequest;
import extrawest.ocpp.model.response.HeartbeatResponse;

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
