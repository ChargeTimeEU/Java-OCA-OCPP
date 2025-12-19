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

/** FixedPFType */
public final class FixedPF {
  /** Priority of setting (0=highest) */
  private Integer priority;

  /** Power factor, cos(phi), as value between 0..1. */
  private Double displacement;

  /**
   * True when absorbing reactive power (under-excited), false when injecting reactive power
   * (over-excited).
   */
  private Boolean excitation;

  /** Time when this setting becomes active */
  @Nullable private ZonedDateTime startTime;

  /** Duration in seconds that this setting is active. */
  @Nullable private Double duration;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the FixedPF class
   *
   * @param priority Priority of setting (0=highest)
   * @param displacement Power factor, cos(phi), as value between 0..1.
   * @param excitation True when absorbing reactive power (under-excited), false when injecting
   *     reactive power (over-excited).
   */
  public FixedPF(Integer priority, Double displacement, Boolean excitation) {
    setPriority(priority);
    setDisplacement(displacement);
    setExcitation(excitation);
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
   * Gets power factor, cos(phi), as value between 0..1.
   *
   * @return Power factor, cos(phi), as value between 0..1
   */
  public Double getDisplacement() {
    return displacement;
  }

  /**
   * Sets power factor, cos(phi), as value between 0..1.
   *
   * @param displacement Power factor, cos(phi), as value between 0..1
   */
  public void setDisplacement(Double displacement) {
    if (!isValidDisplacement(displacement)) {
      throw new PropertyConstraintException(displacement, "displacement is invalid");
    }
    this.displacement = displacement;
  }

  /**
   * Returns whether the given displacement is valid
   *
   * @param displacement the displacement to check the validity of
   * @return {@code true} if displacement is valid, {@code false} if not
   */
  private boolean isValidDisplacement(Double displacement) {
    return displacement != null;
  }

  /**
   * Gets true when absorbing reactive power (under-excited), false when injecting reactive power
   * (over-excited).
   *
   * @return True when absorbing reactive power (under-excited), false when injecting reactive power
   *     (over-excited)
   */
  public Boolean getExcitation() {
    return excitation;
  }

  /**
   * Sets true when absorbing reactive power (under-excited), false when injecting reactive power
   * (over-excited).
   *
   * @param excitation True when absorbing reactive power (under-excited), false when injecting
   *     reactive power (over-excited)
   */
  public void setExcitation(Boolean excitation) {
    if (!isValidExcitation(excitation)) {
      throw new PropertyConstraintException(excitation, "excitation is invalid");
    }
    this.excitation = excitation;
  }

  /**
   * Returns whether the given excitation is valid
   *
   * @param excitation the excitation to check the validity of
   * @return {@code true} if excitation is valid, {@code false} if not
   */
  private boolean isValidExcitation(Boolean excitation) {
    return excitation != null;
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
  public FixedPF withStartTime(@Nullable ZonedDateTime startTime) {
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
  public FixedPF withDuration(@Nullable Double duration) {
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
  public FixedPF withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidPriority(priority)
        && isValidDisplacement(displacement)
        && isValidExcitation(excitation)
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
    FixedPF that = (FixedPF) o;
    return Objects.equals(priority, that.priority)
        && Objects.equals(displacement, that.displacement)
        && Objects.equals(excitation, that.excitation)
        && Objects.equals(startTime, that.startTime)
        && Objects.equals(duration, that.duration)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priority, displacement, excitation, startTime, duration, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("priority", priority)
        .add("displacement", displacement)
        .add("excitation", excitation)
        .add("startTime", startTime)
        .add("duration", duration)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
