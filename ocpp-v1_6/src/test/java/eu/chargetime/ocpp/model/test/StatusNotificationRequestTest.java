package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.StatusNotificationRequest;
import eu.chargetime.ocpp.test.TestUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ChargeTime.eu - Java-OCA-OCPP
 * <p>
 * MIT License
 * <p>
 * Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
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
    public void setErrorCode_illegal_throwsPropertyConstraintException() {
        // Given
        String illegal = "some error code";

        try {
            // When
            request.setErrorCode(illegal);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("errorCode"));
            assertThat(ex.getFieldValue(), equalTo(illegal));
        }
    }

    @Test
    public void setErrorCode_connectorLockFailure_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "ConnectorLockFailure";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_evCommunicationError_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "EVCommunicationError";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_groundFailure_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "GroundFailure";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_highTemperature_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "HighTemperature";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_internalError_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "InternalError";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_localListConflict_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "LocalListConflict";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_noError_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "NoError";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_otherError_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "OtherError";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_overCurrentFailure_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "OverCurrentFailure";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_overVoltage_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "OverVoltage";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_powerMeterFailure_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "PowerMeterFailure";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_powerSwitchFailure_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "PowerSwitchFailure";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_readerFailure_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "ReaderFailure";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_resetFailure_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "ResetFailure";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_underVoltage_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "UnderVoltage";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
    }

    @Test
    public void setErrorCode_weakSignal_errorCodeIsSet() throws Exception {
        // Given
        String chargePointErrorCode = "WeakSignal";

        // When
        request.setErrorCode(chargePointErrorCode);

        // Then
        assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
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
    public void setStatus_illegal_throwsPropertyConstraintException() {
        // Given
        String illegal = "some status";

        try {
            // When
            request.setStatus(illegal);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("status"));
            assertThat(ex.getFieldValue(), equalTo(illegal));
        }
    }

    @Test
    public void setStatus_available_statusIsSet() throws Exception {
        // Given
        String chargePointStatus = "Available";

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.getStatus(), equalTo(chargePointStatus));
    }

    @Test
    public void setStatus_preparing_statusIsSet() throws Exception {
        // Given
        String chargePointStatus = "Preparing";

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.getStatus(), equalTo(chargePointStatus));
    }

    @Test
    public void setStatus_charging_statusIsSet() throws Exception {
        // Given
        String chargePointStatus = "Charging";

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.getStatus(), equalTo(chargePointStatus));
    }

    @Test
    public void setStatus_suspendedEVSE_statusIsSet() throws Exception {
        // Given
        String chargePointStatus = "SuspendedEVSE";

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.getStatus(), equalTo(chargePointStatus));
    }

    @Test
    public void setStatus_suspendedEV_statusIsSet() throws Exception {
        // Given
        String chargePointStatus = "SuspendedEV";

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.getStatus(), equalTo(chargePointStatus));
    }

    @Test
    public void setStatus_finishing_statusIsSet() throws Exception {
        // Given
        String chargePointStatus = "Finishing";

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.getStatus(), equalTo(chargePointStatus));
    }

    @Test
    public void setStatus_reserved_statusIsSet() throws Exception {
        // Given
        String chargePointStatus = "Reserved";

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.getStatus(), equalTo(chargePointStatus));
    }

    @Test
    public void setStatus_unavailable_statusIsSet() throws Exception {
        // Given
        String chargePointStatus = "Unavailable";

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.getStatus(), equalTo(chargePointStatus));
    }

    @Test
    public void setStatus_faulted_statusIsSet() throws Exception {
        // Given
        String chargePointStatus = "Faulted";

        // When
        request.setStatus(chargePointStatus);

        // Then
        assertThat(request.getStatus(), equalTo(chargePointStatus));
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
        request.setErrorCode("NoError");
        request.setStatus("Available");

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(true));
    }
}