package eu.chargetime.ocpp.profiles.test;

import eu.chargetime.ocpp.model.*;
import eu.chargetime.ocpp.profiles.ClientCoreEventHandler;
import eu.chargetime.ocpp.profiles.CoreProfile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public class CoreProfileTest
{
    CoreProfile core;

    @Mock
    ClientCoreEventHandler handler;

    @Before
    public void setup() {
        handler = mock(ClientCoreEventHandler.class);
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
    public void findConfirmation_withBootNotificationRequest_returnsBootNotificationConfirmation() {
        // Given
        BootNotificationRequest request = core.createBootNotificationRequest("", "");

        // When
        Confirmation conf = core.findConfirmation(request);

        // Then
        assertThat(conf, instanceOf(BootNotificationConfirmation.class));
    }

    @Test
    public void findConfirmation_withAuthorizeRequest_returnsAuthorizeConfirmation() {
        // Given
        AuthorizeRequest request = core.createAuthorizeRequest("");

        // When
        Confirmation conf = core.findConfirmation(request);

        // Then
        assertThat(conf, instanceOf(AuthorizeConfirmation.class));
    }


}