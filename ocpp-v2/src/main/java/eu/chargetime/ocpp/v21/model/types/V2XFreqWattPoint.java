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

/** A point of a frequency-watt curve. */
public final class V2XFreqWattPoint {
  /** Net frequency in Hz. */
  private Double frequency;

  /** Power in W to charge (positive) or discharge (negative) at specified frequency. */
  private Double power;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the V2XFreqWattPoint class
   *
   * @param frequency Net frequency in Hz.
   * @param power Power in W to charge (positive) or discharge (negative) at specified frequency.
   */
  public V2XFreqWattPoint(Double frequency, Double power) {
    setFrequency(frequency);
    setPower(power);
  }

  /**
   * Gets net frequency in Hz.
   *
   * @return Net frequency in Hz
   */
  public Double getFrequency() {
    return frequency;
  }

  /**
   * Sets net frequency in Hz.
   *
   * @param frequency Net frequency in Hz
   */
  public void setFrequency(Double frequency) {
    if (!isValidFrequency(frequency)) {
      throw new PropertyConstraintException(frequency, "frequency is invalid");
    }
    this.frequency = frequency;
  }

  /**
   * Returns whether the given frequency is valid
   *
   * @param frequency the frequency to check the validity of
   * @return {@code true} if frequency is valid, {@code false} if not
   */
  private boolean isValidFrequency(Double frequency) {
    return frequency != null;
  }

  /**
   * Gets power in W to charge (positive) or discharge (negative) at specified frequency.
   *
   * @return Power in W to charge (positive) or discharge (negative) at specified frequency
   */
  public Double getPower() {
    return power;
  }

  /**
   * Sets power in W to charge (positive) or discharge (negative) at specified frequency.
   *
   * @param power Power in W to charge (positive) or discharge (negative) at specified frequency
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
  public V2XFreqWattPoint withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidFrequency(frequency) && isValidPower(power) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2XFreqWattPoint that = (V2XFreqWattPoint) o;
    return Objects.equals(frequency, that.frequency)
        && Objects.equals(power, that.power)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frequency, power, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("frequency", frequency)
        .add("power", power)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
