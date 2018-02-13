package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionConfirmation;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionRequest;

public class GetLocalListVersionFeature extends Feature  {

	public GetLocalListVersionFeature(Profile ownerProfile) {
		super(ownerProfile);
	}

	@Override
	public Class<? extends Request> getRequestType() {
		return GetLocalListVersionRequest.class;
	}

	@Override
	public Class<? extends Confirmation> getConfirmationType() {
		return GetLocalListVersionConfirmation.class;
	}

	@Override
	public String getAction() {
		return "GetLocalListVersion";
	}
}
