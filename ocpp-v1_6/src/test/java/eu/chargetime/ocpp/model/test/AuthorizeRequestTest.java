package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.AuthorizeRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by Thomas Volden on 04-May-16.
 */
public class AuthorizeRequestTest {

    AuthorizeRequest request;

    @Before
    public void setup() {
        request = new AuthorizeRequest();
    }

    @Test
    public void setIdToken_exceed20chars_throwsPropertyConstraintException() {
        // Given
        String nullValue = "1234567890123456789012";

        // When
        try {
            request.setIdTag(nullValue);

        // Then
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("idTag"));
            assertThat(ex.getFieldValue(), equalTo(nullValue));
        }

    }

    @Test
    public void setIdToken_legalValue_storedValue() {
        // Given
        String legalValue = "12345678901234567890";

        // When
        try {
            request.setIdTag(legalValue);

        // Then
            assertThat(request.getIdTag(), equalTo(legalValue));
        } catch (Exception ex) {
            Assert.fail("Unexpected exception was thrown");
        }
    }

    @Test
    public void validate_idTagIsNull_returnFalse() {
        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_idTagSet_returnFalse() throws Exception{
        // Given
        request.setIdTag("42");

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(false));
    }

}