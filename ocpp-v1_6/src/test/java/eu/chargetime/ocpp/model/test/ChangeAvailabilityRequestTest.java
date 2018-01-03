package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.AvailabilityType;
import eu.chargetime.ocpp.model.core.ChangeAvailabilityRequest;
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
 * Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
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
public class ChangeAvailabilityRequestTest {
    private ChangeAvailabilityRequest request;

    @Before
    public void setUp() throws Exception {

        request = new ChangeAvailabilityRequest();
    }

    @Test
    public void setConnectorId_negativeValue_throwsPropertyConstraintException() {
        // Given
        int aNegativeValue = -1;

        // When
        try {
            request.setConnectorId(aNegativeValue);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("connectorId"));
            assertThat(ex.getFieldValue(), equalTo(aNegativeValue));
        }
    }

    @Test
    public void setConnectorId_zero_connectorIdIsSet() throws Exception {
        // Given
        int zero = 0;

        // When
        request.setConnectorId(zero);

        // Then
        assertThat(request.getConnectorId(), is(zero));
    }

    @Test
    public void setConnectorId_positiveValue_connectorIdIsSet() throws Exception {
        // Given
        int aPositiveValue = 42;

        // When
        request.setConnectorId(aPositiveValue);

        // Then
        assertThat(request.getConnectorId(), is(aPositiveValue));
    }

    @Test
    public void setType_availabilityType_typeIsSet() throws Exception {
        // Given
        AvailabilityType availabilityType = AvailabilityType.Operative;

        // When
        request.setType(availabilityType);

        // Then
        assertThat(request.objType(), equalTo(availabilityType));
    }

    @Test
    public void validate_typeAndConnectorIdIsSet_returnsTrue() throws Exception {
        // Given
        request.setType(AvailabilityType.Operative);
        request.setConnectorId(0);

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void validate_onlyTypeIsSet_returnsFalse() throws Exception {
        // Given
        request.setType(AvailabilityType.Operative);

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_connectorIdIsSet_returnsFalse() throws Exception {
        // Given
        request.setConnectorId(0);

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_typeIsNull_returnFalse() {
        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void isTransactionRelated_returnsFalse() {
        // When
        boolean isTransactionRelated = request.transactionRelated();

        // Then
        assertThat(isTransactionRelated, is(false));
    }
}