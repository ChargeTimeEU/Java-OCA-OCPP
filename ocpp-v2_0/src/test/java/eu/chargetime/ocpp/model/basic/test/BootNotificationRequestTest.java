package eu.chargetime.ocpp.model.basic.test;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.basic.BootNotificationRequest;
import eu.chargetime.ocpp.model.basic.types.BootReasonEnumType;
import eu.chargetime.ocpp.model.basic.types.ChargingStationType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BootNotificationRequestTest {

  @Mock ChargingStationType chargingStationStub;

  private BootNotificationRequest createSut() {
    return new BootNotificationRequest();
  }

  @Test(expected = PropertyConstraintException.class)
  public void setReason_null_ThrowsPropertyConstraintException() {
    BootNotificationRequest sut = createSut();
    sut.setReason(null);
  }

  @Test
  public void validate_nothingsSet_returnsFalse() {
    boolean expected = false;
    BootNotificationRequest sut = createSut();
    boolean result = sut.validate();
    assertEquals(expected, result);
  }

  @Test
  public void validate_reasonAndChargingStationSet_returnsTrue() {
    boolean expected = true;
    BootNotificationRequest sut = createSut();
    sut.setReason(BootReasonEnumType.ApplicationReset);
    when(chargingStationStub.validate()).thenReturn(true);
    sut.setChargingStation(chargingStationStub);

    boolean result = sut.validate();

    assertEquals(expected, result);
  }
}
