package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.smartcharging.ChangingRateUnitType;
import eu.chargetime.ocpp.model.smartcharging.GetCompositeScheduleRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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
@RunWith(MockitoJUnitRunner.class)
public class GetCompositeScheduleRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private GetCompositeScheduleRequest request;

  @Before
  public void setUp() {
    request = new GetCompositeScheduleRequest();
  }

  @Test
  public void setConnectorId_negativeInteger_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [connectorId must be >= 0]. Current Value: [-1]"));

    int negativeValue = -1;

    request.setConnectorId(negativeValue);
  }

  @Test
  public void setConnectorId_zeroInteger_connectorIdIsSet() {
    // Given
    int zeroValue = 0;

    // When
    request.setConnectorId(zeroValue);

    // Then
    assertThat(request.getConnectorId(), equalTo(zeroValue));
  }

  @Test
  public void setDuration_zeroInteger_durationIsSet() {
    // Given
    int zeroValue = 0;

    // When
    request.setDuration(zeroValue);

    // Then
    assertThat(request.getDuration(), equalTo(zeroValue));
  }

  @Test
  public void setChangingRateUnitType_A_changingRateUnitTypeIsSet() {
    // Given
    ChangingRateUnitType chargingRateUnitType = ChangingRateUnitType.A;

    // When
    request.setChangingRateUnitType(chargingRateUnitType);

    // Then
    assertThat(request.getChangingRateUnitType(), equalTo(chargingRateUnitType));
  }

  @Test
  public void validate_returnFalse() {
    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_connectorIdAndMeterValueIsValid_returnTrue() {
    // Given
    request.setConnectorId(42);
    request.setDuration(0);
    request.setChangingRateUnitType(ChangingRateUnitType.A);

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
