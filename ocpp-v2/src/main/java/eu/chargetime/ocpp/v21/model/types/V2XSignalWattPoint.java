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

/** A point of a signal-watt curve. */
public final class V2XSignalWattPoint {
  /** Signal value from an AFRRSignalRequest. */
  private Integer signal;

  /** Power in W to charge (positive) or discharge (negative) at specified frequency. */
  private Double power;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the V2XSignalWattPoint class
   *
   * @param signal Signal value from an AFRRSignalRequest.
   * @param power Power in W to charge (positive) or discharge (negative) at specified frequency.
   */
  public V2XSignalWattPoint(Integer signal, Double power) {
    setSignal(signal);
    setPower(power);
  }

  /**
   * Gets signal value from an AFRRSignalRequest.
   *
   * @return Signal value from an AFRRSignalRequest
   */
  public Integer getSignal() {
    return signal;
  }

  /**
   * Sets signal value from an AFRRSignalRequest.
   *
   * @param signal Signal value from an AFRRSignalRequest
   */
  public void setSignal(Integer signal) {
    if (!isValidSignal(signal)) {
      throw new PropertyConstraintException(signal, "signal is invalid");
    }
    this.signal = signal;
  }

  /**
   * Returns whether the given signal is valid
   *
   * @param signal the signal to check the validity of
   * @return {@code true} if signal is valid, {@code false} if not
   */
  private boolean isValidSignal(Integer signal) {
    return signal != null;
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
  public V2XSignalWattPoint withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidSignal(signal) && isValidPower(power) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2XSignalWattPoint that = (V2XSignalWattPoint) o;
    return Objects.equals(signal, that.signal)
        && Objects.equals(power, that.power)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signal, power, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("signal", signal)
        .add("power", power)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
