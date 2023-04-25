package extrawest.ocpp.features.client;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.client.handlers.IClientGetCompositeScheduleRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.GetCompositeScheduleRequest;
import extrawest.ocpp.model.response.GetCompositeScheduleResponse;

import java.util.UUID;

public class GetCompositeScheduleFeature implements Feature {
    private final IClientGetCompositeScheduleRequestHandler handler;

    public GetCompositeScheduleFeature(IClientGetCompositeScheduleRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleGetCompositeScheduleRequest((GetCompositeScheduleRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetCompositeScheduleRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetCompositeScheduleResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.GET_COMPOSITE_SCHEDULE.value();
    }
}
