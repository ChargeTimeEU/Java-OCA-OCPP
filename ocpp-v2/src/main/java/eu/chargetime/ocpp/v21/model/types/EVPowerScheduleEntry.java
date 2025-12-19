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

/**
 * An entry in schedule of the energy amount over time that EV is willing to discharge. A negative
 * value indicates the willingness to discharge under specific conditions, a positive value
 * indicates that the EV currently is not able to offer energy to discharge.
 */
public final class EVPowerScheduleEntry {
  /** The duration of this entry. */
  private Integer duration;

  /**
   * Maximum amount of power for the duration of this EVPowerScheduleEntry to be discharged from the
   * EV battery through EVSE power outlet. Negative values are used for discharging.
   */
  private Double power;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the EVPowerScheduleEntry class
   *
   * @param duration The duration of this entry.
   * @param power Maximum amount of power for the duration of this EVPowerScheduleEntry to be
   *     discharged from the EV battery through EVSE power outlet. Negative values are used for
   *     discharging.
   */
  public EVPowerScheduleEntry(Integer duration, Double power) {
    setDuration(duration);
    setPower(power);
  }

  /**
   * Gets the duration of this entry.
   *
   * @return The duration of this entry
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets the duration of this entry.
   *
   * @param duration The duration of this entry
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
   * Gets maximum amount of power for the duration of this EVPowerScheduleEntry to be discharged
   * from the EV battery through EVSE power outlet. Negative values are used for discharging.
   *
   * @return Maximum amount of power for the duration of this EVPowerScheduleEntry to be discharged
   *     from the EV battery through EVSE power outlet
   */
  public Double getPower() {
    return power;
  }

  /**
   * Sets maximum amount of power for the duration of this EVPowerScheduleEntry to be discharged
   * from the EV battery through EVSE power outlet. Negative values are used for discharging.
   *
   * @param power Maximum amount of power for the duration of this EVPowerScheduleEntry to be
   *     discharged from the EV battery through EVSE power outlet
   */
  public void setPower(Double power) {
    if (!isValidPower(power)) {
      throw new PropertyConstraintException(power, "power is invalid");
    }
    this.power = power;
  }

  /**
   * Returns whether the given power is valid
   *
   * @param power the power to check the validity of
   * @return {@code true} if power is valid, {@code false} if not
   */
  private boolean isValidPower(Double power) {
    return power != null;
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
  public EVPowerScheduleEntry withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidDuration(duration) && isValidPower(power) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EVPowerScheduleEntry that = (EVPowerScheduleEntry) o;
    return Objects.equals(duration, that.duration)
        && Objects.equals(power, that.power)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(duration, power, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("duration", duration)
        .add("power", power)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
