/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

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

package eu.chargetime.ocpp.v201.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.BootReasonEnum;
import eu.chargetime.ocpp.v201.model.types.ChargingStation;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * BootNotificationRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class BootNotificationRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Charge Point
   *
   * <p>The physical system where an Electrical Vehicle (EV) can be charged.
   */
  private ChargingStation chargingStation;

  /** The reason for sending this message to the CSMS. */
  private BootReasonEnum reason;

  /**
   * Constructor for the BootNotificationRequest class
   *
   * @param chargingStation The physical system where an Electrical Vehicle (EV) can be charged.
   * @param reason The reason for sending this message to the CSMS.
   */
  public BootNotificationRequest(ChargingStation chargingStation, BootReasonEnum reason) {
    setChargingStation(chargingStation);
    setReason(reason);
  }

  /**
   * Gets custom data
   *
   * @return Custom data
   */
  @Nullable
  public CustomData getCustomData() {
    return customData;
  }

  /**
   * Sets custom data
   *
   * @param customData Custom data
   */
  public void setCustomData(@Nullable CustomData customData) {
    if (!isValidCustomData(customData)) {
      throw new PropertyConstraintException(customData, "customData is invalid");
    }
    this.customData = customData;
  }

  /**
   * Returns whether the given customData is valid
   *
   * @param customData the customData to check the validity of
   * @return {@code true} if customData is valid, {@code false} if not
   */
  private boolean isValidCustomData(@Nullable CustomData customData) {
    return customData == null || customData.validate();
  }

  /**
   * Adds custom data
   *
   * @param customData Custom data
   * @return this
   */
  public BootNotificationRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the physical system where an Electrical Vehicle (EV) can be charged.
   *
   * @return The physical system where an Electrical Vehicle (EV) can be charged
   */
  public ChargingStation getChargingStation() {
    return chargingStation;
  }

  /**
   * Sets the physical system where an Electrical Vehicle (EV) can be charged.
   *
   * @param chargingStation The physical system where an Electrical Vehicle (EV) can be charged
   */
  public void setChargingStation(ChargingStation chargingStation) {
    if (!isValidChargingStation(chargingStation)) {
      throw new PropertyConstraintException(chargingStation, "chargingStation is invalid");
    }
    this.chargingStation = chargingStation;
  }

  /**
   * Returns whether the given chargingStation is valid
   *
   * @param chargingStation the chargingStation to check the validity of
   * @return {@code true} if chargingStation is valid, {@code false} if not
   */
  private boolean isValidChargingStation(ChargingStation chargingStation) {
    return chargingStation != null && chargingStation.validate();
  }

  /**
   * Gets the reason for sending this message to the CSMS.
   *
   * @return The reason for sending this message to the CSMS
   */
  public BootReasonEnum getReason() {
    return reason;
  }

  /**
   * Sets the reason for sending this message to the CSMS.
   *
   * @param reason The reason for sending this message to the CSMS
   */
  public void setReason(BootReasonEnum reason) {
    if (!isValidReason(reason)) {
      throw new PropertyConstraintException(reason, "reason is invalid");
    }
    this.reason = reason;
  }

  /**
   * Returns whether the given reason is valid
   *
   * @param reason the reason to check the validity of
   * @return {@code true} if reason is valid, {@code false} if not
   */
  private boolean isValidReason(BootReasonEnum reason) {
    return reason != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidChargingStation(chargingStation)
        && isValidReason(reason);
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BootNotificationRequest that = (BootNotificationRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(chargingStation, that.chargingStation)
        && Objects.equals(reason, that.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, chargingStation, reason);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("chargingStation", chargingStation)
        .add("reason", reason)
        .add("isValid", validate())
        .toString();
  }
}
