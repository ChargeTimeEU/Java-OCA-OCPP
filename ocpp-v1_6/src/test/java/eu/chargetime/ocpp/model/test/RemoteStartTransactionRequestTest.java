package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.ChargingProfile;
import eu.chargetime.ocpp.model.IdToken;
import eu.chargetime.ocpp.model.RemoteStartTransactionRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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
public class RemoteStartTransactionRequestTest {
    RemoteStartTransactionRequest request;

    @Before
    public void setUp() throws Exception {
        request = new RemoteStartTransactionRequest();
    }

    @Test
    public void setConnectorId_integerZero_throwsPropertyConstraintException() {
        // Given
        int zero = 0;

        try {
            // When
            request.setConnectorId(zero);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("connectorId"));
            assertThat(ex.getFieldValue(), equalTo(zero));
        }
    }

    @Test
    public void setConnectorId_positiveInteger_connectorIdIsSet() throws Exception {
        // Given
        int someInteger = 42;

        // When
        request.setConnectorId(someInteger);

        // Then
        assertThat(request.getConnectorId(), equalTo(someInteger));
    }

    @Test
    public void setIdTag_someIdToken_idTagIsSet() throws Exception {
        // Given
        IdToken idToken = mock(IdToken.class);

        // When
        request.setIdTag(idToken);

        // Then
        assertThat(request.getIdTag(), equalTo(idToken));
    }

    @Test
    public void setChargingProfile_someChargingProfile_chargingProfileIsSet() throws Exception {
        // Given
        ChargingProfile chargingProfile = mock(ChargingProfile.class);

        // When
        request.setChargingProfile(chargingProfile);

        // Then
        assertThat(request.getChargingProfile(), equalTo(chargingProfile));
    }

    @Test
    public void validate_returnFalse() {
        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_idTagIsSet_returnTrue() throws Exception {
        // Given
        IdToken idToken = mock(IdToken.class);
        request.setIdTag(idToken);
        when(idToken.validate()).thenReturn(true);

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void validate_idTagIsSet_idTagIsValidated() throws Exception {
        // Given
        IdToken idToken = mock(IdToken.class);
        request.setIdTag(idToken);

        // When
        request.validate();

        // Then
        verify(idToken, times(1)).validate();
    }

    @Test
    public void validate_chargingProfileIsSet_chargingsProfileIsValidated() throws Exception {
        // Given
        ChargingProfile chargingProfile = mock(ChargingProfile.class);
        request.setChargingProfile(chargingProfile);

        // When
        request.validate();

        // Then
        verify(chargingProfile, times(1)).validate();
    }
}