package eu.chargetime.ocpp.model.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.aString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.KeyValueType;
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
public class KeyValueTypeTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private KeyValueType keyValueType;

  @Before
  public void setUp() {
    keyValueType = new KeyValueType();
  }

  @Test
  public void setKey_stringLength51_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [Exceeds limit of 50 chars]. Current Value: [51]"));

    String aString = aString(51);

    keyValueType.setKey(aString);
  }

  @Test
  public void seyKey_stringLength50_keyIsSet() {
    // Given
    String aString = aString(50);

    // When
    keyValueType.setKey(aString);

    // Then
    assertThat(keyValueType.getKey(), equalTo(aString));
  }

  @Test
  public void setReadonly_null_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [readonly must be present]. Current Value: [null]"));

    Boolean nullValue = null;
    keyValueType.setReadonly(nullValue);
  }

  @Test
  public void setReadonly_aBooleanValue_readonlyIsSet() {
    // Given
    Boolean aBool = true;

    // When
    keyValueType.setReadonly(aBool);

    // Then
    assertThat(keyValueType.getReadonly(), equalTo(aBool));
  }

  @Test
  public void setValue_stringLength501_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [Exceeds limit of 500 chars]. Current Value: [501]"));

    String aString = aString(501);

    keyValueType.setValue(aString);
  }

  @Test
  public void setValue_stringLength500_valueIsSet() {
    // Given
    String aString = aString(500);

    // When
    keyValueType.setValue(aString);

    // Then
    assertThat(keyValueType.getValue(), equalTo(aString));
  }

  @Test
  public void validate_returnFalse() {
    // When
    boolean isValid = keyValueType.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_keyAndReadonlyIsSet_returnTrue() {
    // Given
    keyValueType.setKey("test key");
    keyValueType.setReadonly(false);

    // When
    boolean isValid = keyValueType.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void validate_onlyReadonlyIsSet_returnFalse() {
    // Given
    keyValueType.setReadonly(false);

    // When
    boolean isValid = keyValueType.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_onlyKeyIsSet_returnFalse() {
    // Given
    keyValueType.setKey("some key");

    // When
    boolean isValid = keyValueType.validate();

    // Then
    assertThat(isValid, is(false));
  }
}
