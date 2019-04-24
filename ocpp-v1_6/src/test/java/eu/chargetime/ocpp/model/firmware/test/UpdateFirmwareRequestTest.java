package eu.chargetime.ocpp.model.firmware.test;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
   Copyright (C) 2018 Mikhail Kladkevich <kladmv@ecp-share.com>

   Permission is hereby granted, free of charge, to any person obtaining a copy
   of this software and associated documentation files (the "Software"), to deal
   in the Software without restriction, including without limitation the rights
   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
   copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all
   copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
   SOFTWARE.
*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.firmware.UpdateFirmwareRequest;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UpdateFirmwareRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private UpdateFirmwareRequest request;

  @Before
  public void setup() {
    request = new UpdateFirmwareRequest();
  }

  @Test
  public void validate_locationIsNotSet_returnsFalse() {
    // When
    boolean result = request.validate();

    // Then
    assertThat(result, is(false));
  }

  @Test
  public void validate_retrieveDateIsNotSet_returnsFalse() {

    // Given
    String aLocation = "/";
    request.setLocation(aLocation);

    // When
    boolean result = request.validate();

    // Then
    assertThat(result, is(false));
  }

  @Test
  public void validate_locationAndRetrieveDateIsSet_returnsTrue() {
    // Given
    String aLocation = "/";
    Calendar aRetrieveDate = Calendar.getInstance();
    request.setLocation(aLocation);
    request.setRetrieveDate(aRetrieveDate);

    // When
    boolean result = request.validate();

    // Then
    assertThat(result, is(true));
  }

  @Test
  public void setRetries_asNegative_throwsPropertyConstraintException() {
    testInvalidRetries(-42);
  }

  @Test
  public void setRetries_asZero_throwsPropertyConstraintException() {
    testInvalidRetries(0);
  }

  private void testInvalidRetries(int retryInvalidRetries) {
    defineThrownException(
        "Validation failed: [retries must be > 0]. Current Value: [" + retryInvalidRetries + "]");

    request.setRetries(retryInvalidRetries);
  }

  private void defineThrownException(String expectedExceptionMessage) {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(expectedExceptionMessage));
  }

  @Test
  public void setRetries_asPositive_isAccepted() {
    request.setRetries(42);

    assertThat(request.getRetries(), equalTo(42));
  }

  @Test
  public void setRetryInterval_asNegative_throwsPropertyConstraintException() {
    testInvalidRetryInterval(-42);
  }

  @Test
  public void setRetryInterval_asZero_throwsPropertyConstraintException() {
    testInvalidRetryInterval(0);
  }

  private void testInvalidRetryInterval(int invalidRetryValue) {
    defineThrownException(
        "Validation failed: [retryInterval must be > 0]. Current Value: ["
            + invalidRetryValue
            + "]");

    request.setRetryInterval(invalidRetryValue);
  }

  @Test
  public void setRetryInterval_asPositive_isAccepted() {
    request.setRetryInterval(42);

    assertThat(request.getRetryInterval(), equalTo(42));
  }
}
