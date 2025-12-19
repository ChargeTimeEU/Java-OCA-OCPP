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

/** VoltageParamsType */
public final class VoltageParams {
  /**
   * EN 50549-1 chapter 4.9.3.4 Voltage threshold for the 10 min time window mean value monitoring.
   * The 10 min mean is recalculated up to every 3 s. If the present voltage is above this threshold
   * for more than the time defined by hv10MinMeanValue, the EV must trip. This value is mandatory
   * if hv10MinMeanTripDelay is set.
   */
  @Nullable private Double hv10MinMeanValue;

  /**
   * Time for which the voltage is allowed to stay above the 10 min mean value. After this time, the
   * EV must trip. This value is mandatory if OverVoltageMeanValue10min is set.
   */
  @Nullable private Double hv10MinMeanTripDelay;

  /**
   * Parameter is only sent, if the EV has to feed-in power or reactive power during fault-ride
   * through (FRT) as defined by HVMomCess curve and LVMomCess curve.
   */
  @Nullable private PowerDuringCessationEnum powerDuringCessation;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the VoltageParams class */
  public VoltageParams() {}

  /**
   * Gets EN 50549-1 chapter 4.9.3.4 Voltage threshold for the 10 min time window mean value
   * monitoring. The 10 min mean is recalculated up to every 3 s. If the present voltage is above
   * this threshold for more than the time defined by hv10MinMeanValue, the EV must trip. This value
   * is mandatory if hv10MinMeanTripDelay is set.
   *
   * @return EN 50549-1 chapter 4.9.3.4 Voltage threshold for the 10 min time window mean value
   *     monitoring
   */
  @Nullable
  public Double getHv10MinMeanValue() {
    return hv10MinMeanValue;
  }

  /**
   * Sets EN 50549-1 chapter 4.9.3.4 Voltage threshold for the 10 min time window mean value
   * monitoring. The 10 min mean is recalculated up to every 3 s. If the present voltage is above
   * this threshold for more than the time defined by hv10MinMeanValue, the EV must trip. This value
   * is mandatory if hv10MinMeanTripDelay is set.
   *
   * @param hv10MinMeanValue EN 50549-1 chapter 4.9.3.4 Voltage threshold for the 10 min time window
   *     mean value monitoring
   */
  public void setHv10MinMeanValue(@Nullable Double hv10MinMeanValue) {
    this.hv10MinMeanValue = hv10MinMeanValue;
  }

  /**
   * Adds EN 50549-1 chapter 4.9.3.4 Voltage threshold for the 10 min time window mean value
   * monitoring. The 10 min mean is recalculated up to every 3 s. If the present voltage is above
   * this threshold for more than the time defined by hv10MinMeanValue, the EV must trip. This value
   * is mandatory if hv10MinMeanTripDelay is set.
   *
   * @param hv10MinMeanValue EN 50549-1 chapter 4.9.3.4 Voltage threshold for the 10 min time window
   *     mean value monitoring
   * @return this
   */
  public VoltageParams withHv10MinMeanValue(@Nullable Double hv10MinMeanValue) {
    setHv10MinMeanValue(hv10MinMeanValue);
    return this;
  }

  /**
   * Gets time for which the voltage is allowed to stay above the 10 min mean value. After this
   * time, the EV must trip. This value is mandatory if OverVoltageMeanValue10min is set.
   *
   * @return Time for which the voltage is allowed to stay above the 10 min mean value
   */
  @Nullable
  public Double getHv10MinMeanTripDelay() {
    return hv10MinMeanTripDelay;
  }

  /**
   * Sets time for which the voltage is allowed to stay above the 10 min mean value. After this
   * time, the EV must trip. This value is mandatory if OverVoltageMeanValue10min is set.
   *
   * @param hv10MinMeanTripDelay Time for which the voltage is allowed to stay above the 10 min mean
   *     value
   */
  public void setHv10MinMeanTripDelay(@Nullable Double hv10MinMeanTripDelay) {
    this.hv10MinMeanTripDelay = hv10MinMeanTripDelay;
  }

  /**
   * Adds time for which the voltage is allowed to stay above the 10 min mean value. After this
   * time, the EV must trip. This value is mandatory if OverVoltageMeanValue10min is set.
   *
   * @param hv10MinMeanTripDelay Time for which the voltage is allowed to stay above the 10 min mean
   *     value
   * @return this
   */
  public VoltageParams withHv10MinMeanTripDelay(@Nullable Double hv10MinMeanTripDelay) {
    setHv10MinMeanTripDelay(hv10MinMeanTripDelay);
    return this;
  }

  /**
   * Gets parameter is only sent, if the EV has to feed-in power or reactive power during fault-ride
   * through (FRT) as defined by HVMomCess curve and LVMomCess curve.
   *
   * @return Parameter is only sent, if the EV has to feed-in power or reactive power during
   *     fault-ride through (FRT) as defined by HVMomCess curve and LVMomCess curve
   */
  @Nullable
  public PowerDuringCessationEnum getPowerDuringCessation() {
    return powerDuringCessation;
  }

  /**
   * Sets parameter is only sent, if the EV has to feed-in power or reactive power during fault-ride
   * through (FRT) as defined by HVMomCess curve and LVMomCess curve.
   *
   * @param powerDuringCessation Parameter is only sent, if the EV has to feed-in power or reactive
   *     power during fault-ride through (FRT) as defined by HVMomCess curve and LVMomCess curve
   */
  public void setPowerDuringCessation(@Nullable PowerDuringCessationEnum powerDuringCessation) {
    this.powerDuringCessation = powerDuringCessation;
  }

  /**
   * Adds parameter is only sent, if the EV has to feed-in power or reactive power during fault-ride
   * through (FRT) as defined by HVMomCess curve and LVMomCess curve.
   *
   * @param powerDuringCessation Parameter is only sent, if the EV has to feed-in power or reactive
   *     power during fault-ride through (FRT) as defined by HVMomCess curve and LVMomCess curve
   * @return this
   */
  public VoltageParams withPowerDuringCessation(
      @Nullable PowerDuringCessationEnum powerDuringCessation) {
    setPowerDuringCessation(powerDuringCessation);
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
  public VoltageParams withCustomData(@Nullable CustomData customData) {
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
    VoltageParams that = (VoltageParams) o;
    return Objects.equals(hv10MinMeanValue, that.hv10MinMeanValue)
        && Objects.equals(hv10MinMeanTripDelay, that.hv10MinMeanTripDelay)
        && Objects.equals(powerDuringCessation, that.powerDuringCessation)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hv10MinMeanValue, hv10MinMeanTripDelay, powerDuringCessation, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("hv10MinMeanValue", hv10MinMeanValue)
        .add("hv10MinMeanTripDelay", hv10MinMeanTripDelay)
        .add("powerDuringCessation", powerDuringCessation)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
