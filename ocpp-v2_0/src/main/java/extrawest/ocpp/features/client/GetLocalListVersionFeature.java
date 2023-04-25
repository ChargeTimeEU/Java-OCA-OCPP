package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetLocalListVersionRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetLocalListVersionRequest;
import extrawest.ocpp.model.response.GetLocalListVersionResponse;

import java.util.UUID;

public class GetLocalListVersionFeature implements Feature {
    private final IClientGetLocalListVersionRequestHandler handler;

    public GetLocalListVersionFeature(IClientGetLocalListVersionRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetLocalListVersionRequest((GetLocalListVersionRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetLocalListVersionRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetLocalListVersionResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_LOCAL_LIST_VERSION.value();
    }
}
