package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetLogRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetLogRequest;
import extrawest.ocpp.model.response.GetLogResponse;

import java.util.UUID;

public class GetLogFeature implements Feature {
    private final IClientGetLogRequestHandler handler;

    public GetLogFeature(IClientGetLogRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetLogRequest((GetLogRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetLogRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetLogResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_LOG.value();
    }
}
