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

/** FreqDroopType */
public final class FreqDroop {
  /** Priority of setting (0=highest) */
  private Integer priority;

  /** Over-frequency start of droop */
  private Double overFreq;

  /** Under-frequency start of droop */
  private Double underFreq;

  /** Over-frequency droop per unit, oFDroop */
  private Double overDroop;

  /** Under-frequency droop per unit, uFDroop */
  private Double underDroop;

  /** Open loop response time in seconds */
  private Double responseTime;

  /** Time when this setting becomes active */
  @Nullable private ZonedDateTime startTime;

  /** Duration in seconds that this setting is active */
  @Nullable private Double duration;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the FreqDroop class
   *
   * @param priority Priority of setting (0=highest)
   * @param overFreq Over-frequency start of droop
   * @param underFreq Under-frequency start of droop
   * @param overDroop Over-frequency droop per unit, oFDroop
   * @param underDroop Under-frequency droop per unit, uFDroop
   * @param responseTime Open loop response time in seconds
   */
  public FreqDroop(
      Integer priority,
      Double overFreq,
      Double underFreq,
      Double overDroop,
      Double underDroop,
      Double responseTime) {
    setPriority(priority);
    setOverFreq(overFreq);
    setUnderFreq(underFreq);
    setOverDroop(overDroop);
    setUnderDroop(underDroop);
    setResponseTime(responseTime);
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
   * Gets over-frequency start of droop
   *
   * @return Over-frequency start of droop
   */
  public Double getOverFreq() {
    return overFreq;
  }

  /**
   * Sets over-frequency start of droop
   *
   * @param overFreq Over-frequency start of droop
   */
  public void setOverFreq(Double overFreq) {
    if (!isValidOverFreq(overFreq)) {
      throw new PropertyConstraintException(overFreq, "overFreq is invalid");
    }
    this.overFreq = overFreq;
  }

  /**
   * Returns whether the given overFreq is valid
   *
   * @param overFreq the overFreq to check the validity of
   * @return {@code true} if overFreq is valid, {@code false} if not
   */
  private boolean isValidOverFreq(Double overFreq) {
    return overFreq != null;
  }

  /**
   * Gets under-frequency start of droop
   *
   * @return Under-frequency start of droop
   */
  public Double getUnderFreq() {
    return underFreq;
  }

  /**
   * Sets under-frequency start of droop
   *
   * @param underFreq Under-frequency start of droop
   */
  public void setUnderFreq(Double underFreq) {
    if (!isValidUnderFreq(underFreq)) {
      throw new PropertyConstraintException(underFreq, "underFreq is invalid");
    }
    this.underFreq = underFreq;
  }

  /**
   * Returns whether the given underFreq is valid
   *
   * @param underFreq the underFreq to check the validity of
   * @return {@code true} if underFreq is valid, {@code false} if not
   */
  private boolean isValidUnderFreq(Double underFreq) {
    return underFreq != null;
  }

  /**
   * Gets over-frequency droop per unit, oFDroop
   *
   * @return Over-frequency droop per unit, oFDroop
   */
  public Double getOverDroop() {
    return overDroop;
  }

  /**
   * Sets over-frequency droop per unit, oFDroop
   *
   * @param overDroop Over-frequency droop per unit, oFDroop
   */
  public void setOverDroop(Double overDroop) {
    if (!isValidOverDroop(overDroop)) {
      throw new PropertyConstraintException(overDroop, "overDroop is invalid");
    }
    this.overDroop = overDroop;
  }

  /**
   * Returns whether the given overDroop is valid
   *
   * @param overDroop the overDroop to check the validity of
   * @return {@code true} if overDroop is valid, {@code false} if not
   */
  private boolean isValidOverDroop(Double overDroop) {
    return overDroop != null;
  }

  /**
   * Gets under-frequency droop per unit, uFDroop
   *
   * @return Under-frequency droop per unit, uFDroop
   */
  public Double getUnderDroop() {
    return underDroop;
  }

  /**
   * Sets under-frequency droop per unit, uFDroop
   *
   * @param underDroop Under-frequency droop per unit, uFDroop
   */
  public void setUnderDroop(Double underDroop) {
    if (!isValidUnderDroop(underDroop)) {
      throw new PropertyConstraintException(underDroop, "underDroop is invalid");
    }
    this.underDroop = underDroop;
  }

  /**
   * Returns whether the given underDroop is valid
   *
   * @param underDroop the underDroop to check the validity of
   * @return {@code true} if underDroop is valid, {@code false} if not
   */
  private boolean isValidUnderDroop(Double underDroop) {
    return underDroop != null;
  }

  /**
   * Gets open loop response time in seconds
   *
   * @return Open loop response time in seconds
   */
  public Double getResponseTime() {
    return responseTime;
  }

  /**
   * Sets open loop response time in seconds
   *
   * @param responseTime Open loop response time in seconds
   */
  public void setResponseTime(Double responseTime) {
    if (!isValidResponseTime(responseTime)) {
      throw new PropertyConstraintException(responseTime, "responseTime is invalid");
    }
    this.responseTime = responseTime;
  }

  /**
   * Returns whether the given responseTime is valid
   *
   * @param responseTime the responseTime to check the validity of
   * @return {@code true} if responseTime is valid, {@code false} if not
   */
  private boolean isValidResponseTime(Double responseTime) {
    return responseTime != null;
  }

  /**
   * Gets time when this setting becomes active
   *
   * @return Time when this setting becomes active
   */
  @Nullable
  public ZonedDateTime getStartTime() {
    return startTime;
  }

  /**
   * Sets time when this setting becomes active
   *
   * @param startTime Time when this setting becomes active
   */
  public void setStartTime(@Nullable ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  /**
   * Adds time when this setting becomes active
   *
   * @param startTime Time when this setting becomes active
   * @return this
   */
  public FreqDroop withStartTime(@Nullable ZonedDateTime startTime) {
    setStartTime(startTime);
    return this;
  }

  /**
   * Gets duration in seconds that this setting is active
   *
   * @return Duration in seconds that this setting is active
   */
  @Nullable
  public Double getDuration() {
    return duration;
  }

  /**
   * Sets duration in seconds that this setting is active
   *
   * @param duration Duration in seconds that this setting is active
   */
  public void setDuration(@Nullable Double duration) {
    this.duration = duration;
  }

  /**
   * Adds duration in seconds that this setting is active
   *
   * @param duration Duration in seconds that this setting is active
   * @return this
   */
  public FreqDroop withDuration(@Nullable Double duration) {
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
  public FreqDroop withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidPriority(priority)
        && isValidOverFreq(overFreq)
        && isValidUnderFreq(underFreq)
        && isValidOverDroop(overDroop)
        && isValidUnderDroop(underDroop)
        && isValidResponseTime(responseTime)
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
    FreqDroop that = (FreqDroop) o;
    return Objects.equals(priority, that.priority)
        && Objects.equals(overFreq, that.overFreq)
        && Objects.equals(underFreq, that.underFreq)
        && Objects.equals(overDroop, that.overDroop)
        && Objects.equals(underDroop, that.underDroop)
        && Objects.equals(responseTime, that.responseTime)
        && Objects.equals(startTime, that.startTime)
        && Objects.equals(duration, that.duration)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        priority,
        overFreq,
        underFreq,
        overDroop,
        underDroop,
        responseTime,
        startTime,
        duration,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("priority", priority)
        .add("overFreq", overFreq)
        .add("underFreq", underFreq)
        .add("overDroop", overDroop)
        .add("underDroop", underDroop)
        .add("responseTime", responseTime)
        .add("startTime", startTime)
        .add("duration", duration)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
