package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.ClearCacheConfirmation;
import eu.chargetime.ocpp.model.ClearCacheRequest;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

/**
 * Created by Thomas Volden on 03-May-16.
 */
public class ClearCacheFeature extends Feature{
    public ClearCacheFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ClearCacheRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearCacheConfirmation.class;
    }

    @Override
    public String getAction() {
        return "ClearCache";
    }
}
