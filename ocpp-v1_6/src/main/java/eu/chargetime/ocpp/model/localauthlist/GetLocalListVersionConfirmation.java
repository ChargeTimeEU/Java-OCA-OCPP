package eu.chargetime.ocpp.model.localauthlist;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Confirmation;

public class GetLocalListVersionConfirmation implements Confirmation {

    private int listVersion = -2;

    public GetLocalListVersionConfirmation() {
    }

    public GetLocalListVersionConfirmation(int listVersion) {
        this.listVersion = listVersion;
    }

    /**
     * This contains the current version number of the local authorization list in the Charge Point.
     *
     * @return String, version of localAuthList.
     */
    public int getListVersion() {
        return listVersion;
    }

    /**
     * Required.
     * This contains the current version number of the local authorization list
     * in the Charge Point.
     * <p>
     * A version number of 0 (zero) SHALL be used to indicate that the local
     * authorization list is empty, and a version number of -1 SHALL be used to
     * indicate that the Charge Point does not support Local Authorization Lists.
     *
     * @param listVersion int, version of localAuthList.
     */
    public void setListVersion(int listVersion) {
        if (listVersion < -1) {
            throw new PropertyConstraintException(listVersion, "listVersion must be >= -1");
        }
        this.listVersion = listVersion;
    }

    @Override
    public boolean validate() {
        return listVersion >= -1;
    }
}
