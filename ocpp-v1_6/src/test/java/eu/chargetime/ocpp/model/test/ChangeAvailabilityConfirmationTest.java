package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.model.core.AvailabilityStatus;
import eu.chargetime.ocpp.model.core.ChangeAvailabilityConfirmation;
import org.junit.Before;
import org.junit.Test;

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
public class ChangeAvailabilityConfirmationTest {
  private ChangeAvailabilityConfirmation confirmation;

  @Before
  public void setUp() throws Exception {

    confirmation = new ChangeAvailabilityConfirmation();
  }

  @Test
  public void setStatus_availabilityStatus_statusIsSet() throws Exception {
    // Given
    AvailabilityStatus availabilityStatus = AvailabilityStatus.Accepted;

    // When
    confirmation.setStatus(availabilityStatus);

    // Then
    assertThat(confirmation.getStatus(), equalTo(availabilityStatus));
  }

  @Test
  public void validate_statusIsSet_returnTrue() throws Exception {
    // Given
    confirmation.setStatus(AvailabilityStatus.Accepted);

    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void validate_statusIsNull_returnFalse() throws Exception {
    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(false));
  }
}
