package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.AvailabilityType;
import eu.chargetime.ocpp.model.core.ChangeAvailabilityRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private ChangeAvailabilityRequest request;

  @Before
  public void setUp() {

    request = new ChangeAvailabilityRequest();
  }

  @Test
  public void setConnectorId_negativeValue_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage("");

    int aNegativeValue = -1;

    request.setConnectorId(aNegativeValue);
  }

  @Test
  public void setConnectorId_zero_connectorIdIsSet() {
    // Given
    int zero = 0;

    // When
    request.setConnectorId(zero);

    // Then
    assertThat(request.getConnectorId(), is(zero));
  }

  @Test
  public void setConnectorId_positiveValue_connectorIdIsSet() {
    // Given
    int aPositiveValue = 42;

    // When
    request.setConnectorId(aPositiveValue);

    // Then
    assertThat(request.getConnectorId(), is(aPositiveValue));
  }

  @Test
  public void setType_availabilityType_typeIsSet() {
    // Given
    AvailabilityType availabilityType = AvailabilityType.Operative;

    // When
    request.setType(availabilityType);

    // Then
    assertThat(request.getType(), equalTo(availabilityType));
  }

  @Test
  public void validate_typeAndConnectorIdIsSet_returnsTrue() {
    // Given
    request.setType(AvailabilityType.Operative);
    request.setConnectorId(0);

    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void validate_onlyTypeIsSet_returnsFalse() {
    // Given
    request.setType(AvailabilityType.Operative);

    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_connectorIdIsSet_returnsFalse() {
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
