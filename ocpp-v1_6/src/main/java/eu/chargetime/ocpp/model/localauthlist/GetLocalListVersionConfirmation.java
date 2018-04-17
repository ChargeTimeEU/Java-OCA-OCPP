package eu.chargetime.ocpp.model.localauthlist;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Confirmation;

public class GetLocalListVersionConfirmation implements Confirmation {

	private int listVersion = -2;
	
	public GetLocalListVersionConfirmation() { }

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
     * This contains the current version number of the local authorization list in the Charge Point.
     *
     * @param listVersion int, version of localAuthList.
     */
    public void setListVersion(int listVersion) throws PropertyConstraintException {
        if (listVersion < -1)
            throw new PropertyConstraintException("listVersion", listVersion, "Exceeded limit");
        this.listVersion = listVersion;
    }

	@Override
	public boolean validate() {
		return listVersion >= -1;
	}
}
