package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.IdToken;
import eu.chargetime.ocpp.model.MeterValue;
import eu.chargetime.ocpp.model.StopTransactionRequest;
import eu.chargetime.ocpp.utilities.TestUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

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
public class StopTransactionRequestTest extends TestUtilities {
    StopTransactionRequest request;

    @Before
    public void setUp() throws Exception {
        request = new StopTransactionRequest();
    }

    @Test
    public void setIdTag_anIdToken_idTagIsSet() {
        // Given
        IdToken idToken = mock(IdToken.class);

        // When
        request.setIdTag(idToken);

        // Then
        assertThat(request.getIdTag(), equalTo(idToken));
    }

    @Test
    public void setMeterStop_anInteger_meterStopIsSet() {
        // Given
        Integer anInteger = 42;

        // When
        request.setMeterStop(anInteger);

        // Then
        assertThat(request.getMeterStop(), equalTo(anInteger));
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
    public void setTransactionId_anInteger_transactionIdIsSet() {
        // Given
        Integer anInteger = 42;

        // When
        request.setTransactionId(anInteger);

        // Then
        assertThat(request.getTransactionId(), equalTo(anInteger));
    }

    @Test
    public void setReason_illegalString_throwsPropertyConstraintException() {
        // Given
        String illegal = "some reason";

        try {
            // When
            request.setReason(illegal);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("reason"));
            assertThat(ex.getFieldValue(), equalTo(illegal));
        }
    }

    @Test
    public void setReason_emergencyStop_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "EmergencyStop";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_evDisconnected_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "EVDisconnected";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_hardReset_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "HardReset";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_local_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "Local";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_other_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "Other";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_powerLoss_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "PowerLoss";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_reboot_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "Reboot";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_remote_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "Remote";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_softReset_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "SoftReset";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_unlockCommand_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "UnlockCommand";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setReason_deAuthorized_throwsPropertyConstraintException() throws Exception {
        // Given
        String reason = "DeAuthorized";

        // When
        request.setReason(reason);

        // Then
        assertThat(request.getReason(), equalTo(reason));
    }

    @Test
    public void setTransactionData_listOfMeterValues_transactionDataIsSet() {
        // Given
        MeterValue[] meterValues = aList(mock(MeterValue.class));

        // When
        request.setTransactionData(meterValues);

        // Then
        assertThat(request.getTransactionData(), equalTo(meterValues));
    }

    @Test
    public void validate_returnFalse() {
        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_meterStopAndTimestampAndTransactionIdIsSet_returnTrue() {
        // Given
        request.setMeterStop(42);
        request.setTimestamp(Calendar.getInstance());
        request.setTransactionId(42);

        // When
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void validate_transactionDataIsSet_transactionDataIsValidated() {
        // Given
        MeterValue meterValue = mock(MeterValue.class);
        request.setTransactionData(aList(meterValue));

        // When
        request.validate();

        // Then
        verify(meterValue, times(1)).validate();
    }

    @Test
    public void validate_aMeterValueIsNotValid_returnFalse() {
        // Given
        request.setMeterStop(42);
        request.setTimestamp(Calendar.getInstance());
        request.setTransactionId(42);

        MeterValue meterValue = mock(MeterValue.class);
        request.setTransactionData(aList(meterValue));

        // When
        when(meterValue.validate()).thenReturn(false);
        boolean isValid = request.validate();

        // Then
        assertThat(isValid, is(false));
    }
}