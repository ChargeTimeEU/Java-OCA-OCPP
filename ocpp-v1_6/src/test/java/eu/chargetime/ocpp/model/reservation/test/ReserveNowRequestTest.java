package eu.chargetime.ocpp.model.reservation.test;
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
import eu.chargetime.ocpp.model.reservation.ReserveNowRequest;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ReserveNowRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private ReserveNowRequest request;

  @Before
  public void setup() {
    request = new ReserveNowRequest();
  }

  @Test
  public void validate_statusIsNotSet_returnsFalse() {
    // When
    boolean result = request.validate();

    // Then
    assertThat(result, is(false));
  }

  @Test
  public void validate_requiredFieldsAreSet_returnTrue() {
    // Given
    Integer connectorId = 0;
    Calendar expiryDate = Calendar.getInstance();
    String idTag = "row";
    Integer reservationId = 2;

    request.setConnectorId(connectorId);
    request.setExpiryDate(expiryDate);
    request.setIdTag(idTag);
    request.setReservationId(reservationId);

    // When
    boolean result = request.validate();

    // Then
    assertThat(result, is(true));
  }

  @Test
  public void setIdTag_withMoreThan20Chars_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [Exceeded limit of 20 chars]. Current Value: [26]"));

    request.setIdTag("abcdefghijklmnopqrstuvwxyz");
  }

  @Test
  public void setParentIdTag_withMoreThan20Chars_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [Exceeded limit of 20 chars]. Current Value: [26]"));

    request.setParentIdTag("abcdefghijklmnopqrstuvwxyz");
  }

  @Test
  public void setConnectorId_asNegative_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [connectorId must be >= 0]. Current Value: [-42]"));

    request.setConnectorId(-42);
  }

  @Test
  public void setConnectorId_asPositive_isValid() {
    testValidConnectorId(42);
  }

  @Test
  public void setConnectorId_asZero_isValid() {
    testValidConnectorId(0);
  }

  private void testValidConnectorId(int validConnectorId) {
    request.setConnectorId(validConnectorId);

    assertThat(request.getConnectorId(), equalTo(validConnectorId));
  }
}
