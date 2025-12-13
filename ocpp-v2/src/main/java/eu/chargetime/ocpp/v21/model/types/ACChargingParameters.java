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

/** EV AC charging parameters for ISO 15118-2 */
public final class ACChargingParameters {
  /**
   * Amount of energy requested (in Wh). This includes energy required for preconditioning.
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: ACEVChargeParameterType: EAmount
   * *ISO 15118-20*: Dynamic/ScheduledSEReqControlModeType: EVTargetEnergyRequest
   * </pre>
   */
  private Double energyAmount;

  /**
   * Minimum current (amps) supported by the electric vehicle (per phase).
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: ACEVChargeParameterType: EVMinCurrent
   * </pre>
   */
  private Double evMinCurrent;

  /**
   * Maximum current (amps) supported by the electric vehicle (per phase). Includes cable capacity.
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: ACEVChargeParameterType: EVMaxCurrent
   * </pre>
   */
  private Double evMaxCurrent;

  /**
   * Maximum voltage supported by the electric vehicle.
   *
   * <pre>
   * Relates to:
   * *ISO 15118-2*: ACEVChargeParameterType: EVMaxVoltage
   * </pre>
   */
  private Double evMaxVoltage;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ACChargingParameters class
   *
   * @param energyAmount Amount of energy requested (in Wh). This includes energy required for
   *     preconditioning.
   * @param evMinCurrent Minimum current (amps) supported by the electric vehicle (per phase).
   * @param evMaxCurrent Maximum current (amps) supported by the electric vehicle (per phase).
   *     Includes cable capacity.
   * @param evMaxVoltage Maximum voltage supported by the electric vehicle.
   */
  public ACChargingParameters(
      Double energyAmount, Double evMinCurrent, Double evMaxCurrent, Double evMaxVoltage) {
    setEnergyAmount(energyAmount);
    setEvMinCurrent(evMinCurrent);
    setEvMaxCurrent(evMaxCurrent);
    setEvMaxVoltage(evMaxVoltage);
  }

  /**
   * Gets amount of energy requested (in Wh). This includes energy required for preconditioning.
   *
   * @return Amount of energy requested (in Wh)
   */
  public Double getEnergyAmount() {
    return energyAmount;
  }

  /**
   * Sets amount of energy requested (in Wh). This includes energy required for preconditioning.
   *
   * @param energyAmount Amount of energy requested (in Wh)
   */
  public void setEnergyAmount(Double energyAmount) {
    if (!isValidEnergyAmount(energyAmount)) {
      throw new PropertyConstraintException(energyAmount, "energyAmount is invalid");
    }
    this.energyAmount = energyAmount;
  }

  /**
   * Returns whether the given energyAmount is valid
   *
   * @param energyAmount the energyAmount to check the validity of
   * @return {@code true} if energyAmount is valid, {@code false} if not
   */
  private boolean isValidEnergyAmount(Double energyAmount) {
    return energyAmount != null;
  }

  /**
   * Gets minimum current (amps) supported by the electric vehicle (per phase).
   *
   * @return Minimum current (amps) supported by the electric vehicle (per phase)
   */
  public Double getEvMinCurrent() {
    return evMinCurrent;
  }

  /**
   * Sets minimum current (amps) supported by the electric vehicle (per phase).
   *
   * @param evMinCurrent Minimum current (amps) supported by the electric vehicle (per phase)
   */
  public void setEvMinCurrent(Double evMinCurrent) {
    if (!isValidEvMinCurrent(evMinCurrent)) {
      throw new PropertyConstraintException(evMinCurrent, "evMinCurrent is invalid");
    }
    this.evMinCurrent = evMinCurrent;
  }

  /**
   * Returns whether the given evMinCurrent is valid
   *
   * @param evMinCurrent the evMinCurrent to check the validity of
   * @return {@code true} if evMinCurrent is valid, {@code false} if not
   */
  private boolean isValidEvMinCurrent(Double evMinCurrent) {
    return evMinCurrent != null;
  }

  /**
   * Gets maximum current (amps) supported by the electric vehicle (per phase). Includes cable
   * capacity.
   *
   * @return Maximum current (amps) supported by the electric vehicle (per phase)
   */
  public Double getEvMaxCurrent() {
    return evMaxCurrent;
  }

  /**
   * Sets maximum current (amps) supported by the electric vehicle (per phase). Includes cable
   * capacity.
   *
   * @param evMaxCurrent Maximum current (amps) supported by the electric vehicle (per phase)
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
  public ACChargingParameters withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidEnergyAmount(energyAmount)
        && isValidEvMinCurrent(evMinCurrent)
        && isValidEvMaxCurrent(evMaxCurrent)
        && isValidEvMaxVoltage(evMaxVoltage)
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
    ACChargingParameters that = (ACChargingParameters) o;
    return Objects.equals(energyAmount, that.energyAmount)
        && Objects.equals(evMinCurrent, that.evMinCurrent)
        && Objects.equals(evMaxCurrent, that.evMaxCurrent)
        && Objects.equals(evMaxVoltage, that.evMaxVoltage)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(energyAmount, evMinCurrent, evMaxCurrent, evMaxVoltage, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("energyAmount", energyAmount)
        .add("evMinCurrent", evMinCurrent)
        .add("evMaxCurrent", evMaxCurrent)
        .add("evMaxVoltage", evMaxVoltage)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
