package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.AuthorizeRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
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
        String illegalValue = "1234567890123456789012";

        // When
        try {
            request.setIdTag(illegalValue);

            Assert.fail("Expected exception");
        // Then
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("idTag"));
            assertThat(ex.getFieldValue(), equalTo(illegalValue ));
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
    public void validate_idTagSet_returnTrue() throws Exception{
        // Given
        request.setIdTag("42");

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(true));
    }

}