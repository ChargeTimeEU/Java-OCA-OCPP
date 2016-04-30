package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.BootNotificationConfirmation;
import eu.chargetime.ocpp.model.BootNotificationRequest;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

/**
 * Created by Thomas Volden on 28-Apr-16.
 */
public class BootNotificationFeature extends Feature {

    public BootNotificationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return BootNotificationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return BootNotificationConfirmation.class;
    }

    @Override
    public String getAction() {
        return "BootNotification";
    }
}
