package eu.chargetime.ocpp.feature.profile;

import eu.chargetime.ocpp.feature.AuthorizeFeature;
import eu.chargetime.ocpp.feature.BootNotificationFeature;
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

        features.add(new BootNotificationFeature());
        features.add(new AuthorizeFeature());
    }

    public AuthorizeRequest createAuthorizeRequest(String idToken) {
        return new AuthorizeRequest(idToken);
    }

    public BootNotificationRequest createBootNotificationRequest(String vendor, String model) {
        return new BootNotificationRequest(vendor, model);
    }

    public Confirmation findConfirmation(Request request) {
        Confirmation output = null;
        if (request instanceof BootNotificationRequest) {
            output = new BootNotificationConfirmation();
        }
        else if (request instanceof AuthorizeRequest) {
            output = new AuthorizeConfirmation();
        }
        return output;
    }

    public Request findRequest(String action) {
        Request output = null;
        if ("ChangeAvailability".equals(action)) {
            output = new ChangeAvailabilityRequest();
        }
        return output;
    }

    @Override
    public Feature[] getFeatureList() {
        return features.toArray(new Feature[0]);
    }
}
