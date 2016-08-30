package eu.chargetime.ocpp.feature.profile;/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>

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

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.AuthorizeRequest;
import eu.chargetime.ocpp.model.core.BootNotificationRequest;

import java.util.HashSet;

public class ServerCoreProfile implements Profile {

    private ServerCoreEventHandler handler;
    private HashSet<Feature> features;

    public ServerCoreProfile(ServerCoreEventHandler handler) {
        this.handler = handler;

        features = new HashSet<>();
        features.add(new AuthorizeFeature(this));
        features.add(new BootNotificationFeature(this));
        features.add(new ChangeAvailabilityFeature(this));
        features.add(new ChangeConfigurationFeature(this));
    }

    @Override
    public Feature[] getFeatureList() {
        return features.toArray(new Feature[0]);
    }

    @Override
    public Confirmation handleRequest(int sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof AuthorizeRequest) {
            result = handler.handleAuthorizeRequest(sessionIndex, (AuthorizeRequest) request);
        } else if (request instanceof BootNotificationRequest) {
            result = handler.handleBootNotificationRequest(sessionIndex, (BootNotificationRequest) request);
        }

        return result;
    }
}
