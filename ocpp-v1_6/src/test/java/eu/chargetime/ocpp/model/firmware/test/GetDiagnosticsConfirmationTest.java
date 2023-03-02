package eu.chargetime.ocpp.model.firmware.test;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>

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

import static eu.chargetime.ocpp.utilities.TestUtilities.aString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.firmware.GetDiagnosticsConfirmation;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GetDiagnosticsConfirmationTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private GetDiagnosticsConfirmation confirmation;

  @Before
  public void setup() {
    confirmation = new GetDiagnosticsConfirmation();
  }

  @Test
  public void validate_returnsTrue() {
    // When
    boolean result = confirmation.validate();

    // Then
    assertThat(result, is(true));
  }

  @Test
  public void setFileName_stringLength256_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [Exceeds limit of 255 chars]. Current Value: [256]"));

    String aString = aString(256);

    confirmation.setFileName(aString);
  }

  @Test
  public void setFileName_stringLength255_fileNameIsSet() {
    // Given
    String aString = aString(255);

    // When
    confirmation.setFileName(aString);

    // Then
    assertThat(confirmation.getFileName(), equalTo(aString));
  }
}
