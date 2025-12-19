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
import java.util.Objects;
import javax.annotation.Nullable;

/** FixedVarType */
public final class FixedVar {
  /** Priority of setting (0=highest) */
  private Integer priority;

  /**
   * The value specifies a target var output interpreted as a signed percentage (-100 to 100). A
   * negative value refers to charging, whereas a positive one refers to discharging. The value type
   * is determined by the unit field.
   */
  private Double setpoint;

  /** Unit of the setpoint. */
  private DERUnitEnum unit;

  /** Time when this setting becomes active. */
  @Nullable private ZonedDateTime startTime;

  /** Duration in seconds that this setting is active. */
  @Nullable private Double duration;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the FixedVar class
   *
   * @param priority Priority of setting (0=highest)
   * @param setpoint The value specifies a target var output interpreted as a signed percentage
   *     (-100 to 100). A negative value refers to charging, whereas a positive one refers to
   *     discharging. The value type is determined by the unit field.
   * @param unit Unit of the setpoint.
   */
  public FixedVar(Integer priority, Double setpoint, DERUnitEnum unit) {
    setPriority(priority);
    setSetpoint(setpoint);
    setUnit(unit);
  }

  /**
   * Gets priority of setting (0=highest)
   *
   * @return Priority of setting (0=highest)
   */
  public Integer getPriority() {
    return priority;
  }

  /**
   * Sets priority of setting (0=highest)
   *
   * @param priority Priority of setting (0=highest)
   */
  public void setPriority(Integer priority) {
    if (!isValidPriority(priority)) {
      throw new PropertyConstraintException(priority, "priority is invalid");
    }
    this.priority = priority;
  }

  /**
   * Returns whether the given priority is valid
   *
   * @param priority the priority to check the validity of
   * @return {@code true} if priority is valid, {@code false} if not
   */
  private boolean isValidPriority(Integer priority) {
    return priority != null && priority >= 0;
  }

  /**
   * Gets the value specifies a target var output interpreted as a signed percentage (-100 to 100).
   * A negative value refers to charging, whereas a positive one refers to discharging. The value
   * type is determined by the unit field.
   *
   * @return The value specifies a target var output interpreted as a signed percentage (-100 to
   *     100)
   */
  public Double getSetpoint() {
    return setpoint;
  }

  /**
   * Sets the value specifies a target var output interpreted as a signed percentage (-100 to 100).
   * A negative value refers to charging, whereas a positive one refers to discharging. The value
   * type is determined by the unit field.
   *
   * @param setpoint The value specifies a target var output interpreted as a signed percentage
   *     (-100 to 100)
   */
  public void setSetpoint(Double setpoint) {
    if (!isValidSetpoint(setpoint)) {
      throw new PropertyConstraintException(setpoint, "setpoint is invalid");
    }
    this.setpoint = setpoint;
  }

  /**
   * Returns whether the given setpoint is valid
   *
   * @param setpoint the setpoint to check the validity of
   * @return {@code true} if setpoint is valid, {@code false} if not
   */
  private boolean isValidSetpoint(Double setpoint) {
    return setpoint != null;
  }

  /**
   * Gets unit of the setpoint.
   *
   * @return Unit of the setpoint
   */
  public DERUnitEnum getUnit() {
    return unit;
  }

  /**
   * Sets unit of the setpoint.
   *
   * @param unit Unit of the setpoint
   */
  public void setUnit(DERUnitEnum unit) {
    if (!isValidUnit(unit)) {
      throw new PropertyConstraintException(unit, "unit is invalid");
    }
    this.unit = unit;
  }

  /**
   * Returns whether the given unit is valid
   *
   * @param unit the unit to check the validity of
   * @return {@code true} if unit is valid, {@code false} if not
   */
  private boolean isValidUnit(DERUnitEnum unit) {
    return unit != null;
  }

  /**
   * Gets time when this setting becomes active.
   *
   * @return Time when this setting becomes active
   */
  @Nullable
  public ZonedDateTime getStartTime() {
    return startTime;
  }

  /**
   * Sets time when this setting becomes active.
   *
   * @param startTime Time when this setting becomes active
   */
  public void setStartTime(@Nullable ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  /**
   * Adds time when this setting becomes active.
   *
   * @param startTime Time when this setting becomes active
   * @return this
   */
  public FixedVar withStartTime(@Nullable ZonedDateTime startTime) {
    setStartTime(startTime);
    return this;
  }

  /**
   * Gets duration in seconds that this setting is active.
   *
   * @return Duration in seconds that this setting is active
   */
  @Nullable
  public Double getDuration() {
    return duration;
  }

  /**
   * Sets duration in seconds that this setting is active.
   *
   * @param duration Duration in seconds that this setting is active
   */
  public void setDuration(@Nullable Double duration) {
    this.duration = duration;
  }

  /**
   * Adds duration in seconds that this setting is active.
   *
   * @param duration Duration in seconds that this setting is active
   * @return this
   */
  public FixedVar withDuration(@Nullable Double duration) {
    setDuration(duration);
    return this;
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
  public FixedVar withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidPriority(priority)
        && isValidSetpoint(setpoint)
        && isValidUnit(unit)
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
    FixedVar that = (FixedVar) o;
    return Objects.equals(priority, that.priority)
        && Objects.equals(setpoint, that.setpoint)
        && Objects.equals(unit, that.unit)
        && Objects.equals(startTime, that.startTime)
        && Objects.equals(duration, that.duration)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priority, setpoint, unit, startTime, duration, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("priority", priority)
        .add("setpoint", setpoint)
        .add("unit", unit)
        .add("startTime", startTime)
        .add("duration", duration)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
