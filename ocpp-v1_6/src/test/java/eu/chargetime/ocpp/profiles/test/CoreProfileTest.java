package eu.chargetime.ocpp.profiles.test;

import eu.chargetime.ocpp.model.AuthorizeRequest;
import eu.chargetime.ocpp.model.BootNotificationRequest;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.profiles.CoreProfile;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public class CoreProfileTest
{

    CoreProfile core;

    @Before
    public void setup() {
        core = new CoreProfile();
    }

    @Test
    public void createAuthorizeRequest_withLegalIdToken_returnsAuthorizeRequestWithIdTag() {
        // Given
        String legalIdToken = "test123";

        // When
        AuthorizeRequest result = core.createAuthorizeRequest(legalIdToken);

        // Then
        assertThat(result.getIdTag(), is(legalIdToken));
    }

    @Test
    public void createBootNotification_withLegalVendorAndModel_returnsBootNotificationRequestWithVendorAndModel() {
        // Given
        String legalVendor = "vendor";
        String legalModel = "model";

        // When
        BootNotificationRequest result = core.createBootNotificationRequest(legalVendor, legalModel);

        // Then
        assertThat(result.getChargePointVendor(), is(legalVendor));
        assertThat(result.getChargePointModel(), is(legalModel));
    }

}