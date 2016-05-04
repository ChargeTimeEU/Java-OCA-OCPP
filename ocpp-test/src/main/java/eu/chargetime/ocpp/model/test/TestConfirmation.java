package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.model.Confirmation;

/**
 * Created by Thomas Volden on 30-Apr-16.
 */
public class TestConfirmation implements Confirmation {
    @Override
    public boolean validate() {
        return false;
    }
}
