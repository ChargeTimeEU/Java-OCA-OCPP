package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.BootNotificationConfirmation;
import eu.chargetime.ocpp.model.core.RegistrationStatus;
import java.time.ZonedDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*
ChargeTime.eu - Java-OCA-OCPP
Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>
Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>

MIT License

Copyright (C) 2016-2018 Thomas Volden
Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>

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
public class BootNotificationConfirmationTest {
  @Rule public ExpectedException thrownException = ExpectedException.none();

  private BootNotificationConfirmation confirmation;

  @Before
  public void setUp() {
    confirmation = new BootNotificationConfirmation();
  }

  @Test
  public void setCurrentTime_now_currentTimeIsSet() {
    // Given
    ZonedDateTime now = ZonedDateTime.now();

    // When
    confirmation.setCurrentTime(now);

    // Then
    assertThat(confirmation.getCurrentTime(), equalTo(now));
  }

  @Test
  public void setInterval_anInteger_intervalIsSet() {
    // Given
    int anInterval = 41;

    // When
    confirmation.setInterval(anInterval);

    // Then
    assertThat(confirmation.getInterval(), equalTo(anInterval));
  }

  @Test
  public void setInterval_aNegativeValue_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [interval be a positive value]. Current Value: [-42]"));

    int aNegativeValue = -42;

    confirmation.setInterval(aNegativeValue);
  }

  @Test
  public void setStatus_enumValue_statusIsAccepted() {
    // Given
    RegistrationStatus status = RegistrationStatus.Accepted;

    // When
    confirmation.setStatus(status);

    // Then
    assertThat(confirmation.getStatus(), equalTo(status));
  }

  @Test
  public void validate_currentTimeAndIntervalAndStatusIsSet_returnTrue() {
    // Given
    confirmation.setCurrentTime(ZonedDateTime.now());
    confirmation.setInterval(42);
    confirmation.setStatus(RegistrationStatus.Accepted);

    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void validate_returnFalse() {
    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(false));
  }
}
