package eu.chargetime.ocpp.model.request;

import eu.chargetime.ocpp.model.RequestWithId;

public class ClearCacheRequest extends RequestWithId {
    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
