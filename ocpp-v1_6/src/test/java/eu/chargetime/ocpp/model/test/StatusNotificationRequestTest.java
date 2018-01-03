package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.ChargePointErrorCode;
import eu.chargetime.ocpp.model.core.ChargePointStatus;
import eu.chargetime.ocpp.model.core.StatusNotificationRequest;
import eu.chargetime.ocpp.utilities.TestUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

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
public class StatusNotificationRequestTest extends TestUtilities {
    StatusNotificationRequest request;

    @Before
    public void setUp() throws Exception {
        request = new StatusNotificationRequest();
    }

    @Test
    public void setConnectorId_negativeInteger_throwsPropertyConstraintException() {
        // Given
        Integer negativeValue = -42;

        try {
            // When
            request.setConnectorId(negativeValue);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("connectorId"));
            assertThat(ex.getFieldValue(), equalTo(negativeValue));
        }
    }

    @Test
    public void setConnectorId_zeroInteger_connectorIdIsSet() throws Exception {
        // Given
        Integer zeroValue = 0;

        // When
        request.setConnectorId(zeroValue);

        // Then
        assertThat(request.getConnectorId(), equalTo(zeroValue));
    }

    @Test
    public void setErrorCode_chargePointErrorCode_errorCodeIsSet() throws Exception {
        // Given
        ChargePointErrorCode chargePointErrorCode = ChargePointErrorCode.NoError;

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.objErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setInfo_stringLength51_throwsPropertyConstraintException() {
        // Given
        String length51 = aString(51);

        try {
            // When
            request.setInfo(length51);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("info"));
            assertThat(ex.getFieldValue(), equalTo(length51));
        }
    }

    @Test
    public void setInfo_stringLength50_infoIsSet() throws Exception {
        // Given
        String length50 = aString(50);

        // When
        request.setInfo(length50);

        // Then
        assertThat(request.getInfo(), equalTo(length50));
    }

    @Test
    public void setStatus_chargePointStatus_statusIsSet() throws Exception {
        // Given
        ChargePointStatus chargePointStatus = ChargePointStatus.Available;

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.objStatus(), equalTo(chargePointStatus));
    }

    @Test
    public void setTimestamp_calendarNow_timestampIsSet() {
        // Given
        Calendar now = Calendar.getInstance();

        // When
        request.setTimestamp(now);

        // Then
        assertThat(request.objTimestamp(), equalTo(now));
    }

    @Test
    public void setVendorId_stringLength256_throwsPropertyConstraintException() {
        // Given
        String length256 = aString(256);

        try {
            // When
            request.setVendorId(length256);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("vendorId"));
            assertThat(ex.getFieldValue(), equalTo(length256));
        }
    }

    @Test
    public void setVendorId_stringLength255_vendorIdIsSet() throws Exception {
        // Given
        String length255 = aString(255);

        // When
        request.setVendorId(length255);

        // Then
        assertThat(request.getVendorId(), equalTo(length255));
    }

    @Test
    public void setVendorErrorCode_stringLength51_throwsPropertyConstraintException() {
        // Given
        String length51 = aString(51);

        try {
            // When
            request.setVendorErrorCode(length51);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("vendorErrorCode"));
            assertThat(ex.getFieldValue(), equalTo(length51));
        }
    }

    @Test
    public void setVendorErrorCode_stringLength50_vendorErrorCodeIsSet() throws Exception {
        // Given
        String length50 = aString(50);

        // When
        request.setVendorErrorCode(length50);

        // Then
        assertThat(request.getVendorErrorCode(), equalTo(length50));
    }

    @Test
    public void validate_returnFalse() {
        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_connectorIdAndErrorCodeAndStatusIsSet_returnTrue() throws Exception {
        // Given
        request.setConnectorId(42);
        request.setErrorCode(ChargePointErrorCode.NoError);
        request.setStatus(ChargePointStatus.Available);

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void isTransactionRelated_returnsFalse() {
        // When
        boolean isTransactionRelated = request.transactionRelated();

        // Then
        assertThat(isTransactionRelated, is(false));
    }
}