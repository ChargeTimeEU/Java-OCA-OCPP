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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * DC Charging Parameters
 *
 * <p>EV DC charging parameters
 */
public final class DCChargingParameters {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * DC Charging Parameters. EV Max. Current
   *
   * <p>Maximum current (amps) supported by the electric vehicle. Includes cable capacity.
   */
  private Integer evMaxCurrent;

  /**
   * DC Charging Parameters. EV Max. Voltage
   *
   * <p>Maximum voltage supported by the electric vehicle
   */
  private Integer evMaxVoltage;

  /**
   * DC Charging Parameters. Energy Amount. Energy Amount
   *
   * <p>Amount of energy requested (in Wh). This inludes energy required for preconditioning.
   */
  @Nullable private Integer energyAmount;

  /**
   * DC Charging Parameters. EV Max. Power
   *
   * <p>Maximum power (in W) supported by the electric vehicle. Required for DC charging.
   */
  @Nullable private Integer evMaxPower;

  /**
   * DC Charging Parameters. State Of Charge. Numeric
   *
   * <p>Energy available in the battery (in percent of the battery capacity)
   */
  @Nullable private Integer stateOfCharge;

  /**
   * DC Charging Parameters. EV Energy Capacity. Numeric
   *
   * <p>Capacity of the electric vehicle battery (in Wh)
   */
  @Nullable private Integer evEnergyCapacity;

  /**
   * DC Charging Parameters. Full SOC. Percentage
   *
   * <p>Percentage of SoC at which the EV considers the battery fully charged. (possible values: 0 -
   * 100)
   */
  @Nullable private Integer fullSoC;

  /**
   * DC Charging Parameters. Bulk SOC. Percentage
   *
   * <p>Percentage of SoC at which the EV considers a fast charging process to end. (possible
   * values: 0 - 100)
   */
  @Nullable private Integer bulkSoC;

