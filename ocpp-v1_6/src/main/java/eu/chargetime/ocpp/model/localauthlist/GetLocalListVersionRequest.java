package eu.chargetime.ocpp.model.localauthlist;

import eu.chargetime.ocpp.model.Request;

public class GetLocalListVersionRequest implements Request {

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public boolean transactionRelated() {
		return false;
	}
}
