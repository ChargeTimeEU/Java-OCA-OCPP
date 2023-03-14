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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/** Composite Schedule */
public final class CompositeSchedule {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Charging Schedule Period
   *
   * <p>Charging schedule period structure defines a time period in a charging schedule.
   */
  private ChargingSchedulePeriod[] chargingSchedulePeriod;

  /**
   * The ID of the EVSE for which the schedule is requested. When evseid=0, the Charging Station
   * calculated the expected consumption for the grid connection.
   */
  private Integer evseId;

  /** Duration of the schedule in seconds. */
  private Integer duration;

  /**
   * Composite Schedule. Start. Date Time
   *
   * <p>Date and time at which the schedule becomes active. All time measurements within the
   * schedule are relative to this timestamp.
   */
  private ZonedDateTime scheduleStart;

  /** The unit of measure Limit is expressed in. */
  private ChargingRateUnitEnum chargingRateUnit;

  /**
   * Constructor for the CompositeSchedule class
   *
   * @param chargingSchedulePeriod Charging schedule period structure defines a time period in a
   *     charging schedule.
   * @param evseId The ID of the EVSE for which the schedule is requested. When evseid=0, the
   *     Charging Station calculated the expected consumption for the grid connection.
   * @param duration Duration of the schedule in seconds.
   * @param scheduleStart Date and time at which the schedule becomes active. All time measurements
   *     within the schedule are relative to this timestamp.
   * @param chargingRateUnit The unit of measure Limit is expressed in.
   */
  public CompositeSchedule(
      ChargingSchedulePeriod[] chargingSchedulePeriod,
      Integer evseId,
      Integer duration,
      ZonedDateTime scheduleStart,
      ChargingRateUnitEnum chargingRateUnit) {
    setChargingSchedulePeriod(chargingSchedulePeriod);
    setEvseId(evseId);
    setDuration(duration);
    setScheduleStart(scheduleStart);
    setChargingRateUnit(chargingRateUnit);
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

  /**
   * Gets charging schedule period structure defines a time period in a charging schedule.
   *
   * @return Charging schedule period structure defines a time period in a charging schedule
   */
  public ChargingSchedulePeriod[] getChargingSchedulePeriod() {
    return chargingSchedulePeriod;
  }

  /**
   * Sets charging schedule period structure defines a time period in a charging schedule.
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
   * Gets the ID of the EVSE for which the schedule is requested. When evseid=0, the Charging
   * Station calculated the expected consumption for the grid connection.
   *
   * @return The ID of the EVSE for which the schedule is requested
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the ID of the EVSE for which the schedule is requested. When evseid=0, the Charging
   * Station calculated the expected consumption for the grid connection.
   *
   * @param evseId The ID of the EVSE for which the schedule is requested
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

  /**
   * Gets duration of the schedule in seconds.
   *
   * @return Duration of the schedule in seconds
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets duration of the schedule in seconds.
   *
   * @param duration Duration of the schedule in seconds
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
   * Gets date and time at which the schedule becomes active. All time measurements within the
   * schedule are relative to this timestamp.
   *
   * @return Date and time at which the schedule becomes active
   */
  public ZonedDateTime getScheduleStart() {
    return scheduleStart;
  }

  /**
   * Sets date and time at which the schedule becomes active. All time measurements within the
   * schedule are relative to this timestamp.
   *
   * @param scheduleStart Date and time at which the schedule becomes active
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
   * Gets the unit of measure Limit is expressed in.
   *
   * @return The unit of measure Limit is expressed in
   */
  public ChargingRateUnitEnum getChargingRateUnit() {
    return chargingRateUnit;
  }

  /**
   * Sets the unit of measure Limit is expressed in.
   *
   * @param chargingRateUnit The unit of measure Limit is expressed in
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

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidChargingSchedulePeriod(chargingSchedulePeriod)
        && isValidEvseId(evseId)
        && isValidDuration(duration)
        && isValidScheduleStart(scheduleStart)
        && isValidChargingRateUnit(chargingRateUnit);
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
    return Objects.equals(customData, that.customData)
        && Arrays.equals(chargingSchedulePeriod, that.chargingSchedulePeriod)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(duration, that.duration)
        && Objects.equals(scheduleStart, that.scheduleStart)
        && Objects.equals(chargingRateUnit, that.chargingRateUnit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        Arrays.hashCode(chargingSchedulePeriod),
        evseId,
        duration,
        scheduleStart,
        chargingRateUnit);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("chargingSchedulePeriod", chargingSchedulePeriod)
        .add("evseId", evseId)
        .add("duration", duration)
        .add("scheduleStart", scheduleStart)
        .add("chargingRateUnit", chargingRateUnit)
        .add("isValid", validate())
        .toString();
  }
}
