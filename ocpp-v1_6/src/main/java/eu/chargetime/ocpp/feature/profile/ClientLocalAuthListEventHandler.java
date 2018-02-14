package eu.chargetime.ocpp.feature.profile;

import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionConfirmation;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionRequest;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListConfirmation;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;

public interface ClientLocalAuthListEventHandler {
    /**
     * Handle a {@link GetLocalListVersionRequest} and return a {@link GetLocalListVersionConfirmation}.
     *
     * @param request incoming {@link GetLocalListVersionRequest} to handle.
     * @return outgoing {@link GetLocalListVersionConfirmation} to reply with.
     */
	GetLocalListVersionConfirmation handleGetLocalListVersionRequest(GetLocalListVersionRequest request);
	
    /**
     * Handle a {@link SendLocalListRequest} and return a {@link SendLocalListConfirmation}.
     *
     * @param request incoming {@link SendLocalListRequest} to handle.
     * @return outgoing {@link SendLocalListConfirmation} to reply with.
     */
	SendLocalListConfirmation handleSendLocalListReqeust(SendLocalListRequest request);
}
