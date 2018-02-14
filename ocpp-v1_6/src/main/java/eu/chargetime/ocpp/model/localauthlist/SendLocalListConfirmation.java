package eu.chargetime.ocpp.model.localauthlist;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Confirmation;

public class SendLocalListConfirmation implements Confirmation {

	private UpdateStatus status = null;
	
	public void setStatus(UpdateStatus status) {
		if(status != null) new PropertyConstraintException("status", status, "is null");
		this.status = status;
	}
	
	public UpdateStatus getStatus() {
		return status;
	}
	
	@Override
	public boolean validate() {
		return status != null;
	}
}
