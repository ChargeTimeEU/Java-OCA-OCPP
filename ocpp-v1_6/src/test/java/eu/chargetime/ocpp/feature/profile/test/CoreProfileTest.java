package eu.chargetime.ocpp.feature.profile.test;

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.feature.Feature;
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
 * Created by Thomas Volden on 25-Apr-16.
 */
public class CoreProfileTest
{
    CoreProfile core;

    @Mock
    ClientCoreEventHandler handler = mock(ClientCoreEventHandler.class);

    @Before
    public void setup() {
        core = new CoreProfile(handler);
    }

    @Test
    public void createAuthorizeRequest_withIdToken_returnsAuthorizeRequestWithIdTag() {
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