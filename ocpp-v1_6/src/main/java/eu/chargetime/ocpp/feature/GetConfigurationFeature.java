package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.feature.profile.CoreProfile;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.GetConfigurationConfirmation;
import eu.chargetime.ocpp.model.GetConfigurationRequest;
import eu.chargetime.ocpp.model.Request;

/**
 * Created by Thomas Volden on 01-May-16.
 */
public class GetConfigurationFeature extends Feature {
    public GetConfigurationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return GetConfigurationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetConfigurationConfirmation.class;
    }

    @Override
    public String getAction() {
        return "GetConfiguration";
    }
}
