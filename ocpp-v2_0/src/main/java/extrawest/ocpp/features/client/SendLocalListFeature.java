package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientSendLocalListRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.SendLocalListRequest;
import extrawest.ocpp.model.response.SendLocalListResponse;

import java.util.UUID;

public class SendLocalListFeature implements Feature {
    private final IClientSendLocalListRequestHandler handler;

    public SendLocalListFeature(IClientSendLocalListRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleSendLocalListRequest((SendLocalListRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return SendLocalListRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SendLocalListResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.SEND_LOCAL_LIST.value();
    }
}
