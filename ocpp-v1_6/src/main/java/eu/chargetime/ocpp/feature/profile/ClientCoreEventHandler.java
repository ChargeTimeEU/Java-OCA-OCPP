package eu.chargetime.ocpp.feature.profile;

import eu.chargetime.ocpp.model.*;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public interface ClientCoreEventHandler {
    ChangeAvailabilityConfirmation handleChangeAvailabilityRequest(ChangeAvailabilityRequest request);
    GetConfigurationConfirmation handleGetConfigurationRequest(GetConfigurationRequest request);
}
