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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.model.firmware.GetDiagnosticsRequest;
import org.junit.Before;
import org.junit.Test;

public class GetDiagnosticsRequestTest {

  private GetDiagnosticsRequest request;

  @Before
  public void setup() {
    request = new GetDiagnosticsRequest();
  }

  @Test
  public void validate_locationIsNotSet_returnsFalse() {
    // When
    boolean result = request.validate();

    // Then
    assertThat(result, is(false));
  }

  @Test
  public void validate_locationIsSet_returnsTrue() {
    // Given
    String aLocation = "/";
    request.setLocation(aLocation);

    // When
    boolean result = request.validate();

    // Then
    assertThat(result, is(true));
  }
}
