package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListConfirmation;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;

public class SendLocalListFeature extends Feature  {

	public SendLocalListFeature(Profile ownerProfile) {
		super(ownerProfile);
	}

	@Override
	public Class<? extends Request> getRequestType() {
		return SendLocalListRequest.class;
	}

	@Override
	public Class<? extends Confirmation> getConfirmationType() {
		return SendLocalListConfirmation.class;
	}

	@Override
	public String getAction() {
		return "SendLocalList";
	}

}
