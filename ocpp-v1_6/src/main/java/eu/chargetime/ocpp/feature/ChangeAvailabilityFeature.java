package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.ChangeAvailabilityConfirmation;
import eu.chargetime.ocpp.model.ChangeAvailabilityRequest;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

/**
 * Created by Thomas Volden on 30-Apr-16.
 */
public class ChangeAvailabilityFeature extends Feature {
    public ChangeAvailabilityFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ChangeAvailabilityRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ChangeAvailabilityConfirmation.class;
    }

    @Override
    public String getAction() {
        return "ChangeAvailability";
    }
}
