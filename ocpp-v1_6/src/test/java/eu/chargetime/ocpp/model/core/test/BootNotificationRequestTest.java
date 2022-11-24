package eu.chargetime.ocpp.model.core.test;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2022 Andreas Fischer <andreas.fischer@innocharge.de>

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

import eu.chargetime.ocpp.model.core.BootNotificationRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BootNotificationRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private BootNotificationRequest request;

  @Before
  public void setup() {
    request = new BootNotificationRequest();
  }

  @Test()
  public void validate_setChargeBoxSerialNumber_onNull_doesNotThrowNPE() {
    request.setChargeBoxSerialNumber(null);
  }

  @Test()
  public void validate_setChargePointSerialNumber_onNull_doesNotThrowNPE() {
    request.setChargePointSerialNumber(null);
  }

  @Test()
  public void validate_setFirmwareVersion_onNull_doesNotThrowNPE() {
    request.setFirmwareVersion(null);
  }

  @Test()
  public void validate_setIccid_onNull_doesNotThrowNPE() {
    request.setIccid(null);
  }

  @Test()
  public void validate_setImsi_onNull_doesNotThrowNPE() {
    request.setImsi(null);
  }

  @Test()
  public void validate_setMeterSerialNumber_onNull_doesNotThrowNPE() {
    request.setMeterSerialNumber(null);
  }

  @Test()
  public void validate_setMeterType_onNull_doesNotThrowNPE() {
    request.setMeterType(null);
  }
}
