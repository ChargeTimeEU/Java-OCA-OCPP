package extrawest.ocpp.model.securityext.types;

/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2022 Mathias Oben <mathias.oben@enervalis.com>

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

import extrawest.ocpp.model.core.BootNotificationRequest;
import extrawest.ocpp.model.core.HeartbeatRequest;
import extrawest.ocpp.model.core.MeterValuesRequest;
import extrawest.ocpp.model.core.StatusNotificationRequest;
import extrawest.ocpp.model.securityext.ExtendedTriggerMessageRequest;
import extrawest.ocpp.model.securityext.LogStatusNotificationRequest;
import extrawest.ocpp.model.securityext.SignCertificateRequest;
import extrawest.ocpp.model.securityext.SignedFirmwareStatusNotificationRequest;

/**
 * MessageTriggerEnumType is used by {@link ExtendedTriggerMessageRequest}
 */
public enum MessageTriggerEnumType {
  /**
   * To trigger {@link BootNotificationRequest}
   */
  BootNotification,

  /**
   * To trigger {@link LogStatusNotificationRequest}.
   */
  LogStatusNotification,

  /**
   * To trigger {@link SignedFirmwareStatusNotificationRequest}.
   */
  FirmwareStatusNotification,

  /**
   * To trigger {@link HeartbeatRequest}.
   */
  Heartbeat,

  /**
   * To trigger {@link MeterValuesRequest}.
   */
  MeterValues,

  /**
   * To trigger {@link SignCertificateRequest} with certificateType: ChargePointCertificate.
   */
  SignChargePointCertificate,

  /**
   * To trigger {@link StatusNotificationRequest}.
   */
  StatusNotification
}
