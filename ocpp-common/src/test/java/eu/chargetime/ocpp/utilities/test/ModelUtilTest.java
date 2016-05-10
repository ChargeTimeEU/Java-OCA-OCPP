package eu.chargetime.ocpp.utilities.test;

import eu.chargetime.ocpp.utilities.ModelUtil;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Thomas Volden on 10-May-16.
 */
public class ModelUtilTest {

    @Test
    public void isAmong_notPresent_returnsFalse() {
        // Given
        String needle = "not present";
        String[] haystack = {"something", "some other thing"};

        // When
        boolean found = ModelUtil.isAmong(needle, haystack);

        // Then
        assertThat(found, is(false));
    }

    @Test
    public void isAmong_present_returnsTrue() {
        // Given
        String needle = "something";
        String[] haystack = {"something", "some other thing"};

        // When
        boolean found = ModelUtil.isAmong(needle, haystack);

        // Then
        assertThat(found, is(true));
    }

    @Test
    public void isAmong_presentInHaystackWithNull_returnsTrue() {
        // Given
        String needle = "something";
        String[] haystack = {null, "something", "some other thing"};

        // When
        boolean found = ModelUtil.isAmong(needle, haystack);

        // Then
        assertThat(found, is(true));
    }

    @Test
    public void isAmong_null_returnsFalse() {
        // Given
        String[] haystack = {"something", "some other thing"};

        // When
        boolean found = ModelUtil.isAmong(null, haystack);

        // Then
        assertThat(found, is(false));
    }

    @Test
    public void isAmong_haystackIsNull_returnsFalse() {
        // Given
        String someString = "";
        String[] haystack = null;

        // When
        boolean found = ModelUtil.isAmong(someString, haystack);

        // Then
        assertThat(found, is(false));
    }

    @Test
    public void isAmong_needleAndHaystackIsNull_returnsFalse() {
        // When
        boolean found = ModelUtil.isAmong(null, null);

        // Then
        assertThat(found, is(false));
    }

    @Test
    public void isAmong_needleIsNullAndHaystackIsListWithNulls_returnsTrue() {
        // When
        boolean found = ModelUtil.isAmong(null, null, null);

        // Then
        assertThat(found, is(true));
    }

    @Test
    public void isAmong_needleIsNullAndHaystackIsContainsANull_returnsTrue() {
        // When
        boolean found = ModelUtil.isAmong(null, "something", null);

        // Then
        assertThat(found, is(true));
    }
}