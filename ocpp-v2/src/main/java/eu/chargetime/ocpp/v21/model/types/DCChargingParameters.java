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

/** EV DC charging parameters for ISO 15118-2 */
public final class DCChargingParameters {
  /**
   * Maximum current (in A) supported by the electric vehicle. Includes cable capacity.
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: DCEVChargeParameterType:EVMaximumCurrentLimit
   * </pre>
   */
  private Double evMaxCurrent;

  /**
   * Maximum voltage supported by the electric vehicle.
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: DCEVChargeParameterType: EVMaximumVoltageLimit
   * </pre>
   */
  private Double evMaxVoltage;

  /**
   * Maximum power (in W) supported by the electric vehicle. Required for DC charging.
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: DCEVChargeParameterType: EVMaximumPowerLimit
   * </pre>
   */
  @Nullable private Double evMaxPower;

  /**
   * Capacity of the electric vehicle battery (in Wh).
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: DCEVChargeParameterType: EVEnergyCapacity
   * </pre>
   */
  @Nullable private Double evEnergyCapacity;

  /**
   * Amount of energy requested (in Wh). This inludes energy required for preconditioning.
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: DCEVChargeParameterType: EVEnergyRequest
   * </pre>
   */
  @Nullable private Double energyAmount;

  /**
   * Energy available in the battery (in percent of the battery capacity)
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: DCEVChargeParameterType: DCEVStatus: EVRESSSOC
   * </pre>
   */
  @Nullable private Integer stateOfCharge;

  /**
   * Percentage of SoC at which the EV considers the battery fully charged. (possible values: 0 -
   * 100)
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: DCEVChargeParameterType: FullSOC
   * </pre>
   */
  @Nullable private Integer fullSoC;

  /**
   * Percentage of SoC at which the EV considers a fast charging process to end. (possible values: 0
   * - 100)
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: DCEVChargeParameterType: BulkSOC
   * </pre>
   */
  @Nullable private Integer bulkSoC;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the DCChargingParameters class
   *
   * @param evMaxCurrent Maximum current (in A) supported by the electric vehicle. Includes cable
   *     capacity.
   * @param evMaxVoltage Maximum voltage supported by the electric vehicle.
   */
  public DCChargingParameters(Double evMaxCurrent, Double evMaxVoltage) {
    setEvMaxCurrent(evMaxCurrent);
    setEvMaxVoltage(evMaxVoltage);
  }

  /**
   * Gets maximum current (in A) supported by the electric vehicle. Includes cable capacity.
   *
   * @return Maximum current (in A) supported by the electric vehicle
   */
  public Double getEvMaxCurrent() {
    return evMaxCurrent;
  }

