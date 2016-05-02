package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.feature.profile.CoreProfile;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.ChangeConfigurationConfirmation;
import eu.chargetime.ocpp.model.ChangeConfigurationRequest;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

/**
 * Created by Thomas Volden on 02-May-16.
 */
public class ChangeConfigurationFeature extends Feature {
    public ChangeConfigurationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    @Override
    public Class<? extends Request> getRequestType() {
        return ChangeConfigurationRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ChangeConfigurationConfirmation.class;
    }

    @Override
    public String getAction() {
        return "ChangeConfiguration";
    }
}
