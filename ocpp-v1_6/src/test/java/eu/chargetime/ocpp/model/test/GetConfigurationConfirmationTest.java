package eu.chargetime.ocpp.model.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.aList;
import static eu.chargetime.ocpp.utilities.TestUtilities.aString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.GetConfigurationConfirmation;
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
public class GetConfigurationConfirmationTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private GetConfigurationConfirmation confirmation;

  @Before
  public void setUp() {
    confirmation = new GetConfigurationConfirmation();
  }

  @Test
  public void setConfigurationKey_aKeyValueType_configurationKeyIsSet() {
    // Given
    KeyValueType[] keyValueType = aList(new KeyValueType());

    // When
    confirmation.setConfigurationKey(keyValueType);

    // Then
    assertThat(confirmation.getConfigurationKey(), equalTo(keyValueType));
  }

  @Test
  public void setUnknownKey_aListWithStringLength51_throwsPropertyConstraintException() {
    defineExpectedException();

    String[] aList = aList(aString(51));

    confirmation.setUnknownKey(aList);
  }

  private void defineExpectedException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [Exceeds limit of 50 chars]. Current Value: [51]"));
  }

  @Test
  public void setUnknownKey_aListWithStringLength50_unknownKeyIsSet() {
    // Given
    String[] aList = aList(aString(50));

    // When
    confirmation.setUnknownKey(aList);

    // Then
    assertThat(confirmation.getUnknownKey(), equalTo(aList));
  }

  @Test
  public void setUnknownKey_aListWithAStringLength51_throwsPropertyConstraintException() {

    defineExpectedException();

    String[] aList = aList(aString(50), aString(51), aString(50));

    confirmation.setUnknownKey(aList);
  }

  @Test
  public void setUnknownKey_aListWithMoreStringLength50_unknownKeyIsSet() {
    // Given
    String[] aList = aList(aString(50), aString(50), aString(50));

    // When
    confirmation.setUnknownKey(aList);

    // Then
    assertThat(confirmation.getUnknownKey(), equalTo(aList));
  }

  @Test
  public void validate_returnTrue() {
    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void validate_configurationKeyIsSet_configurationKeyIsValidated() {
    // Given
    KeyValueType keyValueType = mock(KeyValueType.class);
    confirmation.setConfigurationKey(aList(keyValueType));

    // When
    confirmation.validate();

    // Then
    verify(keyValueType, times(1)).validate();
  }

  @Test
  public void validate_configurationKeyIsInvalid_returnsFalse() {
    // Given
    KeyValueType keyValueType = mock(KeyValueType.class);
    when(keyValueType.validate()).thenReturn(false);
    confirmation.setConfigurationKey(aList(keyValueType));

    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(false));
  }
}
