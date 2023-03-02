package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.UnlockConnectorRequest;
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
public class UnlockConnectorRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private UnlockConnectorRequest request;

  @Before
  public void setUp() {
    request = new UnlockConnectorRequest();
  }

  @Test
  public void setConnectorId_zeroInteger_throwsPropertyConstraintException() {
    testInvalidConnectorId(0);
  }

  @Test
  public void setConnectorId_negativeInteger_throwsPropertyConstraintException() {
    testInvalidConnectorId(-42);
  }

  @Test
  public void setConnectorId_asNull_throwsPropertyConstraintException() {
    testInvalidConnectorId(null);
  }

  private void testInvalidConnectorId(Integer invalidValue) {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo(
            "Validation failed: [connectorId must be > 0]. Current Value: [" + invalidValue + "]"));

    request.setConnectorId(invalidValue);
  }

  @Test
  public void setConnectorId_positiveInteger_connectorIdIsSet() {
    // Given
    Integer positive = 42;

    // When
    request.setConnectorId(positive);

    // Then
    assertThat(request.getConnectorId(), equalTo(positive));
  }

  @Test
  public void validate_returnFalse() {
    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_connectorIdIsSet_returnTrue() {
    // Given
    request.setConnectorId(42);

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
