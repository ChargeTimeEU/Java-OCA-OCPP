package eu.chargetime.ocpp.feature.profile;

import eu.chargetime.ocpp.feature.*;
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
        features.add(new GetConfigurationFeature(this));
        features.add(new ChangeConfigurationFeature(this));
        features.add(new ClearCacheFeature(this));
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
        Confirmation result = null;

        if (request instanceof ChangeAvailabilityRequest) {
            result = eventHandler.handleChangeAvailabilityRequest((ChangeAvailabilityRequest) request);
        }
        else if (request instanceof GetConfigurationRequest) {
            result = eventHandler.handleGetConfigurationRequest((GetConfigurationRequest) request);
        }
        else if (request instanceof ChangeConfigurationRequest) {
            result = eventHandler.handleChangeConfigurationRequest((ChangeConfigurationRequest) request);
        }
        else if (request instanceof ClearCacheRequest) {
            result = eventHandler.handleClearCacheRequest((ClearCacheRequest) request);
        }

        return result;
    }
}
