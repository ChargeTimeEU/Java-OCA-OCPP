package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.model.AuthorizeConfirmation;
import eu.chargetime.ocpp.model.IdTagInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Thomas Volden on 05-May-16.
 */
public class AuthorizeConfirmationTest {
    AuthorizeConfirmation confirmation;

    @Mock
    IdTagInfo infoTag = mock(IdTagInfo.class);

    @Before
    public void setup() throws Exception {

        confirmation = new AuthorizeConfirmation();
    }

    @Test
    public void validate_idTagInfoIsNull_returnsFalse() {
        // when
        boolean isValid = confirmation.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_idTagInfoSet_callsValidateOnIdTagInfo() {
        // Given
        confirmation.setIdTagInfo(infoTag);

        // When
        confirmation.validate();

        // Then
        verify(infoTag, times(1)).validate();
    }

}