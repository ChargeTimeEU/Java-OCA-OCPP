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
import java.util.Objects;
import javax.annotation.Nullable;

/** EnterServiceType */
public final class EnterService {
  /** Priority of setting (0=highest) */
  private Integer priority;

  /** Enter service voltage high */
  private Double highVoltage;

  /** Enter service voltage low */
  private Double lowVoltage;

  /** Enter service frequency high */
  private Double highFreq;

  /** Enter service frequency low */
  private Double lowFreq;

  /** Enter service delay */
  @Nullable private Double delay;

  /** Enter service randomized delay */
  @Nullable private Double randomDelay;

  /** Enter service ramp rate in seconds */
  @Nullable private Double rampRate;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the EnterService class
   *
   * @param priority Priority of setting (0=highest)
   * @param highVoltage Enter service voltage high
   * @param lowVoltage Enter service voltage low
   * @param highFreq Enter service frequency high
   * @param lowFreq Enter service frequency low
   */
  public EnterService(
      Integer priority, Double highVoltage, Double lowVoltage, Double highFreq, Double lowFreq) {
    setPriority(priority);
    setHighVoltage(highVoltage);
    setLowVoltage(lowVoltage);
    setHighFreq(highFreq);
    setLowFreq(lowFreq);
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
   * Gets enter service voltage high
   *
   * @return Enter service voltage high
   */
  public Double getHighVoltage() {
    return highVoltage;
  }

  /**
   * Sets enter service voltage high
   *
   * @param highVoltage Enter service voltage high
   */
  public void setHighVoltage(Double highVoltage) {
    if (!isValidHighVoltage(highVoltage)) {
      throw new PropertyConstraintException(highVoltage, "highVoltage is invalid");
    }
    this.highVoltage = highVoltage;
  }

  /**
   * Returns whether the given highVoltage is valid
   *
   * @param highVoltage the highVoltage to check the validity of
   * @return {@code true} if highVoltage is valid, {@code false} if not
   */
  private boolean isValidHighVoltage(Double highVoltage) {
    return highVoltage != null;
  }

  /**
   * Gets enter service voltage low
   *
   * @return Enter service voltage low
   */
  public Double getLowVoltage() {
    return lowVoltage;
  }

  /**
   * Sets enter service voltage low
   *
   * @param lowVoltage Enter service voltage low
   */
  public void setLowVoltage(Double lowVoltage) {
    if (!isValidLowVoltage(lowVoltage)) {
      throw new PropertyConstraintException(lowVoltage, "lowVoltage is invalid");
    }
    this.lowVoltage = lowVoltage;
  }

  /**
   * Returns whether the given lowVoltage is valid
   *
   * @param lowVoltage the lowVoltage to check the validity of
   * @return {@code true} if lowVoltage is valid, {@code false} if not
   */
  private boolean isValidLowVoltage(Double lowVoltage) {
    return lowVoltage != null;
  }

  /**
   * Gets enter service frequency high
   *
   * @return Enter service frequency high
   */
  public Double getHighFreq() {
    return highFreq;
  }

  /**
   * Sets enter service frequency high
   *
   * @param highFreq Enter service frequency high
   */
  public void setHighFreq(Double highFreq) {
    if (!isValidHighFreq(highFreq)) {
      throw new PropertyConstraintException(highFreq, "highFreq is invalid");
    }
    this.highFreq = highFreq;
  }

  /**
   * Returns whether the given highFreq is valid
   *
   * @param highFreq the highFreq to check the validity of
   * @return {@code true} if highFreq is valid, {@code false} if not
   */
  private boolean isValidHighFreq(Double highFreq) {
    return highFreq != null;
  }

  /**
   * Gets enter service frequency low
   *
   * @return Enter service frequency low
   */
  public Double getLowFreq() {
    return lowFreq;
  }

  /**
   * Sets enter service frequency low
   *
   * @param lowFreq Enter service frequency low
   */
  public void setLowFreq(Double lowFreq) {
    if (!isValidLowFreq(lowFreq)) {
      throw new PropertyConstraintException(lowFreq, "lowFreq is invalid");
    }
    this.lowFreq = lowFreq;
  }

  /**
   * Returns whether the given lowFreq is valid
   *
   * @param lowFreq the lowFreq to check the validity of
   * @return {@code true} if lowFreq is valid, {@code false} if not
   */
  private boolean isValidLowFreq(Double lowFreq) {
    return lowFreq != null;
  }

  /**
   * Gets enter service delay
   *
   * @return Enter service delay
   */
  @Nullable
  public Double getDelay() {
    return delay;
  }

  /**
   * Sets enter service delay
   *
   * @param delay Enter service delay
   */
  public void setDelay(@Nullable Double delay) {
    this.delay = delay;
  }

  /**
   * Adds enter service delay
   *
   * @param delay Enter service delay
   * @return this
   */
  public EnterService withDelay(@Nullable Double delay) {
    setDelay(delay);
    return this;
  }

  /**
   * Gets enter service randomized delay
   *
   * @return Enter service randomized delay
   */
  @Nullable
  public Double getRandomDelay() {
    return randomDelay;
  }

  /**
   * Sets enter service randomized delay
   *
   * @param randomDelay Enter service randomized delay
   */
  public void setRandomDelay(@Nullable Double randomDelay) {
    this.randomDelay = randomDelay;
  }

  /**
   * Adds enter service randomized delay
   *
   * @param randomDelay Enter service randomized delay
   * @return this
   */
  public EnterService withRandomDelay(@Nullable Double randomDelay) {
    setRandomDelay(randomDelay);
    return this;
  }

  /**
   * Gets enter service ramp rate in seconds
   *
   * @return Enter service ramp rate in seconds
   */
  @Nullable
  public Double getRampRate() {
    return rampRate;
  }

  /**
   * Sets enter service ramp rate in seconds
   *
   * @param rampRate Enter service ramp rate in seconds
   */
  public void setRampRate(@Nullable Double rampRate) {
    this.rampRate = rampRate;
  }

  /**
   * Adds enter service ramp rate in seconds
   *
   * @param rampRate Enter service ramp rate in seconds
   * @return this
   */
  public EnterService withRampRate(@Nullable Double rampRate) {
    setRampRate(rampRate);
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
  public EnterService withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidPriority(priority)
        && isValidHighVoltage(highVoltage)
        && isValidLowVoltage(lowVoltage)
        && isValidHighFreq(highFreq)
        && isValidLowFreq(lowFreq)
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
    EnterService that = (EnterService) o;
    return Objects.equals(priority, that.priority)
        && Objects.equals(highVoltage, that.highVoltage)
        && Objects.equals(lowVoltage, that.lowVoltage)
        && Objects.equals(highFreq, that.highFreq)
        && Objects.equals(lowFreq, that.lowFreq)
        && Objects.equals(delay, that.delay)
        && Objects.equals(randomDelay, that.randomDelay)
        && Objects.equals(rampRate, that.rampRate)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        priority,
        highVoltage,
        lowVoltage,
        highFreq,
        lowFreq,
        delay,
        randomDelay,
        rampRate,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("priority", priority)
        .add("highVoltage", highVoltage)
        .add("lowVoltage", lowVoltage)
        .add("highFreq", highFreq)
        .add("lowFreq", lowFreq)
        .add("delay", delay)
        .add("randomDelay", randomDelay)
        .add("rampRate", rampRate)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
