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

/** LimitMaxDischargeType */
public final class LimitMaxDischarge {
  /** Priority of setting (0=highest) */
  private Integer priority;

  /**
   * Only for PowerMonitoring.
   *
   * <p>The value specifies a percentage (0 to 100) of the rated maximum discharge power of EV. The
   * PowerMonitoring curve becomes active when power exceeds this percentage.
   */
  @Nullable private Double pctMaxDischargePower;

  /** powerMonitoringMustTrip */
  @Nullable private DERCurve powerMonitoringMustTrip;

  /** Time when this setting becomes active */
  @Nullable private ZonedDateTime startTime;

  /** Duration in seconds that this setting is active */
  @Nullable private Double duration;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the LimitMaxDischarge class
   *
   * @param priority Priority of setting (0=highest)
   */
  public LimitMaxDischarge(Integer priority) {
    setPriority(priority);
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
   * Gets only for PowerMonitoring.
   *
   * @return Only for PowerMonitoring
   */
  @Nullable
  public Double getPctMaxDischargePower() {
    return pctMaxDischargePower;
  }

  /**
   * Sets only for PowerMonitoring.
   *
   * @param pctMaxDischargePower Only for PowerMonitoring
   */
  public void setPctMaxDischargePower(@Nullable Double pctMaxDischargePower) {
    this.pctMaxDischargePower = pctMaxDischargePower;
  }

  /**
   * Adds only for PowerMonitoring.
   *
   * @param pctMaxDischargePower Only for PowerMonitoring
   * @return this
   */
  public LimitMaxDischarge withPctMaxDischargePower(@Nullable Double pctMaxDischargePower) {
    setPctMaxDischargePower(pctMaxDischargePower);
    return this;
  }

  /**
   * Gets powerMonitoringMustTrip
   *
   * @return powerMonitoringMustTrip
   */
  @Nullable
  public DERCurve getPowerMonitoringMustTrip() {
    return powerMonitoringMustTrip;
  }

  /**
   * Sets powerMonitoringMustTrip
   *
   * @param powerMonitoringMustTrip powerMonitoringMustTrip
   */
  public void setPowerMonitoringMustTrip(@Nullable DERCurve powerMonitoringMustTrip) {
    if (!isValidPowerMonitoringMustTrip(powerMonitoringMustTrip)) {
      throw new PropertyConstraintException(
          powerMonitoringMustTrip, "powerMonitoringMustTrip is invalid");
    }
    this.powerMonitoringMustTrip = powerMonitoringMustTrip;
  }

  /**
   * Returns whether the given powerMonitoringMustTrip is valid
   *
   * @param powerMonitoringMustTrip the powerMonitoringMustTrip to check the validity of
   * @return {@code true} if powerMonitoringMustTrip is valid, {@code false} if not
   */
  private boolean isValidPowerMonitoringMustTrip(@Nullable DERCurve powerMonitoringMustTrip) {
    return powerMonitoringMustTrip == null || powerMonitoringMustTrip.validate();
  }

  /**
   * Adds powerMonitoringMustTrip
   *
   * @param powerMonitoringMustTrip powerMonitoringMustTrip
   * @return this
   */
  public LimitMaxDischarge withPowerMonitoringMustTrip(@Nullable DERCurve powerMonitoringMustTrip) {
    setPowerMonitoringMustTrip(powerMonitoringMustTrip);
    return this;
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
  public LimitMaxDischarge withStartTime(@Nullable ZonedDateTime startTime) {
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
  public LimitMaxDischarge withDuration(@Nullable Double duration) {
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
  public LimitMaxDischarge withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidPriority(priority)
        && isValidPowerMonitoringMustTrip(powerMonitoringMustTrip)
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
    LimitMaxDischarge that = (LimitMaxDischarge) o;
    return Objects.equals(priority, that.priority)
        && Objects.equals(pctMaxDischargePower, that.pctMaxDischargePower)
        && Objects.equals(powerMonitoringMustTrip, that.powerMonitoringMustTrip)
        && Objects.equals(startTime, that.startTime)
        && Objects.equals(duration, that.duration)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        priority, pctMaxDischargePower, powerMonitoringMustTrip, startTime, duration, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("priority", priority)
        .add("pctMaxDischargePower", pctMaxDischargePower)
        .add("powerMonitoringMustTrip", powerMonitoringMustTrip)
        .add("startTime", startTime)
        .add("duration", duration)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
