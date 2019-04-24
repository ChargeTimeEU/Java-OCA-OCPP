package eu.chargetime.ocpp.model.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.aString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.StartTransactionRequest;
import java.util.Calendar;
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
public class StartTransactionRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private StartTransactionRequest request;

  @Before
  public void setUp() {
    request = new StartTransactionRequest();
  }

  @Test
  public void setConnectorId_asZeroInteger_throwsPropertyConstraintException() {
    testConnectorIdInvalidValue(0);
  }

  @Test
  public void setConnectorId_asNegativeInteger_throwsPropertyConstraintException() {
    testConnectorIdInvalidValue(-1);
  }

  @Test
  public void setConnectorId_asNullInteger_throwsPropertyConstraintException() {
    testConnectorIdInvalidValue(null);
  }

  private void testConnectorIdInvalidValue(Integer invalidValue) {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo(
            "Validation failed: [connectorId must be > 0]. Current Value: [" + invalidValue + "]"));

    request.setConnectorId(invalidValue);
  }

  @Test
  public void setIdTag_exceeds20Chars_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [Exceeded limit of 20 chars]. Current Value: [21]"));

    request.setIdTag(aString(21));
  }

  @Test
  public void setIdTag_string20_isSet() {
    // Given
    String validString = aString(20);

    // When
    request.setIdTag(validString);

    // Then
    assertThat(request.getIdTag(), equalTo(validString));
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
  public void setMeterStart_anInteger_meterStartIsSet() {
    // Given
    Integer meterStart = 42;

    // When
    request.setMeterStart(meterStart);

    // Then
    assertThat(request.getMeterStart(), equalTo(meterStart));
  }

  @Test
  public void setReservationId_anInteger_reservationIdIsSet() {
    // Given
    Integer anInteger = 42;

    // When
    request.setReservationId(anInteger);

    // Then
    assertThat(request.getReservationId(), equalTo(anInteger));
  }

  @Test
  public void setTimestamp_calendarNow_timestampIsSet() {
    // Given
    Calendar now = Calendar.getInstance();

    // When
    request.setTimestamp(now);
    // Then
    assertThat(request.getTimestamp(), equalTo(now));
  }

  @Test
  public void validate_returnFalse() {
    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_requiredFieldsAreSet_returnTrue() {
    // Given
    request.setConnectorId(42);
    request.setIdTag("xxx");
    request.setMeterStart(42);
    request.setTimestamp(Calendar.getInstance());

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
