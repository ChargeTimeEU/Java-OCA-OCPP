package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.BootNotificationRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
public class BootNotificationRequestTest {
    private BootNotificationRequest bootNotificationRequest;

    private String stringLength20 = "12345678901234567890";
    private String stringLength21 = "123456789012345678901";
    private String stringLength25 = "1234567890123456789012345";
    private String stringLength26 = "12345678901234567890123456";

    @Before
    public void setUp() throws Exception {

        bootNotificationRequest = new BootNotificationRequest();
    }

    @Test
    public void setChargeBoxSerialNumber_stringLength25_noExceptions() throws Exception {
        // When
        bootNotificationRequest.setChargeBoxSerialNumber(stringLength25);

        // Then
        assertThat(bootNotificationRequest.getChargeBoxSerialNumber(), equalTo(stringLength25));
    }

    @Test
    public void setChargeBoxSerialNumber_stringLength26_throwsPropertyConstraintException() {
        // When
        try {
            bootNotificationRequest.setChargeBoxSerialNumber(stringLength26);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("chargeBoxSerialNumber"));
            assertThat(ex.getFieldValue(), equalTo(stringLength26));
        }
    }

    @Test
    public void setChargePointModel_stringLength20_noExceptions() throws Exception {
        // When
        bootNotificationRequest.setChargePointModel(stringLength20);

        // Then
        assertThat(bootNotificationRequest.getChargePointModel(), equalTo(stringLength20));
    }

    @Test
    public void setChargePointModel_stringLength21_throwsPropertyConstraintException() {
        // When
        try {
            bootNotificationRequest.setChargePointModel(stringLength21);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("chargePointModel"));
            assertThat(ex.getFieldValue(), equalTo(stringLength21));
        }
    }

    @Test
    public void setChargePointSerialNumber_stringLength25_noExceptions() throws Exception {
        // When
        bootNotificationRequest.setChargePointSerialNumber(stringLength25);

        // Then
        assertThat(bootNotificationRequest.getChargePointSerialNumber(), equalTo(stringLength25));
    }

    @Test
    public void setChargePointSerialNumber_stringLength26_throwsPropertyConstraintException() {
        // When
        try {
            bootNotificationRequest.setChargePointSerialNumber(stringLength26);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("chargePointSerialNumber"));
            assertThat(ex.getFieldValue(), equalTo(stringLength26));
        }
    }

    @Test
    public void setChargePointVendor_stringLength20_noExceptions() throws Exception {
        // When
        bootNotificationRequest.setChargePointVendor(stringLength20);

        // Then
        assertThat(bootNotificationRequest.getChargePointVendor(), equalTo(stringLength20));
    }

    @Test
    public void setChargePointVendor_stringLength21_throwsPropertyConstraintException() {
        // When
        try {
            bootNotificationRequest.setChargePointVendor(stringLength21);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("chargePointVendor"));
            assertThat(ex.getFieldValue(), equalTo(stringLength21));
        }
    }

    @Test
    public void setFirmwareVersion_stringLength50_noException() throws Exception {
        // Given
        String stringLength50 = "12345678901234567890123456789012345678901234567890";

        // When
        bootNotificationRequest.setFirmwareVersion(stringLength50);

        // Then
        assertThat(bootNotificationRequest.getFirmwareVersion(), equalTo(stringLength50));
    }

    @Test
    public void setFirmwareVersion_stringLength51_throwsPropertyConstraintException() {
        // Given
        String stringLength51 = "123456789012345678901234567890123456789012345678901";

        // When
        try {
            bootNotificationRequest.setFirmwareVersion(stringLength51);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("firmwareVersion"));
            assertThat(ex.getFieldValue(), equalTo(stringLength51));
        }
    }

    @Test
    public void setIccid_stringLength20_noExceptions() throws Exception {
        // When
        bootNotificationRequest.setIccid(stringLength20);

        // Then
        assertThat(bootNotificationRequest.getIccid(), equalTo(stringLength20));
    }

    @Test
    public void setIccid_stringLength21_throwsPropertyConstraintException() {
        // When
        try {
            bootNotificationRequest.setIccid(stringLength21);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("iccid"));
            assertThat(ex.getFieldValue(), equalTo(stringLength21));
        }
    }

    @Test
    public void setImsi_stringLength20_noExceptions() throws Exception {
        // When
        bootNotificationRequest.setImsi(stringLength20);

        // Then
        assertThat(bootNotificationRequest.getImsi(), equalTo(stringLength20));
    }

    @Test
    public void setImsi_stringLength21_throwsPropertyConstraintException() {
        // When
        try {
            bootNotificationRequest.setImsi(stringLength21);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("imsi"));
            assertThat(ex.getFieldValue(), equalTo(stringLength21));
        }
    }

    @Test
    public void setMeterSerialNumber_stringLength25_noExceptions() throws Exception {
        // When
        bootNotificationRequest.setMeterSerialNumber(stringLength25);

        // Then
        assertThat(bootNotificationRequest.getMeterSerialNumber(), equalTo(stringLength25));
    }

    @Test
    public void setMeterSerialNumber_stringLength26_throwsPropertyConstraintException() {
        // When
        try {
            bootNotificationRequest.setMeterSerialNumber(stringLength26);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("meterSerialNumber"));
            assertThat(ex.getFieldValue(), equalTo(stringLength26));
        }
    }

    @Test
    public void setMeterType_stringLength25_noExceptions() throws Exception {
        // When
        bootNotificationRequest.setMeterType(stringLength25);

        // Then
        assertThat(bootNotificationRequest.getMeterType(), equalTo(stringLength25));
    }

    @Test
    public void setMeterType_stringLength26_throwsPropertyConstraintException() {
        // When
        try {
            bootNotificationRequest.setMeterType(stringLength26);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("meterType"));
            assertThat(ex.getFieldValue(), equalTo(stringLength26));
        }
    }

    @Test
    public void validate_chargePointVendorAndChargePointModelIsSet_returnsTrue() throws Exception {
        // Given
        bootNotificationRequest.setChargePointModel(stringLength20);
        bootNotificationRequest.setChargePointVendor(stringLength20);

        // When
        boolean isValid = bootNotificationRequest.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void validate_nothingIsSet_returnsFalse() throws Exception {
        // When
        boolean isValid = bootNotificationRequest.validate();

        // Then
        assertThat(isValid, is(false));
    }
}