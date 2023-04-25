package extrawest.ocpp.features.server;

import extrawest.ocpp.constants.FeatureConstants;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.features.server.handlers.IServerNotifyReportRequestHandler;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.request.NotifyReportRequest;
import extrawest.ocpp.model.response.NotifyReportResponse;

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
