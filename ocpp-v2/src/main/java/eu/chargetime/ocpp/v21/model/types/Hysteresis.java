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

/** HysteresisType */
public final class Hysteresis {
  /**
   * High value for return to normal operation after a grid event, in absolute value. This value
   * adopts the same unit as defined by yUnit
   */
  @Nullable private Double hysteresisHigh;

  /**
   * Low value for return to normal operation after a grid event, in absolute value. This value
   * adopts the same unit as defined by yUnit
   */
  @Nullable private Double hysteresisLow;

  /**
   * Delay in seconds, once grid parameter within HysteresisLow and HysteresisHigh, for the EV to
   * return to normal operation after a grid event.
   */
  @Nullable private Double hysteresisDelay;

  /**
   * Set default rate of change (ramp rate %/s) for the EV to return to normal operation after a
   * grid event
   */
  @Nullable private Double hysteresisGradient;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the Hysteresis class */
  public Hysteresis() {}

  /**
   * Gets high value for return to normal operation after a grid event, in absolute value. This
   * value adopts the same unit as defined by yUnit
   *
   * @return High value for return to normal operation after a grid event, in absolute value
   */
  @Nullable
  public Double getHysteresisHigh() {
    return hysteresisHigh;
  }

  /**
   * Sets high value for return to normal operation after a grid event, in absolute value. This
   * value adopts the same unit as defined by yUnit
   *
   * @param hysteresisHigh High value for return to normal operation after a grid event, in absolute
   *     value
   */
  public void setHysteresisHigh(@Nullable Double hysteresisHigh) {
    this.hysteresisHigh = hysteresisHigh;
  }

  /**
   * Adds high value for return to normal operation after a grid event, in absolute value. This
   * value adopts the same unit as defined by yUnit
   *
   * @param hysteresisHigh High value for return to normal operation after a grid event, in absolute
   *     value
   * @return this
   */
  public Hysteresis withHysteresisHigh(@Nullable Double hysteresisHigh) {
    setHysteresisHigh(hysteresisHigh);
    return this;
  }

  /**
   * Gets low value for return to normal operation after a grid event, in absolute value. This value
   * adopts the same unit as defined by yUnit
   *
   * @return Low value for return to normal operation after a grid event, in absolute value
   */
  @Nullable
  public Double getHysteresisLow() {
    return hysteresisLow;
  }

  /**
   * Sets low value for return to normal operation after a grid event, in absolute value. This value
   * adopts the same unit as defined by yUnit
   *
   * @param hysteresisLow Low value for return to normal operation after a grid event, in absolute
   *     value
   */
  public void setHysteresisLow(@Nullable Double hysteresisLow) {
    this.hysteresisLow = hysteresisLow;
  }

  /**
   * Adds low value for return to normal operation after a grid event, in absolute value. This value
   * adopts the same unit as defined by yUnit
   *
   * @param hysteresisLow Low value for return to normal operation after a grid event, in absolute
   *     value
   * @return this
   */
  public Hysteresis withHysteresisLow(@Nullable Double hysteresisLow) {
    setHysteresisLow(hysteresisLow);
    return this;
  }

  /**
   * Gets delay in seconds, once grid parameter within HysteresisLow and HysteresisHigh, for the EV
   * to return to normal operation after a grid event.
   *
   * @return Delay in seconds, once grid parameter within HysteresisLow and HysteresisHigh, for the
   *     EV to return to normal operation after a grid event
   */
  @Nullable
  public Double getHysteresisDelay() {
    return hysteresisDelay;
  }

  /**
   * Sets delay in seconds, once grid parameter within HysteresisLow and HysteresisHigh, for the EV
   * to return to normal operation after a grid event.
   *
   * @param hysteresisDelay Delay in seconds, once grid parameter within HysteresisLow and
   *     HysteresisHigh, for the EV to return to normal operation after a grid event
   */
  public void setHysteresisDelay(@Nullable Double hysteresisDelay) {
    this.hysteresisDelay = hysteresisDelay;
  }

  /**
   * Adds delay in seconds, once grid parameter within HysteresisLow and HysteresisHigh, for the EV
   * to return to normal operation after a grid event.
   *
   * @param hysteresisDelay Delay in seconds, once grid parameter within HysteresisLow and
   *     HysteresisHigh, for the EV to return to normal operation after a grid event
   * @return this
   */
  public Hysteresis withHysteresisDelay(@Nullable Double hysteresisDelay) {
    setHysteresisDelay(hysteresisDelay);
    return this;
  }

  /**
   * Gets set default rate of change (ramp rate %/s) for the EV to return to normal operation after
   * a grid event
   *
   * @return Set default rate of change (ramp rate %/s) for the EV to return to normal operation
   *     after a grid event
   */
  @Nullable
  public Double getHysteresisGradient() {
    return hysteresisGradient;
  }

  /**
   * Sets set default rate of change (ramp rate %/s) for the EV to return to normal operation after
   * a grid event
   *
   * @param hysteresisGradient Set default rate of change (ramp rate %/s) for the EV to return to
   *     normal operation after a grid event
   */
  public void setHysteresisGradient(@Nullable Double hysteresisGradient) {
    this.hysteresisGradient = hysteresisGradient;
  }

  /**
   * Adds set default rate of change (ramp rate %/s) for the EV to return to normal operation after
   * a grid event
   *
   * @param hysteresisGradient Set default rate of change (ramp rate %/s) for the EV to return to
   *     normal operation after a grid event
   * @return this
   */
  public Hysteresis withHysteresisGradient(@Nullable Double hysteresisGradient) {
    setHysteresisGradient(hysteresisGradient);
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
  public Hysteresis withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hysteresis that = (Hysteresis) o;
    return Objects.equals(hysteresisHigh, that.hysteresisHigh)
        && Objects.equals(hysteresisLow, that.hysteresisLow)
        && Objects.equals(hysteresisDelay, that.hysteresisDelay)
        && Objects.equals(hysteresisGradient, that.hysteresisGradient)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        hysteresisHigh, hysteresisLow, hysteresisDelay, hysteresisGradient, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("hysteresisHigh", hysteresisHigh)
        .add("hysteresisLow", hysteresisLow)
        .add("hysteresisDelay", hysteresisDelay)
        .add("hysteresisGradient", hysteresisGradient)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
