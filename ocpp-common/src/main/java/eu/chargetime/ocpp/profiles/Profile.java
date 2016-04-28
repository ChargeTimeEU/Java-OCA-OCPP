package eu.chargetime.ocpp.profiles;

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public interface Profile
{
    Confirmation findConfirmation(Request request);
    Request findRequest(String action);
}
