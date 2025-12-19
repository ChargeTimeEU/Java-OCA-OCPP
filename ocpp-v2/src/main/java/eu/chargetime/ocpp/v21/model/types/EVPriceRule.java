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

/** An entry in price schedule over time for which EV is willing to discharge. */
public final class EVPriceRule {
  /** Cost per kWh. */
  private Double energyFee;

  /**
   * The EnergyFee applies between this value and the value of the PowerRangeStart of the subsequent
   * EVPriceRule. If the power is below this value, the EnergyFee of the previous EVPriceRule
   * applies. Negative values are used for discharging.
   */
  private Double powerRangeStart;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the EVPriceRule class
   *
   * @param energyFee Cost per kWh.
   * @param powerRangeStart The EnergyFee applies between this value and the value of the
   *     PowerRangeStart of the subsequent EVPriceRule. If the power is below this value, the
   *     EnergyFee of the previous EVPriceRule applies. Negative values are used for discharging.
   */
  public EVPriceRule(Double energyFee, Double powerRangeStart) {
    setEnergyFee(energyFee);
    setPowerRangeStart(powerRangeStart);
  }

  /**
   * Gets cost per kWh.
   *
   * @return Cost per kWh
   */
  public Double getEnergyFee() {
    return energyFee;
  }

  /**
   * Sets cost per kWh.
   *
   * @param energyFee Cost per kWh
   */
  public void setEnergyFee(Double energyFee) {
    if (!isValidEnergyFee(energyFee)) {
      throw new PropertyConstraintException(energyFee, "energyFee is invalid");
    }
    this.energyFee = energyFee;
  }

  /**
   * Returns whether the given energyFee is valid
   *
   * @param energyFee the energyFee to check the validity of
   * @return {@code true} if energyFee is valid, {@code false} if not
   */
  private boolean isValidEnergyFee(Double energyFee) {
    return energyFee != null;
  }

  /**
   * Gets the EnergyFee applies between this value and the value of the PowerRangeStart of the
   * subsequent EVPriceRule. If the power is below this value, the EnergyFee of the previous
   * EVPriceRule applies. Negative values are used for discharging.
   *
   * @return The EnergyFee applies between this value and the value of the PowerRangeStart of the
   *     subsequent EVPriceRule
   */
  public Double getPowerRangeStart() {
    return powerRangeStart;
  }

  /**
   * Sets the EnergyFee applies between this value and the value of the PowerRangeStart of the
   * subsequent EVPriceRule. If the power is below this value, the EnergyFee of the previous
   * EVPriceRule applies. Negative values are used for discharging.
   *
   * @param powerRangeStart The EnergyFee applies between this value and the value of the
   *     PowerRangeStart of the subsequent EVPriceRule
   */
  public void setPowerRangeStart(Double powerRangeStart) {
    if (!isValidPowerRangeStart(powerRangeStart)) {
      throw new PropertyConstraintException(powerRangeStart, "powerRangeStart is invalid");
    }
    this.powerRangeStart = powerRangeStart;
  }

  /**
   * Returns whether the given powerRangeStart is valid
   *
   * @param powerRangeStart the powerRangeStart to check the validity of
   * @return {@code true} if powerRangeStart is valid, {@code false} if not
   */
  private boolean isValidPowerRangeStart(Double powerRangeStart) {
    return powerRangeStart != null;
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
  public EVPriceRule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidEnergyFee(energyFee)
        && isValidPowerRangeStart(powerRangeStart)
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
    EVPriceRule that = (EVPriceRule) o;
    return Objects.equals(energyFee, that.energyFee)
        && Objects.equals(powerRangeStart, that.powerRangeStart)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(energyFee, powerRangeStart, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("energyFee", energyFee)
        .add("powerRangeStart", powerRangeStart)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
