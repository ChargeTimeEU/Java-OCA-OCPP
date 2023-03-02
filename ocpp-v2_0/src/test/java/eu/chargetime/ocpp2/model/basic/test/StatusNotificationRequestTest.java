package eu.chargetime.ocpp2.model.basic.test;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2021 John Michael Luy <johnmichael.luy@gmail.com>

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

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp2.model.basic.StatusNotificationRequest;
import eu.chargetime.ocpp2.model.basic.types.ConnectorStatusEnumType;
import java.time.ZonedDateTime;
import org.junit.Assert;
import org.junit.Test;

public class StatusNotificationRequestTest {

  @Test(expected = PropertyConstraintException.class)
  public void setTimestamp_null_throwsPropertyConstraintException() {
    StatusNotificationRequest sut = new StatusNotificationRequest();

    sut.setTimestamp(null);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setConnectorStatus_null_throwsPropertyConstraintException() {
    StatusNotificationRequest sut = new StatusNotificationRequest();

    sut.setConnectorStatus(null);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setEvseId_null_throwsPropertyConstraintException() {
    StatusNotificationRequest sut = new StatusNotificationRequest();

    sut.setEvseId(null);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setConnectorId_null_throwsPropertyConstraintException() {
    StatusNotificationRequest sut = new StatusNotificationRequest();

    sut.setConnectorId(null);
  }

  @Test
  public void validate_nothingIsSet_returnsFalse() {
    boolean expected = false;
    StatusNotificationRequest sut = new StatusNotificationRequest();

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void validate_dataIsSet_returnsTrue() {
    boolean expected = true;

    StatusNotificationRequest sut = new StatusNotificationRequest();
    sut.setTimestamp(ZonedDateTime.now());
    sut.setConnectorStatus(ConnectorStatusEnumType.Available);
    sut.setEvseId(1);
    sut.setConnectorId(1);

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }
}