  /**
   * Sets maximum current (in A) supported by the electric vehicle. Includes cable capacity.
   *
   * @param evMaxCurrent Maximum current (in A) supported by the electric vehicle
   */
  public void setEvMaxCurrent(Double evMaxCurrent) {
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
  private boolean isValidEvMaxCurrent(Double evMaxCurrent) {
    return evMaxCurrent != null;
  }

  /**
   * Gets maximum voltage supported by the electric vehicle.
   *
   * @return Maximum voltage supported by the electric vehicle
   */
  public Double getEvMaxVoltage() {
    return evMaxVoltage;
  }

  /**
   * Sets maximum voltage supported by the electric vehicle.
   *
   * @param evMaxVoltage Maximum voltage supported by the electric vehicle
   */
  public void setEvMaxVoltage(Double evMaxVoltage) {
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
  private boolean isValidEvMaxVoltage(Double evMaxVoltage) {
    return evMaxVoltage != null;
  }

  /**
   * Gets maximum power (in W) supported by the electric vehicle. Required for DC charging.
   *
   * @return Maximum power (in W) supported by the electric vehicle
   */
  @Nullable
  public Double getEvMaxPower() {
    return evMaxPower;
  }

  /**
   * Sets maximum power (in W) supported by the electric vehicle. Required for DC charging.
   *
   * @param evMaxPower Maximum power (in W) supported by the electric vehicle
   */
  public void setEvMaxPower(@Nullable Double evMaxPower) {
    this.evMaxPower = evMaxPower;
  }

  /**
   * Adds maximum power (in W) supported by the electric vehicle. Required for DC charging.
   *
   * @param evMaxPower Maximum power (in W) supported by the electric vehicle
   * @return this
   */
  public DCChargingParameters withEvMaxPower(@Nullable Double evMaxPower) {
    setEvMaxPower(evMaxPower);
    return this;
  }

  /**
   * Gets capacity of the electric vehicle battery (in Wh).
   *
   * @return Capacity of the electric vehicle battery (in Wh)
   */
  @Nullable
  public Double getEvEnergyCapacity() {
    return evEnergyCapacity;
  }

  /**
   * Sets capacity of the electric vehicle battery (in Wh).
   *
   * @param evEnergyCapacity Capacity of the electric vehicle battery (in Wh)
   */
  public void setEvEnergyCapacity(@Nullable Double evEnergyCapacity) {
    this.evEnergyCapacity = evEnergyCapacity;
  }

  /**
   * Adds capacity of the electric vehicle battery (in Wh).
   *
   * @param evEnergyCapacity Capacity of the electric vehicle battery (in Wh)
   * @return this
   */
  public DCChargingParameters withEvEnergyCapacity(@Nullable Double evEnergyCapacity) {
    setEvEnergyCapacity(evEnergyCapacity);
    return this;
  }

  /**
   * Gets amount of energy requested (in Wh). This inludes energy required for preconditioning.
   *
   * @return Amount of energy requested (in Wh)
   */
  @Nullable
  public Double getEnergyAmount() {
    return energyAmount;
  }

  /**
   * Sets amount of energy requested (in Wh). This inludes energy required for preconditioning.
   *
   * @param energyAmount Amount of energy requested (in Wh)
   */
  public void setEnergyAmount(@Nullable Double energyAmount) {
    this.energyAmount = energyAmount;
  }

  /**
   * Adds amount of energy requested (in Wh). This inludes energy required for preconditioning.
   *
   * @param energyAmount Amount of energy requested (in Wh)
   * @return this
   */
  public DCChargingParameters withEnergyAmount(@Nullable Double energyAmount) {
    setEnergyAmount(energyAmount);
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

  public boolean validate() {
    return isValidEvMaxCurrent(evMaxCurrent)
        && isValidEvMaxVoltage(evMaxVoltage)
        && isValidStateOfCharge(stateOfCharge)
        && isValidFullSoC(fullSoC)
        && isValidBulkSoC(bulkSoC)
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
    DCChargingParameters that = (DCChargingParameters) o;
    return Objects.equals(evMaxCurrent, that.evMaxCurrent)
        && Objects.equals(evMaxVoltage, that.evMaxVoltage)
        && Objects.equals(evMaxPower, that.evMaxPower)
        && Objects.equals(evEnergyCapacity, that.evEnergyCapacity)
        && Objects.equals(energyAmount, that.energyAmount)
        && Objects.equals(stateOfCharge, that.stateOfCharge)
        && Objects.equals(fullSoC, that.fullSoC)
        && Objects.equals(bulkSoC, that.bulkSoC)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        evMaxCurrent,
        evMaxVoltage,
        evMaxPower,
        evEnergyCapacity,
        energyAmount,
        stateOfCharge,
        fullSoC,
        bulkSoC,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evMaxCurrent", evMaxCurrent)
        .add("evMaxVoltage", evMaxVoltage)
        .add("evMaxPower", evMaxPower)
        .add("evEnergyCapacity", evEnergyCapacity)
        .add("energyAmount", energyAmount)
        .add("stateOfCharge", stateOfCharge)
        .add("fullSoC", fullSoC)
        .add("bulkSoC", bulkSoC)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
