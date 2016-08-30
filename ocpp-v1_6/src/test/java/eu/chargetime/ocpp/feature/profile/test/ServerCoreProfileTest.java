package eu.chargetime.ocpp.feature.profile.test;

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import eu.chargetime.ocpp.model.core.AuthorizeRequest;
import eu.chargetime.ocpp.model.core.BootNotificationRequest;
import eu.chargetime.ocpp.model.core.DataTransferRequest;
import eu.chargetime.ocpp.model.core.HeartbeatRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/*
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
public class ServerCoreProfileTest {

    private ServerCoreProfile core;

    @Mock
    ServerCoreEventHandler handler = mock(ServerCoreEventHandler.class);

    @Before
    public void setup() {
        core = new ServerCoreProfile(handler);
    }

    @Test
    public void getFeatureList_containsAuthorizeFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // Then
        assertThat(findFeature(features, "Authorize"), is(instanceOf(AuthorizeFeature.class)));
    }

    @Test
    public void handleRequest_anAuthorizeRequest_callsHandleAuthorizeRequest() {
        // Given
        AuthorizeRequest request = new AuthorizeRequest();
        int sessionId = 42;

        // When
        core.handleRequest(sessionId, request);

        // Then
        verify(handler, times(1)).handleAuthorizeRequest(eq(sessionId), eq(request));
    }

    @Test
    public void getFeatureList_containsBootNotificatonFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // Then
        assertThat(findFeature(features, "BootNotification"), is(instanceOf(BootNotificationFeature.class)));
    }

    @Test
    public void handleRequest_aBootNotificationRequest_callsHandleBootNotificationRequest() {
        // Given
        BootNotificationRequest request = new BootNotificationRequest();
        int sessionId = 42;

        // When
        core.handleRequest(sessionId, request);

        // Then
        verify(handler, times(1)).handleBootNotificationRequest(eq(sessionId), eq(request));
    }

    @Test
    public void getFeatureList_containsChangeAvailabilityFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // Then
        assertThat(findFeature(features, "ChangeAvailability"), is(instanceOf(ChangeAvailabilityFeature.class)));
    }

    @Test
    public void getFeatureList_containsChangeConfigurationFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // Then
        assertThat(findFeature(features, "ChangeConfiguration"), is(instanceOf(ChangeConfigurationFeature.class)));
    }

    @Test
    public void getFeatureList_containsClearCacheFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // Then
        assertThat(findFeature(features, "ClearCache"), is(instanceOf(ClearCacheFeature.class)));
    }

    @Test
    public void getFeatureList_containsDataTransferFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // Then
        assertThat(findFeature(features, "DataTransfer"), is(instanceOf(DataTransferFeature.class)));
    }

    @Test
    public void handleRequest_aDataTransferRequest_callsHandleDataTransferRequest() {
        // Given
        DataTransferRequest request = new DataTransferRequest();
        int sessionId = 42;

        // When
        core.handleRequest(sessionId, request);

        // Then
        verify(handler, times(1)).handleDataTransferRequest(eq(sessionId), eq(request));
    }

    @Test
    public void getFeatureList_containsGetConfigurationFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // Then
        assertThat(findFeature(features, "GetConfiguration"), is(instanceOf(GetConfigurationFeature.class)));
    }

    @Test
    public void getFeatureList_containsHeartbeatFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // Then
        assertThat(findFeature(features, "Heartbeat"), is(instanceOf(HeartbeatFeature.class)));
    }

    @Test
    public void handleRequest_aHeartbeatRequest_callsHandleHeartbeatRequest() {
        // Given
        HeartbeatRequest request = new HeartbeatRequest();
        int sessionId = 42;

        // When
        core.handleRequest(sessionId, request);

        // Then
        verify(handler, times(1)).handleHeartbeatRequest(eq(sessionId), eq(request));
    }

    private Feature findFeature(Feature[] features, String action) {
        Feature output = null;
        for (Feature feature : features) {
            if (action.equals(feature.getAction())) {
                output = feature;
                break;
            }
        }
        return output;
    }
}