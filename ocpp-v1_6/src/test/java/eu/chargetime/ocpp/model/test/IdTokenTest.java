package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.IdToken;
import eu.chargetime.ocpp.utilities.TestUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class IdTokenTest extends TestUtilities {
    IdToken idToken;

    @Before
    public void setUp() throws Exception {
        idToken = new IdToken();
    }

    @Test
    public void setIdToken_stringLength21_throwsPropertyConstraintException() {
        // Given
        String length21 = aString(21);

        try {
            // When
            idToken.setIdToken(length21);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("idToken"));
            assertThat(ex.getFieldValue(), equalTo(length21));
        }
    }

    @Test
    public void setIdToken_stringLength20_idTokenIsSet() throws Exception {
        // Given
        String length20 = aString(20);

        // When
        idToken.setIdToken(length20);

        // Then
        assertThat(idToken.getIdToken(), equalTo(length20));
    }

    @Test
    public void validate_returnFalse() {
        // When
        boolean isValid = idToken.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_idTokenIsSet_returnTrue() throws Exception {
        // Given
        idToken.setIdToken("some id");

        // When
        boolean isValid = idToken.validate();

        // Then
        assertThat(isValid, is(true));
    }
}