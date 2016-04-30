package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

/**
 * Created by Thomas Volden on 28-Apr-16.
 */
public abstract class Feature {
    private Profile profile;

    public Feature(Profile ownerProfile) {
        profile = ownerProfile;
    }

    public Confirmation handleRequest(Request request) {
        return profile.handleRequest(request);
    }

    public abstract Class<? extends Request> getRequestType();
    public abstract Class<? extends Confirmation> getConfirmationType();
    public abstract String getAction();
}
