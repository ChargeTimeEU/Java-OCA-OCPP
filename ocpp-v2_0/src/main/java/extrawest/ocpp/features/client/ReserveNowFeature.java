package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientReserveNowRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.ReserveNowRequest;
import extrawest.ocpp.model.response.ReserveNowResponse;

import java.util.UUID;

public class ReserveNowFeature implements Feature {
    private final IClientReserveNowRequestHandler handler;

    public ReserveNowFeature(IClientReserveNowRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleReserveNowRequest((ReserveNowRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ReserveNowRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ReserveNowResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.RESERVE_NOW.value();
    }
}
