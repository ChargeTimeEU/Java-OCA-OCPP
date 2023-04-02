package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientSendLocalListRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.SendLocalListRequest;
import eu.chargetime.ocpp.model.response.SendLocalListResponse;

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
