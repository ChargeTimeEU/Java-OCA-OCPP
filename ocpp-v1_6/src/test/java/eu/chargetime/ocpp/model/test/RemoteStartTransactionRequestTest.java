package eu.chargetime.ocpp.model.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.aString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.ChargingProfile;
import eu.chargetime.ocpp.model.core.RemoteStartTransactionRequest;
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
public class RemoteStartTransactionRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private RemoteStartTransactionRequest request;

  @Before
  public void setUp() {
    request = new RemoteStartTransactionRequest();
  }

  @Test
  public void setConnectorId_integerZero_throwsPropertyConstraintException() {
    testInvalidConnectorIdValue(0);
  }

  @Test
  public void setConnectorId_negativeInteger_throwsPropertyConstraintException() {
    testInvalidConnectorIdValue(-42);
  }

  private void testInvalidConnectorIdValue(int invalidValue) {
    defineExpectedException(
        "Validation failed: [connectorId must be > 0]. Current Value: [" + invalidValue + "]");

    request.setConnectorId(invalidValue);
  }

  @Test
  public void setConnectorId_positiveInteger_connectorIdIsSet() {
    // Given
    int someInteger = 42;

    // When
    request.setConnectorId(someInteger);

    // Then
    assertThat(request.getConnectorId(), equalTo(someInteger));
  }

  @Test
  public void setIdTag_string20_idTagIsSet() {
    // Given
    String idTag = aString(20);

    // When
    request.setIdTag(idTag);

    // Then
    assertThat(request.getIdTag(), equalTo(idTag));
  }

  @Test
  public void setIdTag_exceeds20Chars_throwsPropertyConstraintException() {
    defineExpectedException("Validation failed: [Exceeded limit of 20 chars]. Current Value: [21]");

    request.setIdTag(aString(21));
  }

  private void defineExpectedException(String expectedExceptionMessage) {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(expectedExceptionMessage));
  }

  @Test
  public void setChargingProfile_someChargingProfile_chargingProfileIsSet() {
    // Given
    ChargingProfile chargingProfile = mock(ChargingProfile.class);

    // When
    request.setChargingProfile(chargingProfile);

    // Then
    assertThat(request.getChargingProfile(), equalTo(chargingProfile));
  }

  @Test
  public void validate_returnFalse() {
    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_chargingProfileIsSet_chargingsProfileIsValidated() {
    // Given
    ChargingProfile chargingProfile = mock(ChargingProfile.class);
    request.setChargingProfile(chargingProfile);

    // When
    request.validate();

    // Then
    verify(chargingProfile, times(1)).validate();
  }

  @Test
  public void isTransactionRelated_returnsFalse() {
    // When
    boolean isTransactionRelated = request.transactionRelated();

    // Then
    assertThat(isTransactionRelated, is(false));
  }
}
