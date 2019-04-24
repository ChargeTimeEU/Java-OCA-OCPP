package eu.chargetime.ocpp.model.basic.types;
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

import eu.chargetime.ocpp.model.basic.BootNotificationRequest;

/** Accepted values used with {@link BootNotificationRequest}. */
public enum BootReasonEnumType {
  /** The Charging Station rebooted due to an application error. */
  ApplicationReset,

  /** The Charging Station rebooted due to a firmware update. */
  FirmwareUpdate,

  /** The Charging Station rebooted due to a local reset command. */
  LocalReset,

  /** The Charging Station powered up and registers itself with the CSMS. */
  PowerUp,

  /** The Charging Station rebooted due to a remote reset command. */
  RemoteReset,

  /** The Charging Station rebooted due to a scheduled reset command. */
  ScheduledReset,

  /** Requested by the CSMS via a TriggerMessage. */
  Triggered,

  /** The boot reason is unknown. */
  Unknown,

  /** The Charging Station rebooted due to an elapsed watchdog timer. */
  Watchdog
}
