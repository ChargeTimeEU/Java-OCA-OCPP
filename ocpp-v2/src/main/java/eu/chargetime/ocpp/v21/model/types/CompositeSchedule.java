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

package eu.chargetime.ocpp.v21.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/** CompositeScheduleType */
public final class CompositeSchedule {
  /** evseId */
  private Integer evseId;

  /** duration */
  private Integer duration;

  /** scheduleStart */
  private ZonedDateTime scheduleStart;

  /** chargingRateUnit */
  private ChargingRateUnitEnum chargingRateUnit;

  /**
   * Charging schedule period structure defines a time period in a charging schedule. It is used in:
   * CompositeScheduleType and in ChargingScheduleType. When used in a
   * NotifyEVChargingScheduleRequest only startPeriod, limit, limitL2, limitL3 are relevant.
   */
  private ChargingSchedulePeriod[] chargingSchedulePeriod;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the CompositeSchedule class
   *
   * @param evseId evseId
   * @param duration duration
   * @param scheduleStart scheduleStart
   * @param chargingRateUnit chargingRateUnit
   * @param chargingSchedulePeriod Charging schedule period structure defines a time period in a
   *     charging schedule. It is used in: CompositeScheduleType and in ChargingScheduleType. When
   *     used in a NotifyEVChargingScheduleRequest only startPeriod, limit, limitL2, limitL3 are
   *     relevant.
   */
  public CompositeSchedule(
      Integer evseId,
      Integer duration,
      ZonedDateTime scheduleStart,
      ChargingRateUnitEnum chargingRateUnit,
      ChargingSchedulePeriod[] chargingSchedulePeriod) {
    setEvseId(evseId);
    setDuration(duration);
    setScheduleStart(scheduleStart);
    setChargingRateUnit(chargingRateUnit);
    setChargingSchedulePeriod(chargingSchedulePeriod);
  }

  /**
   * Gets evseId
   *
   * @return evseId
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets evseId
   *
   * @param evseId evseId
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
    return evseId != null && evseId >= 0;
  }

  /**
   * Gets duration
   *
   * @return duration
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets duration
   *
   * @param duration duration
   */
  public void setDuration(Integer duration) {
    if (!isValidDuration(duration)) {
      throw new PropertyConstraintException(duration, "duration is invalid");
    }
    this.duration = duration;
  }

  /**
   * Returns whether the given duration is valid
   *
   * @param duration the duration to check the validity of
   * @return {@code true} if duration is valid, {@code false} if not
   */
  private boolean isValidDuration(Integer duration) {
    return duration != null;
  }

  /**
   * Gets scheduleStart
   *
   * @return scheduleStart
   */
  public ZonedDateTime getScheduleStart() {
    return scheduleStart;
  }

  /**
   * Sets scheduleStart
   *
   * @param scheduleStart scheduleStart
   */
  public void setScheduleStart(ZonedDateTime scheduleStart) {
    if (!isValidScheduleStart(scheduleStart)) {
      throw new PropertyConstraintException(scheduleStart, "scheduleStart is invalid");
    }
    this.scheduleStart = scheduleStart;
  }

  /**
   * Returns whether the given scheduleStart is valid
   *
   * @param scheduleStart the scheduleStart to check the validity of
   * @return {@code true} if scheduleStart is valid, {@code false} if not
   */
  private boolean isValidScheduleStart(ZonedDateTime scheduleStart) {
    return scheduleStart != null;
  }

  /**
   * Gets chargingRateUnit
   *
   * @return chargingRateUnit
   */
  public ChargingRateUnitEnum getChargingRateUnit() {
    return chargingRateUnit;
  }

  /**
   * Sets chargingRateUnit
   *
   * @param chargingRateUnit chargingRateUnit
   */
  public void setChargingRateUnit(ChargingRateUnitEnum chargingRateUnit) {
    if (!isValidChargingRateUnit(chargingRateUnit)) {
      throw new PropertyConstraintException(chargingRateUnit, "chargingRateUnit is invalid");
    }
    this.chargingRateUnit = chargingRateUnit;
  }

  /**
   * Returns whether the given chargingRateUnit is valid
   *
   * @param chargingRateUnit the chargingRateUnit to check the validity of
   * @return {@code true} if chargingRateUnit is valid, {@code false} if not
   */
  private boolean isValidChargingRateUnit(ChargingRateUnitEnum chargingRateUnit) {
    return chargingRateUnit != null;
  }

  /**
   * Gets charging schedule period structure defines a time period in a charging schedule. It is
   * used in: CompositeScheduleType and in ChargingScheduleType. When used in a
   * NotifyEVChargingScheduleRequest only startPeriod, limit, limitL2, limitL3 are relevant.
   *
   * @return Charging schedule period structure defines a time period in a charging schedule
   */
  public ChargingSchedulePeriod[] getChargingSchedulePeriod() {
    return chargingSchedulePeriod;
  }

  /**
   * Sets charging schedule period structure defines a time period in a charging schedule. It is
   * used in: CompositeScheduleType and in ChargingScheduleType. When used in a
   * NotifyEVChargingScheduleRequest only startPeriod, limit, limitL2, limitL3 are relevant.
   *
   * @param chargingSchedulePeriod Charging schedule period structure defines a time period in a
   *     charging schedule
   */
  public void setChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
    if (!isValidChargingSchedulePeriod(chargingSchedulePeriod)) {
      throw new PropertyConstraintException(
          chargingSchedulePeriod, "chargingSchedulePeriod is invalid");
    }
    this.chargingSchedulePeriod = chargingSchedulePeriod;
  }

  /**
   * Returns whether the given chargingSchedulePeriod is valid
   *
   * @param chargingSchedulePeriod the chargingSchedulePeriod to check the validity of
   * @return {@code true} if chargingSchedulePeriod is valid, {@code false} if not
   */
  private boolean isValidChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
    return chargingSchedulePeriod != null
        && chargingSchedulePeriod.length >= 1
        && Arrays.stream(chargingSchedulePeriod).allMatch(item -> item.validate());
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
  public CompositeSchedule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidEvseId(evseId)
        && isValidDuration(duration)
        && isValidScheduleStart(scheduleStart)
        && isValidChargingRateUnit(chargingRateUnit)
        && isValidChargingSchedulePeriod(chargingSchedulePeriod)
        && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompositeSchedule that = (CompositeSchedule) o;
    return Objects.equals(evseId, that.evseId)
        && Objects.equals(duration, that.duration)
        && Objects.equals(scheduleStart, that.scheduleStart)
        && Objects.equals(chargingRateUnit, that.chargingRateUnit)
        && Arrays.equals(chargingSchedulePeriod, that.chargingSchedulePeriod)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        evseId,
        duration,
        scheduleStart,
        chargingRateUnit,
        Arrays.hashCode(chargingSchedulePeriod),
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evseId", evseId)
        .add("duration", duration)
        .add("scheduleStart", scheduleStart)
        .add("chargingRateUnit", chargingRateUnit)
        .add("chargingSchedulePeriod", chargingSchedulePeriod)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
