package eu.chargetime.ocpp.feature.profile.test;

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
    public void handleRequest_aChangeAvailabilityRequest_callsHandleBootNotificationRequest() {
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
}