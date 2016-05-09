package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.IdTagInfo;
import org.junit.*;

import java.util.Calendar;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Thomas Volden on 09-May-16.
 */
public class IdTagInfoTest {
    private IdTagInfo idTagInfo;

    @Before
    public void setUp() throws Exception {
        idTagInfo = new IdTagInfo();
    }

    @Test
    public void setStatus_illegalValue_throwsPropertyConstraintException() {
        // Given
        // Legal values: Accepted, Blocked, Expired, Invalid, ConcurrentTx
        String illegalValue = "something";

        // When
        try {
            idTagInfo.setStatus(illegalValue);
            Assert.fail("Expected exception");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("status"));
            assertThat(ex.getFieldValue(), equalTo(illegalValue));
        }
    }

    @Test
    public void setParentIdTag_stringLength21_throwsPropertyConstraintException() {
        // Given
        String illegalValue = "123456789012345678901";

        // When
        try {
            idTagInfo.setParentIdTag(illegalValue);
            Assert.fail("Excepted exception");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("parentIdTag"));
            assertThat(ex.getFieldValue(), equalTo(illegalValue));
        }
    }

    @Test
    public void validate_statusNotSet_returnsFalse() {
        // When
        boolean isValid = idTagInfo.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_statusSet_returnsTrue() throws Exception {
        // Given
        idTagInfo.setStatus("Accepted");

        // When
        boolean isValid = idTagInfo.validate();

        // Then
        assertThat(isValid, is(true));
    }
}