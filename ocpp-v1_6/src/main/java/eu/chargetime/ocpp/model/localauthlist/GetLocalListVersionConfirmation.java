package eu.chargetime.ocpp.model.localauthlist;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Confirmation;

public class GetLocalListVersionConfirmation implements Confirmation {

	private int listVersion;
	
	public GetLocalListVersionConfirmation() {
		listVersion = -2;
	}
	
	public void setListVersion(int listVersion) throws PropertyConstraintException {
		if (listVersion < -1) throw new PropertyConstraintException("listVersion", listVersion, "Exceeded limit");
		this.listVersion = listVersion;
	}
	
	public int getListVersion() {
		return listVersion;
	}
	
	@Override
	public boolean validate() {
		return listVersion >= -1;
	}
}
