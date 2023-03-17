package eu.chargetime.ocpp.model.response;

import eu.chargetime.ocpp.model.Confirmation;

public class ClearedChargingLimitResponse extends Confirmation {
    @Override
    public boolean validate() {
        return true;
    }
}
