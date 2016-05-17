package eu.chargetime.ocpp.feature.profile.test;

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.feature.profile.ClientCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.CoreProfile;
import eu.chargetime.ocpp.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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
public class CoreProfileTest
{
    private CoreProfile core;

    @Mock
    ClientCoreEventHandler handler = mock(ClientCoreEventHandler.class);

    @Before
    public void setup() {
        core = new CoreProfile(handler);
    }

    @Test
    public void createAuthorizeRequest_withIdToken_returnsAuthorizeRequestWithIdTag() throws Exception {
        // Given
        String legalIdToken = "test123";

        // When
        AuthorizeRequest result = core.createAuthorizeRequest(legalIdToken);

        // Then
        assertThat(result.getIdTag(), is(legalIdToken));
    }

    @Test
    public void createBootNotification_withVendorAndModel_returnsBootNotificationRequestWithVendorAndModel() {
        // Given
        String legalVendor = "vendor";
        String legalModel = "model";

        // When
        BootNotificationRequest result = core.createBootNotificationRequest(legalVendor, legalModel);

        // Then
        assertThat(result.getChargePointVendor(), is(legalVendor));
        assertThat(result.getChargePointModel(), is(legalModel));
    }

    @Test
    public void getFeatureList_containsBootNotificationFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // then
        assertThat(findFeature(features, "BootNotification"), is(instanceOf(BootNotificationFeature.class)));
    }

    @Test
    public void getFeatureList_containsAuthorizeFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // then
        assertThat(findFeature(features, "Authorize"), is(instanceOf(AuthorizeFeature.class)));
    }

    @Test
    public void handleRequest_aChangeAvailabilityRequest_callsHandleChangeAvailabilityRequest() {
        // Given
        ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();

        // When
        core.handleRequest(request);

        // Then
        verify(handler, times(1)).handleChangeAvailabilityRequest(eq(request));
    }

    @Test
    public void handleRequest_aChangeAvailabilityRequest_returnsChangeAvailabilityConfirmation() {
        // Given
        when(handler.handleChangeAvailabilityRequest(any())).thenReturn(new ChangeAvailabilityConfirmation());
        ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();

        // When
        Confirmation conf = core.handleRequest(request);

        // Then
        assertThat(conf, instanceOf(ChangeAvailabilityConfirmation.class));
    }

    @Test
    public void getFeatureList_containsChangeAvailabilityFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // then
        assertThat(findFeature(features, "ChangeAvailability"), is(instanceOf(ChangeAvailabilityFeature.class)));
    }


    @Test
    public void handleRequest_aGetConfigurationRequest_callsHandleGetConfigurationRequest() {
        // Given
        GetConfigurationRequest request = new GetConfigurationRequest();

        // When
        core.handleRequest(request);

        // Then
        verify(handler, times(1)).handleGetConfigurationRequest(eq(request));
    }

    @Test
    public void handleRequest_aGetConfigurationRequest_returnGetConfigurationConfirmation() {
        // Given
        when(handler.handleGetConfigurationRequest(any())).thenReturn(new GetConfigurationConfirmation());
        GetConfigurationRequest request = new GetConfigurationRequest();

        // When
        Confirmation conf = core.handleRequest(request);

        // Then
        assertThat(conf, instanceOf(GetConfigurationConfirmation.class));
    }

    @Test
    public void handleRequest_aDataTransferRequest_callsHandleDataTransferRequest() {
        // Given
        DataTransferRequest request = new DataTransferRequest();

        // When
        core.handleRequest(request);

        // Then
        verify(handler, times(1)).handleDataTransferRequest(eq(request));
    }

    @Test
    public void handleRequest_aDataTransferRequest_returnDataTransferConfirmation() {
        // Given
        when(handler.handleDataTransferRequest(any())).thenReturn(new DataTransferConfirmation());
        DataTransferRequest request = new DataTransferRequest();

        // When
        Confirmation conf = core.handleRequest(request);

        // Then
        assertThat(conf, instanceOf(DataTransferConfirmation.class));
    }

    @Test
    public void getFeatureList_containsGetConfigurationFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // then
        assertThat(findFeature(features, "GetConfiguration"), is(instanceOf(GetConfigurationFeature.class)));
    }

    @Test
    public void handleRequest_aChangeConfigurationRequest_callsHandleChangeConfigurationRequest() {
        // Given
        ChangeConfigurationRequest request = new ChangeConfigurationRequest();

        // When
        core.handleRequest(request);

        // Then
        verify(handler, times(1)).handleChangeConfigurationRequest(eq(request));
    }

    @Test
    public void handleRequest_aChangeConfigurationRequest_returnsChangeConfigurationConfirmation() {
        // Given
        when(handler.handleChangeConfigurationRequest(any())).thenReturn(new ChangeConfigurationConfirmation());
        ChangeConfigurationRequest request = new ChangeConfigurationRequest();

        // When
        Confirmation conf = core.handleRequest(request);

        // Then
        assertThat(conf, instanceOf(ChangeConfigurationConfirmation.class));
    }

    @Test
    public void getFeatureList_containsChangeConfigurationFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // then
        assertThat(findFeature(features, "ChangeConfiguration"), is(instanceOf(ChangeConfigurationFeature.class)));
    }

    @Test
    public void handleRequest_aClearCacheRequest_callsHandleClearCacheRequest() {
        // Given
        ClearCacheRequest request = new ClearCacheRequest();

        // When
        core.handleRequest(request);

        // Then
        verify(handler, times(1)).handleClearCacheRequest(request);
    }

    @Test
    public void handleRequest_aClearCacheRequest_returnsClearCacheConfirmation() {
        // Given
        when(handler.handleClearCacheRequest(any())).thenReturn(new ClearCacheConfirmation());
        ClearCacheRequest request = new ClearCacheRequest();

        // When
        Confirmation conf = core.handleRequest(request);

        // Then
        assertThat(conf, instanceOf(ClearCacheConfirmation.class));
    }

    @Test
    public void getFeatureList_containsClearCacheFeature() {
        // When
        Feature[] features = core.getFeatureList();

        // then
        assertThat(findFeature(features, "ClearCache"), is(instanceOf(ClearCacheFeature.class)));
    }

    @Test
    public void getFeatureList_containsDataTransfer() {
        // When
        Feature[] features = core.getFeatureList();

        // Then
        assertThat(findFeature(features, "DataTransfer"), is(instanceOf(DataTransferFeature.class)));
    }

    private Feature findFeature(Feature[] features, String action) {
        Feature output = null;
        for (Feature feature: features) {
            if (action.equals(feature.getAction())) {
                output = feature;
                break;
            }
        }
        return output;
    }
}