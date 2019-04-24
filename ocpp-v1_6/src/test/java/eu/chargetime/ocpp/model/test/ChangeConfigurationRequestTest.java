package eu.chargetime.ocpp.model.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.aString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.ChangeConfigurationRequest;
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

public class ChangeConfigurationRequestTest {

  private static final String EXCEPTION_MESSAGE_TEMPLATE =
      "Validation failed: [Exceeded limit of %s chars]. Current Value: [%s]";

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private ChangeConfigurationRequest request;

  @Before
  public void setUp() {
    request = new ChangeConfigurationRequest();
  }

  @Test
  public void setKey_stringLength51_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(createExpectedExceptionMessage(50, 51)));

    String stringLength51 = aString(51);

    request.setKey(stringLength51);
  }

  @Test
  public void setKey_stringLength50_keyIsSet() {
    // Given
    String stringLength50 = aString(50);

    // When
    request.setKey(stringLength50);

    // Then
    assertThat(request.getKey(), equalTo(stringLength50));
  }

  @Test
  public void setValue_stringLength501_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(createExpectedExceptionMessage(500, 501)));

    String stringLength501 = aString(501);

    request.setValue(stringLength501);
  }

  @Test
  public void setValue_stringLength500_valueIsSet() {
    // Given
    String stringLength500 = aString(500);

    // When
    request.setValue(stringLength500);

    // Then
    assertThat(request.getValue(), equalTo(stringLength500));
  }

  @Test
  public void validate_keyAndValueIsSet_returnTrue() {
    // Given
    request.setKey("some key");
    request.setValue("some value");

    // When
    boolean isValid = request.validate();

    // Given
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
  public void validate_onlyKeyIsSet_returnFalse() {
    // Given
    request.setKey("some key");

    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_onlyValueIsSet_returnFalse() {
    // Given
    request.setValue("some value");

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
