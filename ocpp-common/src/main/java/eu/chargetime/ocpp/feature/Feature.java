package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.model.Confirmation;

/**
 * Created by Thomas Volden on 28-Apr-16.
 */
public interface Feature {
    Class<? extends eu.chargetime.ocpp.model.Request> getRequestType();
    Class<? extends Confirmation> getConfirmationType();
    String getAction();
}
