package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.DataTransferRequest;
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
public class DataTransferRequestTest extends TestUtilities {
    DataTransferRequest request;

    @Before
    public void setUp() throws Exception {
        request = new DataTransferRequest();
    }

    @Test
    public void setVendorId_stringLength256_throwsPropertyConstraintException() {
        // Given
        String aString = aString(256);

        try {
            // When
            request.setVendorId(aString);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("vendorId"));
            assertThat(ex.getFieldValue(), equalTo(aString));
        }
    }

    @Test
    public void setVendorId_stringLength255_vendorIdIsSet() throws Exception {
        // Given
        String aString = aString(255);

        // When
        request.setVendorId(aString);

        // Then
        assertThat(request.getVendorId(), equalTo(aString));
    }

    @Test
    public void setMessageId_stringLength51_throwsPropertyConstraintException() {
        // given
        String aString = aString(51);

        try {
            // When
            request.setMessageId(aString);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("messageId"));
            assertThat(ex.getFieldValue(), equalTo(aString));
        }
    }

    @Test
    public void setMessageId_stringLength50_messageIdIsSet() throws Exception {
        // Given
        String aString = aString(50);

        // When
        request.setMessageId(aString);

        // Then
        assertThat(request.getMessageId(), equalTo(aString));
    }

    @Test
    public void setData_aString_dataIsSet() {
        // Given
        String aString = "some string";

        // When
        request.setData(aString);

        // Then
        assertThat(request.getData(), equalTo(aString));
    }

    @Test
    public void validate_vendorIdIsSet_returnTrue() throws Exception {
        // Given
        request.setVendorId("Some vendor id");

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void validate_returnFalse() {
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