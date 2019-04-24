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

import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.model.reservation.ReservationStatus;
import eu.chargetime.ocpp.model.reservation.ReserveNowConfirmation;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class ReserveNowConfirmationTest {

  private ReserveNowConfirmation confirmation;

  @Before
  public void setup() {
    confirmation = new ReserveNowConfirmation();
  }

  @Test
  public void validate_statusIsNotSet_returnsFalse() {
    // When
    boolean result = confirmation.validate();

    // Then
    assertThat(result, CoreMatchers.is(false));
  }

  @Test
  public void validate_statusIsSet_returnsTrue() {
    // Given
    ReservationStatus status = ReservationStatus.Occupied;

    confirmation.setStatus(status);

    // When
    boolean result = confirmation.validate();

    // Then
    assertThat(result, CoreMatchers.is(true));
  }
}
