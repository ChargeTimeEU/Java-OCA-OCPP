package eu.chargetime.ocpp.model.test;
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

import static eu.chargetime.ocpp.utilities.TestUtilities.aString;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.DataTransferRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataTransferRequestTest {

  private static final String EXCEPTION_MESSAGE_TEMPLATE =
      "Validation failed: [Exceeded limit of %s chars]. Current Value: [%s]";

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private DataTransferRequest request;

  @Before
  public void setUp() {
    request = new DataTransferRequest();
  }

  @Test
  public void setVendorId_stringLength256_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(createExpectedExceptionMessage(255, 256)));

    String aString = aString(256);

    request.setVendorId(aString);
  }

  @Test
  public void setVendorId_stringLength255_vendorIdIsSet() {
    // Given
    String aString = aString(255);

    // When
    request.setVendorId(aString);

    // Then
    assertThat(request.getVendorId(), equalTo(aString));
  }

  @Test
  public void setMessageId_stringLength51_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(createExpectedExceptionMessage(50, 51)));

    String aString = aString(51);

    request.setMessageId(aString);
  }

  @Test
  public void setMessageId_stringLength50_messageIdIsSet() {
    // Given
    String aString = aString(50);

    // When
    request.setMessageId(aString);

    // Then
    assertThat(request.getMessageId(), equalTo(aString));
  }

  @Test
  public void setData_aString_dataIsSet() {
    // Given
    String aString = "some string";

    // When
    request.setData(aString);

    // Then
    assertThat(request.getData(), equalTo(aString));
  }

  @Test
  public void validate_vendorIdIsSet_returnTrue() {
    // Given
    request.setVendorId("Some vendor id");

    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void validate_returnFalse() {
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

  private static String createExpectedExceptionMessage(int maxAllowedLength, int currentLength) {
    return String.format(EXCEPTION_MESSAGE_TEMPLATE, maxAllowedLength, currentLength);
  }
}
