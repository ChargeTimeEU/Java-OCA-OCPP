package eu.chargetime.ocpp.model.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.aString;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.ChargePointErrorCode;
import eu.chargetime.ocpp.model.core.ChargePointStatus;
import eu.chargetime.ocpp.model.core.StatusNotificationRequest;
import java.time.ZonedDateTime;
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
public class StatusNotificationRequestTest {

  private static final String EXCEPTION_MESSAGE_TEMPLATE =
      "Validation failed: [Exceeds limit of %s chars]. Current Value: [%s]";

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private StatusNotificationRequest request;

  private static String createExpectedExceptionMessage(int maxAllowedLength, int currentLength) {
    return String.format(EXCEPTION_MESSAGE_TEMPLATE, maxAllowedLength, currentLength);
  }

  @Before
  public void setUp() {
    request = new StatusNotificationRequest();
  }

  @Test
  public void setConnectorId_negativeInteger_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [connectorId >= 0]. Current Value: [-42]"));

    Integer negativeValue = -42;

    request.setConnectorId(negativeValue);
  }

  @Test
  public void setConnectorId_zeroInteger_connectorIdIsSet() {
    // Given
    Integer zeroValue = 0;

    // When
    request.setConnectorId(zeroValue);

    // Then
    assertThat(request.getConnectorId(), equalTo(zeroValue));
  }

  @Test
  public void setErrorCode_chargePointErrorCode_errorCodeIsSet() {
    // Given
    ChargePointErrorCode chargePointErrorCode = ChargePointErrorCode.NoError;

    // When
    request.setErrorCode(chargePointErrorCode);

    // Then
    assertThat(request.getErrorCode(), equalTo(chargePointErrorCode));
  }

  @Test
  public void setInfo_stringLength51_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(createExpectedExceptionMessage(50, 51)));

    String length51 = aString(51);

    request.setInfo(length51);
  }

  @Test
  public void setInfo_stringLength50_infoIsSet() {
    // Given
    String length50 = aString(50);

    // When
    request.setInfo(length50);

    // Then
    assertThat(request.getInfo(), equalTo(length50));
  }

  @Test
  public void setStatus_chargePointStatus_statusIsSet() {
    // Given
    ChargePointStatus chargePointStatus = ChargePointStatus.Available;

    // When
    request.setStatus(chargePointStatus);

    // Then
    assertThat(request.getStatus(), equalTo(chargePointStatus));
  }

  @Test
  public void setTimestamp_calendarNow_timestampIsSet() {
    // Given
    ZonedDateTime now = ZonedDateTime.now();

    // When
    request.setTimestamp(now);

    // Then
    assertThat(request.getTimestamp(), equalTo(now));
  }

  @Test
  public void setVendorId_stringLength256_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(createExpectedExceptionMessage(255, 256)));

    String length256 = aString(256);

    request.setVendorId(length256);
  }

  @Test
  public void setVendorId_stringLength255_vendorIdIsSet() {
    // Given
    String length255 = aString(255);

    // When
    request.setVendorId(length255);

    // Then
    assertThat(request.getVendorId(), equalTo(length255));
  }

  @Test
  public void setVendorErrorCode_stringLength51_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(createExpectedExceptionMessage(50, 51)));

    String length51 = aString(51);

    request.setVendorErrorCode(length51);
  }

  @Test
  public void setVendorErrorCode_stringLength50_vendorErrorCodeIsSet() {
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
  public void validate_connectorIdAndErrorCodeAndStatusIsSet_returnTrue() {
    // Given
    request.setConnectorId(42);
    request.setErrorCode(ChargePointErrorCode.NoError);
    request.setStatus(ChargePointStatus.Available);

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
