package eu.chargetime.ocpp.model.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.aList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.MeterValue;
import eu.chargetime.ocpp.model.core.MeterValuesRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
@RunWith(MockitoJUnitRunner.class)
public class MeterValuesRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private MeterValuesRequest request;

  @Mock private MeterValue meterValueMock;

  @Before
  public void setUp() {
    request = new MeterValuesRequest();
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
  public void setTransactionId_transactionIdIsSet() {
    // Given
    int anyValue = 42;

    // When
    request.setTransactionId(anyValue);

    // Then
    assertThat(request.getTransactionId(), equalTo(anyValue));
  }

  @Test
  public void setMeterValue_aMeterValueArray_meterValueIsSet() {
    // Given
    MeterValue[] listOfMeterValues = aList(new MeterValue());

    // When
    request.setMeterValue(listOfMeterValues);

    // Then
    assertThat(request.getMeterValue(), equalTo(listOfMeterValues));
  }

  @Test
  public void validate_returnFalse() {
    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_meterValueIsSet_validatesMeterValue() {
    // Given
    request.setMeterValue(aList(meterValueMock));

    // When
    request.validate();

    // Then
    verify(meterValueMock, times(1)).validate();
  }

  @Test
  public void validate_connectorIdAndMeterValueIsValid_returnTrue() {
    // Given
    request.setConnectorId(42);
    request.setMeterValue(aList(meterValueMock));
    when(meterValueMock.validate()).thenReturn(true);

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
    assertThat(isTransactionRelated, is(true));
  }
}
