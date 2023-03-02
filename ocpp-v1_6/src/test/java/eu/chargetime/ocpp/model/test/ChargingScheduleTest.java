package eu.chargetime.ocpp.model.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.aList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import eu.chargetime.ocpp.model.core.ChargingRateUnitType;
import eu.chargetime.ocpp.model.core.ChargingSchedule;
import eu.chargetime.ocpp.model.core.ChargingSchedulePeriod;
import java.time.ZonedDateTime;
import org.junit.Before;
import org.junit.Test;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
 * Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>
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
public class ChargingScheduleTest {
  ChargingSchedule chargingSchedule;

  @Before
  public void setUp() throws Exception {
    chargingSchedule = new ChargingSchedule();
  }

  @Test
  public void setDuration_anInteger_durationIsSet() {
    // Given
    Integer anInteger = 42;

    // When
    chargingSchedule.setDuration(anInteger);

    // Then
    assertThat(chargingSchedule.getDuration(), equalTo(anInteger));
  }

  @Test
  public void setStartSchedule_calendarNow_startScheduleIsSet() {
    // Given
    ZonedDateTime now = ZonedDateTime.now();

    // When
    chargingSchedule.setStartSchedule(now);

    // Then
    assertThat(chargingSchedule.getStartSchedule(), equalTo(now));
  }

  @Test
  public void setChargingRateUnit_chargingRateUnitType_chargingRateUnitIsSet() throws Exception {
    // Given
    ChargingRateUnitType chargingRateUnitType = ChargingRateUnitType.A;

    // When
    chargingSchedule.setChargingRateUnit(chargingRateUnitType);

    // Then
    assertThat(chargingSchedule.getChargingRateUnit(), equalTo(chargingRateUnitType));
  }

  @Test
  public void
      setChargingSchedulePeriod_listOfChargingSchedulePeriods_chargingSchedulePeriodIsSet() {
    // Given
    ChargingSchedulePeriod[] chargingSchedulePeriod = aList(mock(ChargingSchedulePeriod.class));

    // When
    chargingSchedule.setChargingSchedulePeriod(chargingSchedulePeriod);

    // Then
    assertThat(chargingSchedule.getChargingSchedulePeriod(), equalTo(chargingSchedulePeriod));
  }

  @Test
  public void setMinChargingRate_aDouble_minChargingRate() {
    // Given
    Double aDouble = 4.2;

    // When
    chargingSchedule.setMinChargingRate(aDouble);

    // Then
    assertThat(chargingSchedule.getMinChargingRate(), equalTo(aDouble));
  }

  @Test
  public void validate_chargingSchedulePeriodIsSet_chargingSchedulePeriodIsValidated()
      throws Exception {
    // Given
    chargingSchedule.setChargingRateUnit(ChargingRateUnitType.W);
    ChargingSchedulePeriod chargingSchedulePeriod = mock(ChargingSchedulePeriod.class);
    chargingSchedule.setChargingSchedulePeriod(aList(chargingSchedulePeriod));

    // When
    chargingSchedule.validate();

    // Then
    verify(chargingSchedulePeriod, times(1)).validate();
  }

  @Test
  public void validate_chargingRateUnitAndChargingSchedulePeriodIsSet_returnTrue()
      throws Exception {
    // Given
    ChargingSchedulePeriod chargingSchedulePeriod = mock(ChargingSchedulePeriod.class);
    chargingSchedule.setChargingRateUnit(ChargingRateUnitType.W);
    chargingSchedule.setChargingSchedulePeriod(aList(chargingSchedulePeriod));
    when(chargingSchedulePeriod.validate()).thenReturn(true);

    // When
    boolean isValid = chargingSchedule.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void validate_returnFalse() {
    // When
    boolean isValid = chargingSchedule.validate();

    // Then
    assertThat(isValid, is(false));
  }
}
