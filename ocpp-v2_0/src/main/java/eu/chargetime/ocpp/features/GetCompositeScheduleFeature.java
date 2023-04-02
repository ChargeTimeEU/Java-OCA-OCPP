package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.client.IClientGetCompositeScheduleRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.GetCompositeScheduleRequest;
import eu.chargetime.ocpp.model.response.GetCompositeScheduleResponse;

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
