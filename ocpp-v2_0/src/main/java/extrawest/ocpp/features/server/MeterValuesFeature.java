package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerMeterValuesRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.MeterValuesRequest;
import extrawest.ocpp.model.response.MeterValuesResponse;

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
