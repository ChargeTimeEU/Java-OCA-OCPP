package eu.chargetime.ocpp.model.basic;
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

import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.basic.types.BootReasonEnumType;
import eu.chargetime.ocpp.model.basic.types.ChargingStationType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;

/** Sent by the Charging Station to the CSMS. */
public class BootNotificationRequest implements Request {
  private transient RequiredValidator validator = new RequiredValidator();

  private BootReasonEnumType reason;
  private ChargingStationType chargingStation;

  public BootNotificationRequest() {}

  /**
   * This contains the reason for sending this message to the CSMS.
   *
   * @return {@link BootReasonEnumType}
   */
  public BootReasonEnumType getReason() {
    return reason;
  }

  /**
   * Required. This contains the reason for sending this message to the CSMS.
   *
   * @param reason {@link BootReasonEnumType}
   */
  public void setReason(BootReasonEnumType reason) {
    validator.validate(reason);
    this.reason = reason;
  }

  /**
   * Identifies the Charging Station.
   *
   * @return {@link ChargingStationType}
   */
  public ChargingStationType getChargingStation() {
    return chargingStation;
  }

  /**
   * Required. Identifies the Charging Station.
   *
   * @param chargingStation {@link ChargingStationType}
   */
  public void setChargingStation(ChargingStationType chargingStation) {
    this.chargingStation = chargingStation;
  }

  @Override
  public boolean validate() {
    return validator.safeValidate(reason)
        && validator.safeValidate(chargingStation)
        && chargingStation.validate();
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BootNotificationRequest that = (BootNotificationRequest) o;
    return Objects.equals(reason, that.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reason, chargingStation);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("reason", reason)
        .add("chargingStation", chargingStation)
        .toString();
  }
}
