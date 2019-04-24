package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.smartcharging.SetChargingProfileRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2017 Emil Christopher Solli Melar <emil@iconsultable.no>
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

public class SetChargingProfileRequestTest {

  private static final String EXPECTED_ERROR_MESSAGE =
      "Validation failed: [connectorId must be >= 0]. Current Value: [%s]";

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private SetChargingProfileRequest request;

  @Before
  public void setUp() {
    request = new SetChargingProfileRequest();
  }

  @Test
  public void setConnectorId_negativeInteger_throwsPropertyConstraintException() {
    testInvalidConnectorIdValue(-42);
  }

  @Test
  public void setConnectorId_asNull_throwsPropertyConstraintException() {
    testInvalidConnectorIdValue(null);
  }

  private void testInvalidConnectorIdValue(Integer invalidConnectorId) {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(createExpectedErrorMessage(invalidConnectorId)));

    request.setConnectorId(invalidConnectorId);
  }

  private static String createExpectedErrorMessage(Integer invalidConnectorId) {
    return String.format(EXPECTED_ERROR_MESSAGE, invalidConnectorId);
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
}
