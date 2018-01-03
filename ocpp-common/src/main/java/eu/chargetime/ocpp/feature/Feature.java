package eu.chargetime.ocpp.feature;

import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

import java.util.UUID;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (C) 2016-2018 Thomas Volden

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

/**
 * Abstract class. Feature ties {@link Request} and {@link Confirmation} types together with an action name.
 * Can handle a incoming request by forwarding it to the feature {@link Profile}.
 */
public abstract class Feature {
    private Profile profile;

    /**
     * Creates link back to the {@link Profile}.
     *
     * @param ownerProfile the {@link Profile} that owns the function.
     */
    public Feature(Profile ownerProfile) {
        profile = ownerProfile;
    }

    /**
     * Calls {@link Profile} to handle a {@link Request}.
     *
     * @param sessionIndex source of the request.
     * @param request the {@link Request} to be handled.
     * @return the {@link Confirmation} to be send back.
     */
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return profile.handleRequest(sessionIndex, request);
    }

    /**
     * Get the {@link Request} {@link java.lang.reflect.Type} for the feature.
     *
     * @return the {@link Request} {@link java.lang.reflect.Type}
     */
    public abstract Class<? extends Request> getRequestType();

    /**
     * Get the {@link Confirmation} {@link java.lang.reflect.Type} for the feature.
     *
     * @return the {@link Confirmation} {@link java.lang.reflect.Type}.
     */
    public abstract Class<? extends Confirmation> getConfirmationType();

    /**
     * Get the action name of the feature.
     *
     * @return the action name.
     */
    public abstract String getAction();
}
