/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * Copyright (C) 2023 Robert Schlabbach (robert.schlabbach@ubitricity.com)
 *
 * MIT License
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

package eu.chargetime.ocpp.v201.model.messages;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import eu.chargetime.ocpp.JSONCommunicator;
import eu.chargetime.ocpp.v201.model.types.BootReasonEnum;
import eu.chargetime.ocpp.v201.model.types.ChargingStation;
import eu.chargetime.ocpp.v201.model.types.Modem;
import org.junit.Test;

/** unit tests for OCPP 2.0.1 BootNotificationRequest serialization and deserialization */
public class BootNotificationRequestTest {
  @Test
  public void testSerializationAndDeserialization() throws Exception {
    JSONCommunicator communicator = new JSONCommunicator(null);
    BootNotificationRequest originalRequest =
        new BootNotificationRequest(
            new ChargingStation("model", "vendor")
                .withSerialNumber("123456789")
                .withFirmwareVersion("0.0.1")
                .withModem(new Modem().withImsi("1851").withIccid("16610")),
            BootReasonEnum.Unknown);
    String json = communicator.packPayload(originalRequest).toString();
    BootNotificationRequest deserializedRequest =
        communicator.unpackPayload(json, BootNotificationRequest.class);
    assertThat(deserializedRequest, notNullValue());
    assertThat(deserializedRequest, is(originalRequest));
  }

  @Test
  public void testInvalidBootNotificationCanBeDeserialized() throws Exception {
    JSONCommunicator communicator = new JSONCommunicator(null);
    String json = "{}";
    BootNotificationRequest bootNotificationRequest =
        communicator.unpackPayload(json, BootNotificationRequest.class);
    assertThat(bootNotificationRequest, notNullValue());
    assertThat(bootNotificationRequest.validate(), is(false));
  }
}