  /**
   * Constructor for the DCChargingParameters class
   *
   * @param evMaxCurrent Maximum current (amps) supported by the electric vehicle. Includes cable
   *     capacity.
   * @param evMaxVoltage Maximum voltage supported by the electric vehicle
   */
  public DCChargingParameters(Integer evMaxCurrent, Integer evMaxVoltage) {
    setEvMaxCurrent(evMaxCurrent);
    setEvMaxVoltage(evMaxVoltage);
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
  public DCChargingParameters withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets maximum current (amps) supported by the electric vehicle. Includes cable capacity.
   *
   * @return Maximum current (amps) supported by the electric vehicle
   */
  public Integer getEvMaxCurrent() {
    return evMaxCurrent;
  }

  /**
   * Sets maximum current (amps) supported by the electric vehicle. Includes cable capacity.
   *
   * @param evMaxCurrent Maximum current (amps) supported by the electric vehicle
   */
  public void setEvMaxCurrent(Integer evMaxCurrent) {
    if (!isValidEvMaxCurrent(evMaxCurrent)) {
      throw new PropertyConstraintException(evMaxCurrent, "evMaxCurrent is invalid");
    }
    this.evMaxCurrent = evMaxCurrent;
  }

  /**
   * Returns whether the given evMaxCurrent is valid
   *
   * @param evMaxCurrent the evMaxCurrent to check the validity of
   * @return {@code true} if evMaxCurrent is valid, {@code false} if not
   */
  private boolean isValidEvMaxCurrent(Integer evMaxCurrent) {
    return evMaxCurrent != null;
  }

  /**
   * Gets maximum voltage supported by the electric vehicle
   *
   * @return Maximum voltage supported by the electric vehicle
   */
  public Integer getEvMaxVoltage() {
    return evMaxVoltage;
  }

  /**
   * Sets maximum voltage supported by the electric vehicle
   *
   * @param evMaxVoltage Maximum voltage supported by the electric vehicle
   */
  public void setEvMaxVoltage(Integer evMaxVoltage) {
    if (!isValidEvMaxVoltage(evMaxVoltage)) {
      throw new PropertyConstraintException(evMaxVoltage, "evMaxVoltage is invalid");
    }
    this.evMaxVoltage = evMaxVoltage;
  }

  /**
   * Returns whether the given evMaxVoltage is valid
   *
   * @param evMaxVoltage the evMaxVoltage to check the validity of
   * @return {@code true} if evMaxVoltage is valid, {@code false} if not
   */
  private boolean isValidEvMaxVoltage(Integer evMaxVoltage) {
    return evMaxVoltage != null;
  }

  /**
   * Gets amount of energy requested (in Wh). This inludes energy required for preconditioning.
   *
   * @return Amount of energy requested (in Wh)
   */
  @Nullable
  public Integer getEnergyAmount() {
    return energyAmount;
  }

  /**
   * Sets amount of energy requested (in Wh). This inludes energy required for preconditioning.
   *
   * @param energyAmount Amount of energy requested (in Wh)
   */
  public void setEnergyAmount(@Nullable Integer energyAmount) {
    this.energyAmount = energyAmount;
  }

  /**
   * Adds amount of energy requested (in Wh). This inludes energy required for preconditioning.
   *
   * @param energyAmount Amount of energy requested (in Wh)
   * @return this
   */
  public DCChargingParameters withEnergyAmount(@Nullable Integer energyAmount) {
    setEnergyAmount(energyAmount);
    return this;
  }

  /**
   * Gets maximum power (in W) supported by the electric vehicle. Required for DC charging.
   *
   * @return Maximum power (in W) supported by the electric vehicle
   */
  @Nullable
  public Integer getEvMaxPower() {
    return evMaxPower;
  }

  /**
   * Sets maximum power (in W) supported by the electric vehicle. Required for DC charging.
   *
   * @param evMaxPower Maximum power (in W) supported by the electric vehicle
   */
  public void setEvMaxPower(@Nullable Integer evMaxPower) {
    this.evMaxPower = evMaxPower;
  }

  /**
   * Adds maximum power (in W) supported by the electric vehicle. Required for DC charging.
   *
   * @param evMaxPower Maximum power (in W) supported by the electric vehicle
   * @return this
   */
  public DCChargingParameters withEvMaxPower(@Nullable Integer evMaxPower) {
    setEvMaxPower(evMaxPower);
    return this;
  }

  /**
   * Gets energy available in the battery (in percent of the battery capacity)
   *
   * @return Energy available in the battery (in percent of the battery capacity)
   */
  @Nullable
  public Integer getStateOfCharge() {
    return stateOfCharge;
  }

  /**
   * Sets energy available in the battery (in percent of the battery capacity)
   *
   * @param stateOfCharge Energy available in the battery (in percent of the battery capacity)
   */
  public void setStateOfCharge(@Nullable Integer stateOfCharge) {
    if (!isValidStateOfCharge(stateOfCharge)) {
      throw new PropertyConstraintException(stateOfCharge, "stateOfCharge is invalid");
    }
    this.stateOfCharge = stateOfCharge;
  }

  /**
   * Returns whether the given stateOfCharge is valid
   *
   * @param stateOfCharge the stateOfCharge to check the validity of
   * @return {@code true} if stateOfCharge is valid, {@code false} if not
   */
  private boolean isValidStateOfCharge(@Nullable Integer stateOfCharge) {
    return stateOfCharge == null || (stateOfCharge >= 0 && stateOfCharge <= 100);
  }

  /**
   * Adds energy available in the battery (in percent of the battery capacity)
   *
   * @param stateOfCharge Energy available in the battery (in percent of the battery capacity)
   * @return this
   */
  public DCChargingParameters withStateOfCharge(@Nullable Integer stateOfCharge) {
    setStateOfCharge(stateOfCharge);
    return this;
  }

  /**
   * Gets capacity of the electric vehicle battery (in Wh)
   *
   * @return Capacity of the electric vehicle battery (in Wh)
   */
  @Nullable
  public Integer getEvEnergyCapacity() {
    return evEnergyCapacity;
  }

  /**
   * Sets capacity of the electric vehicle battery (in Wh)
   *
   * @param evEnergyCapacity Capacity of the electric vehicle battery (in Wh)
   */
  public void setEvEnergyCapacity(@Nullable Integer evEnergyCapacity) {
    this.evEnergyCapacity = evEnergyCapacity;
  }

  /**
   * Adds capacity of the electric vehicle battery (in Wh)
   *
   * @param evEnergyCapacity Capacity of the electric vehicle battery (in Wh)
   * @return this
   */
  public DCChargingParameters withEvEnergyCapacity(@Nullable Integer evEnergyCapacity) {
    setEvEnergyCapacity(evEnergyCapacity);
    return this;
  }

  /**
   * Gets percentage of SoC at which the EV considers the battery fully charged. (possible values: 0
   * - 100)
   *
   * @return Percentage of SoC at which the EV considers the battery fully charged
   */
  @Nullable
  public Integer getFullSoC() {
    return fullSoC;
  }

  /**
   * Sets percentage of SoC at which the EV considers the battery fully charged. (possible values: 0
   * - 100)
   *
   * @param fullSoC Percentage of SoC at which the EV considers the battery fully charged
   */
  public void setFullSoC(@Nullable Integer fullSoC) {
    if (!isValidFullSoC(fullSoC)) {
      throw new PropertyConstraintException(fullSoC, "fullSoC is invalid");
    }
    this.fullSoC = fullSoC;
  }

  /**
   * Returns whether the given fullSoC is valid
   *
   * @param fullSoC the fullSoC to check the validity of
   * @return {@code true} if fullSoC is valid, {@code false} if not
   */
  private boolean isValidFullSoC(@Nullable Integer fullSoC) {
    return fullSoC == null || (fullSoC >= 0 && fullSoC <= 100);
  }

  /**
   * Adds percentage of SoC at which the EV considers the battery fully charged. (possible values: 0
   * - 100)
   *
   * @param fullSoC Percentage of SoC at which the EV considers the battery fully charged
   * @return this
   */
  public DCChargingParameters withFullSoC(@Nullable Integer fullSoC) {
    setFullSoC(fullSoC);
    return this;
  }

  /**
   * Gets percentage of SoC at which the EV considers a fast charging process to end. (possible
   * values: 0 - 100)
   *
   * @return Percentage of SoC at which the EV considers a fast charging process to end
   */
  @Nullable
  public Integer getBulkSoC() {
    return bulkSoC;
  }

  /**
   * Sets percentage of SoC at which the EV considers a fast charging process to end. (possible
   * values: 0 - 100)
   *
   * @param bulkSoC Percentage of SoC at which the EV considers a fast charging process to end
   */
  public void setBulkSoC(@Nullable Integer bulkSoC) {
    if (!isValidBulkSoC(bulkSoC)) {
      throw new PropertyConstraintException(bulkSoC, "bulkSoC is invalid");
    }
    this.bulkSoC = bulkSoC;
  }

  /**
   * Returns whether the given bulkSoC is valid
   *
   * @param bulkSoC the bulkSoC to check the validity of
   * @return {@code true} if bulkSoC is valid, {@code false} if not
   */
  private boolean isValidBulkSoC(@Nullable Integer bulkSoC) {
    return bulkSoC == null || (bulkSoC >= 0 && bulkSoC <= 100);
  }

  /**
   * Adds percentage of SoC at which the EV considers a fast charging process to end. (possible
   * values: 0 - 100)
   *
   * @param bulkSoC Percentage of SoC at which the EV considers a fast charging process to end
   * @return this
   */
  public DCChargingParameters withBulkSoC(@Nullable Integer bulkSoC) {
    setBulkSoC(bulkSoC);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidEvMaxCurrent(evMaxCurrent)
        && isValidEvMaxVoltage(evMaxVoltage)
        && isValidStateOfCharge(stateOfCharge)
        && isValidFullSoC(fullSoC)
        && isValidBulkSoC(bulkSoC);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DCChargingParameters that = (DCChargingParameters) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(evMaxCurrent, that.evMaxCurrent)
        && Objects.equals(evMaxVoltage, that.evMaxVoltage)
        && Objects.equals(energyAmount, that.energyAmount)
        && Objects.equals(evMaxPower, that.evMaxPower)
        && Objects.equals(stateOfCharge, that.stateOfCharge)
        && Objects.equals(evEnergyCapacity, that.evEnergyCapacity)
        && Objects.equals(fullSoC, that.fullSoC)
        && Objects.equals(bulkSoC, that.bulkSoC);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        evMaxCurrent,
        evMaxVoltage,
        energyAmount,
        evMaxPower,
        stateOfCharge,
        evEnergyCapacity,
        fullSoC,
        bulkSoC);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("evMaxCurrent", evMaxCurrent)
        .add("evMaxVoltage", evMaxVoltage)
        .add("energyAmount", energyAmount)
        .add("evMaxPower", evMaxPower)
        .add("stateOfCharge", stateOfCharge)
        .add("evEnergyCapacity", evEnergyCapacity)
        .add("fullSoC", fullSoC)
        .add("bulkSoC", bulkSoC)
        .add("isValid", validate())
        .toString();
  }
}
