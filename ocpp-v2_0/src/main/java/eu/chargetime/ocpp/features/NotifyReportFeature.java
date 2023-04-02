package eu.chargetime.ocpp.features;

import eu.chargetime.ocpp.constants.FeatureConstants;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.features.handlers.server.IServerNotifyReportRequestHandler;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.request.NotifyReportRequest;
import eu.chargetime.ocpp.model.response.NotifyReportResponse;

import java.util.UUID;

public class NotifyReportFeature implements Feature {
    private final IServerNotifyReportRequestHandler handler;

    public NotifyReportFeature(IServerNotifyReportRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return handler.handleNotifyReportRequest(sessionIndex, (NotifyReportRequest) request);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyReportRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyReportResponse.class;
    }

    @Override
    public String getAction() {
        return FeatureConstants.NOTIFY_REPORT.value();
    }
}
