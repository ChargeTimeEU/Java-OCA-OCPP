package eu.chargetime.ocpp.feature.profile;

import java.util.ArrayList;
import java.util.UUID;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.GetLocalListVersionFeature;
import eu.chargetime.ocpp.feature.SendLocalListFeature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionRequest;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;

public class ClientLocalAuthListProfile implements Profile {
	
	ClientLocalAuthListEventHandler eventHandler;
	ArrayList<Feature> featureList;

	public ClientLocalAuthListProfile(ClientLocalAuthListEventHandler handler) {
		eventHandler = handler;

		featureList = new ArrayList<>();
		featureList.add(new GetLocalListVersionFeature(this));
		featureList.add(new SendLocalListFeature(this));
	}
	
	@Override
	public Feature[] getFeatureList() {
		return featureList.toArray(new Feature[0]);
	}

	@Override
	public Confirmation handleRequest(UUID sessionIndex, Request request) {
		Confirmation result = null;

		if(request instanceof GetLocalListVersionRequest) {
			result = eventHandler.handleGetLocalListVersionRequest((GetLocalListVersionRequest)request);
		} else if (request instanceof SendLocalListRequest) {
			result = eventHandler.handleSendLocalListReqeust((SendLocalListRequest) request); 
		}
		
		return result;
	}
}
