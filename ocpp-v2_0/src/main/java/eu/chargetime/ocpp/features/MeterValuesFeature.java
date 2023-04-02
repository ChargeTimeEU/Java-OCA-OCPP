package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerMeterValuesRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.MeterValuesRequest;
import eu.chargetime.ocpp.model.response.MeterValuesResponse;

import java.util.UUID;

public class MeterValuesFeature implements Feature {
    private final IServerMeterValuesRequestHandler handler;

    public MeterValuesFeature(IServerMeterValuesRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleMeterValuesRequest(sessionIndex, (MeterValuesRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return MeterValuesRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return MeterValuesResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.METER_VALUES.value();
    }
}
