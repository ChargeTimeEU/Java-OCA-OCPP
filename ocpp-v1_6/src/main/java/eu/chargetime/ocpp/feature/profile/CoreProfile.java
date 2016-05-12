package eu.chargetime.ocpp.feature.profile;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.model.*;

import java.util.ArrayList;

/**
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
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

    public AuthorizeRequest createAuthorizeRequest(String idToken) throws PropertyConstraintException {
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
