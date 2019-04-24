package eu.chargetime.ocpp.model.basic.types.test;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>

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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.basic.types.ChargingStationType;
import eu.chargetime.ocpp.model.basic.types.ModemType;
import org.junit.Test;

public class ChargingStationTypeTest {

  private ChargingStationType createSut() {
    return new ChargingStationType();
  }

  @Test
  public void validate_nothingsSet_returnsFalse() {
    boolean expected = false;
    ChargingStationType sut = createSut();

    boolean result = sut.validate();

    assertEquals(expected, result);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setFirmwareVersion_above50Chars_throwsPropertyConstraintException() {
    String longString = aString(51);
    ChargingStationType sut = createSut();
    sut.setFirmwareVersion(longString);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setVendorName_above50Chars_throwsPropertyConstraintException() {
    String longString = aString(51);
    ChargingStationType sut = createSut();
    sut.setVendorName(longString);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setModel_above20Chars_throwsPropertyConstraintException() {
    String longString = aString(21);
    ChargingStationType sut = createSut();
    sut.setModel(longString);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setSerialNumber_above20Chars_throwsPropertyConstraintException() {
    String longString = aString(21);
    ChargingStationType sut = createSut();
    sut.setSerialNumber(longString);
  }

  @Test
  public void validate_modelAndVendorNameIsSet_returnTrue() {
    boolean expected = true;
    String someString = "test123";
    ChargingStationType sut = createSut();
    sut.setModel(someString);
    sut.setVendorName(someString);

    boolean result = sut.validate();

    assertEquals(expected, result);
  }

  @Test
  public void validate_modemSet_validateIsCalledOnModem() {
    ChargingStationType sut = createSut();
    sut.setModel(aString(2));
    sut.setVendorName(aString(2));
    ModemType modemTypeMock = mock(ModemType.class);
    sut.setModem(modemTypeMock);

    sut.validate();

    verify(modemTypeMock).validate();
  }
}
