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
import eu.chargetime.ocpp.v201.model.types.ChargingSchedule;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyEVChargingScheduleRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class NotifyEVChargingScheduleRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Periods contained in the charging profile are relative to this point in time. */
  private ZonedDateTime timeBase;

  /**
   * Charging Schedule
   *
   * <p>Charging schedule structure defines a list of charging periods, as used in:
   * GetCompositeSchedule.conf and ChargingProfile.
   */
  private ChargingSchedule chargingSchedule;

  /**
   * The charging schedule contained in this notification applies to an EVSE. EvseId must be greater
   * than 0.
   */
  private Integer evseId;

  /**
   * Constructor for the NotifyEVChargingScheduleRequest class
   *
   * @param timeBase Periods contained in the charging profile are relative to this point in time.
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: GetCompositeSchedule.conf and ChargingProfile.
   * @param evseId The charging schedule contained in this notification applies to an EVSE. EvseId
   *     must be greater than 0.
   */
  public NotifyEVChargingScheduleRequest(
      ZonedDateTime timeBase, ChargingSchedule chargingSchedule, Integer evseId) {
    setTimeBase(timeBase);
    setChargingSchedule(chargingSchedule);
    setEvseId(evseId);
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
  public NotifyEVChargingScheduleRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets periods contained in the charging profile are relative to this point in time.
   *
   * @return Periods contained in the charging profile are relative to this point in time
   */
  public ZonedDateTime getTimeBase() {
    return timeBase;
  }

  /**
   * Sets periods contained in the charging profile are relative to this point in time.
   *
   * @param timeBase Periods contained in the charging profile are relative to this point in time
   */
  public void setTimeBase(ZonedDateTime timeBase) {
    if (!isValidTimeBase(timeBase)) {
      throw new PropertyConstraintException(timeBase, "timeBase is invalid");
    }
    this.timeBase = timeBase;
  }

  /**
   * Returns whether the given timeBase is valid
   *
   * @param timeBase the timeBase to check the validity of
   * @return {@code true} if timeBase is valid, {@code false} if not
   */
  private boolean isValidTimeBase(ZonedDateTime timeBase) {
    return timeBase != null;
  }

  /**
   * Gets charging schedule structure defines a list of charging periods, as used in:
   * GetCompositeSchedule.conf and ChargingProfile.
   *
   * @return Charging schedule structure defines a list of charging periods, as used in:
   *     GetCompositeSchedule.conf and ChargingProfile
   */
  public ChargingSchedule getChargingSchedule() {
    return chargingSchedule;
  }

  /**
   * Sets charging schedule structure defines a list of charging periods, as used in:
   * GetCompositeSchedule.conf and ChargingProfile.
   *
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: GetCompositeSchedule.conf and ChargingProfile
   */
  public void setChargingSchedule(ChargingSchedule chargingSchedule) {
    if (!isValidChargingSchedule(chargingSchedule)) {
      throw new PropertyConstraintException(chargingSchedule, "chargingSchedule is invalid");
    }
    this.chargingSchedule = chargingSchedule;
  }

  /**
   * Returns whether the given chargingSchedule is valid
   *
   * @param chargingSchedule the chargingSchedule to check the validity of
   * @return {@code true} if chargingSchedule is valid, {@code false} if not
   */
  private boolean isValidChargingSchedule(ChargingSchedule chargingSchedule) {
    return chargingSchedule != null && chargingSchedule.validate();
  }

  /**
   * Gets the charging schedule contained in this notification applies to an EVSE. EvseId must be
   * greater than 0.
   *
   * @return The charging schedule contained in this notification applies to an EVSE
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the charging schedule contained in this notification applies to an EVSE. EvseId must be
   * greater than 0.
   *
   * @param evseId The charging schedule contained in this notification applies to an EVSE
   */
  public void setEvseId(Integer evseId) {
    if (!isValidEvseId(evseId)) {
      throw new PropertyConstraintException(evseId, "evseId is invalid");
    }
    this.evseId = evseId;
  }

  /**
   * Returns whether the given evseId is valid
   *
   * @param evseId the evseId to check the validity of
   * @return {@code true} if evseId is valid, {@code false} if not
   */
  private boolean isValidEvseId(Integer evseId) {
    return evseId != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidTimeBase(timeBase)
        && isValidChargingSchedule(chargingSchedule)
        && isValidEvseId(evseId);
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
    NotifyEVChargingScheduleRequest that = (NotifyEVChargingScheduleRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(timeBase, that.timeBase)
        && Objects.equals(chargingSchedule, that.chargingSchedule)
        && Objects.equals(evseId, that.evseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, timeBase, chargingSchedule, evseId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("timeBase", timeBase)
        .add("chargingSchedule", chargingSchedule)
        .add("evseId", evseId)
        .add("isValid", validate())
        .toString();
  }
}
