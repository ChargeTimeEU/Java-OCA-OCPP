package eu.chargetime.ocpp.feature.profile;

import eu.chargetime.ocpp.feature.AuthorizeFeature;
import eu.chargetime.ocpp.feature.BootNotificationFeature;
import eu.chargetime.ocpp.feature.ChangeAvailabilityFeature;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.*;

import java.util.ArrayList;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public class CoreProfile implements Profile
{
    ClientCoreEventHandler eventHandler;
    ArrayList<Feature> features;

    public CoreProfile(ClientCoreEventHandler handler) {
        features = new ArrayList<>();
        eventHandler = handler;

        features.add(new BootNotificationFeature(null));
        features.add(new AuthorizeFeature(null));
        features.add(new ChangeAvailabilityFeature(this));
    }

    public AuthorizeRequest createAuthorizeRequest(String idToken) {
        return new AuthorizeRequest(idToken);
    }

    public BootNotificationRequest createBootNotificationRequest(String vendor, String model) {
        return new BootNotificationRequest(vendor, model);
    }

    @Override
    public Feature[] getFeatureList() {
        return features.toArray(new Feature[0]);
    }

    @Override
    public Confirmation handleRequest(Request request) {
        return eventHandler.handleChangeAvailabilityRequest((ChangeAvailabilityRequest) request);
    }

}
