package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
public class ChargingProfileTest {
    ChargingProfile chargingProfile;

    @Before
    public void setUp() throws Exception {
        chargingProfile = new ChargingProfile();
    }

    @Test
    public void setChargingProfileId_nullValue_throwsPropertyConstraintException() {
        // Given
        Integer nullValue = null;

        try {
            // When
            chargingProfile.setChargingProfileId(nullValue);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("chargingProfileId"));
            assertThat(ex.getFieldValue(), equalTo(nullValue));
        }
    }

    @Test
    public void setChargingProfileId_positiveInteger_chargingProfileIdIsSet() throws Exception {
        // Given
        int someInteger = 42;

        // When
        chargingProfile.setChargingProfileId(someInteger);

        // Then
        assertThat(chargingProfile.getChargingProfileId(), equalTo(someInteger));
    }

    @Test
    public void setTransactionId_someInteger_transactionIdIsSet() {
        // Given
        Integer someInteger = 42;

        // When
        chargingProfile.setTransactionId(someInteger);

        // Then
        assertThat(chargingProfile.getTransactionId(), equalTo(someInteger));
    }

    @Test
    public void setStackLevel_negativeInteger_throwsPropertyConstraintException() {
        // Given
        Integer negativeValue = -42;

        try {
            // When
            chargingProfile.setStackLevel(negativeValue);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("stackLevel"));
            assertThat(ex.getFieldValue(), equalTo(negativeValue));
        }
    }

    @Test
    public void setStackLevel_zeroInteger_stackLevelIsSet() throws Exception {
        // Given
        int zero = 0;

        // When
        chargingProfile.setStackLevel(zero);

        // Then
        assertThat(chargingProfile.getStackLevel(), equalTo(zero));
    }

    @Test
    public void setChargingProfilePurpose_chargingProfilePurposeType_chargingProfilePurposeIsSet() throws Exception {
        // Given
        ChargingProfilePurposeType chargingProfilePurposeType = ChargingProfilePurposeType.ChargePointMaxProfile;

        // When
        chargingProfile.setChargingProfilePurpose(chargingProfilePurposeType);

        // Then
        assertThat(chargingProfile.objChargingProfilePurpose(), equalTo(chargingProfilePurposeType));
    }

    @Test
    public void setChargingProfileKind_chargingProfileKindType_chargingProfileKindIsSet() throws Exception {
        // Given
        ChargingProfileKindType chargingProfileKindType = ChargingProfileKindType.Absolute;

        // When
        chargingProfile.setChargingProfileKind(chargingProfileKindType);

        // Then
        assertThat(chargingProfile.objChargingProfileKind(), equalTo(chargingProfileKindType));
    }

    @Test
    public void setRecurrencyKind_recurrencyKindType_recurrencyKindIsSet() throws Exception {
        // Given
        RecurrencyKindType recurrencyKindType = RecurrencyKindType.Daily;

        // When
        chargingProfile.setRecurrencyKind(recurrencyKindType);

        // Then
        assertThat(chargingProfile.objRecurrencyKind(), equalTo(recurrencyKindType));
    }

    @Test
    public void setValidFrom_calendarNow_validFromIsSet() {
        // Given
        Calendar now = Calendar.getInstance();

        // When
        chargingProfile.setValidFrom(now);

        // Then
        assertThat(chargingProfile.objValidFrom(), equalTo(now));
    }

    @Test
    public void setValidTo_calendarNow_validToIsSet() {
        // Given
        Calendar now = Calendar.getInstance();

        // When
        chargingProfile.setValidTo(now);

        // Then
        assertThat(chargingProfile.objValidTo(), equalTo(now));
    }

    @Test
    public void setChargingSchedule_aChargingSchedule_chargingScheduleIsSet() {
        // Given
        ChargingSchedule chargingSchedule = mock(ChargingSchedule.class);

        // When
        chargingProfile.setChargingSchedule(chargingSchedule);

        // Then
        assertThat(chargingProfile.getChargingSchedule(), equalTo(chargingSchedule));
    }

    @Test
    public void validate_mandatoryFieldsIsSet_returnTrue() throws Exception {
        // Given
        chargingProfile.setChargingProfileId(42);
        chargingProfile.setStackLevel(0);
        chargingProfile.setChargingProfilePurpose(ChargingProfilePurposeType.TxProfile);
        chargingProfile.setChargingProfileKind(ChargingProfileKindType.Absolute);
        ChargingSchedule chargingSchedule = mock(ChargingSchedule.class);
        when(chargingSchedule.validate()).thenReturn(true);
        chargingProfile.setChargingSchedule(chargingSchedule);

        // When
        boolean isValid = chargingProfile.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void validate_transactionIdIsSetAndChargingProfilePurposeIsNotTxProfile_returnFalse() throws Exception {
        // Given
        chargingProfile.setChargingProfileId(42);
        chargingProfile.setStackLevel(0);
        chargingProfile.setChargingProfileKind(ChargingProfileKindType.Absolute);
        ChargingSchedule chargingSchedule = mock(ChargingSchedule.class);
        when(chargingSchedule.validate()).thenReturn(true);
        chargingProfile.setChargingSchedule(chargingSchedule);

        chargingProfile.setTransactionId(42);
        chargingProfile.setChargingProfilePurpose(ChargingProfilePurposeType.TxDefaultProfile);

        // When
        boolean isValid = chargingProfile.validate();

        // Then
        assertThat(isValid, is(false));
    }
}