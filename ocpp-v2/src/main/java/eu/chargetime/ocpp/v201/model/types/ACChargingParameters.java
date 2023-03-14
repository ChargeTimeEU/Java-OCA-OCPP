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
 * AC Charging Parameters
 *
 * <p>EV AC charging parameters.
 */
public final class ACChargingParameters {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * AC Charging Parameters. Energy Amount. Energy Amount
   *
   * <p>Amount of energy requested (in Wh). This includes energy required for preconditioning.
   */
  private Integer energyAmount;

  /**
   * AC Charging Parameters. EV Min. Current
   *
   * <p>Minimum current (amps) supported by the electric vehicle (per phase).
   */
  private Integer evMinCurrent;

  /**
   * AC Charging Parameters. EV Max. Current
   *
   * <p>Maximum current (amps) supported by the electric vehicle (per phase). Includes cable
   * capacity.
   */
  private Integer evMaxCurrent;

  /**
   * AC Charging Parameters. EV Max. Voltage
   *
   * <p>Maximum voltage supported by the electric vehicle
   */
  private Integer evMaxVoltage;

  /**
   * Constructor for the ACChargingParameters class
   *
   * @param energyAmount Amount of energy requested (in Wh). This includes energy required for
   *     preconditioning.
   * @param evMinCurrent Minimum current (amps) supported by the electric vehicle (per phase).
   * @param evMaxCurrent Maximum current (amps) supported by the electric vehicle (per phase).
   *     Includes cable capacity.
   * @param evMaxVoltage Maximum voltage supported by the electric vehicle
   */
  public ACChargingParameters(
      Integer energyAmount, Integer evMinCurrent, Integer evMaxCurrent, Integer evMaxVoltage) {
    setEnergyAmount(energyAmount);
    setEvMinCurrent(evMinCurrent);
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
  public ACChargingParameters withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets amount of energy requested (in Wh). This includes energy required for preconditioning.
   *
   * @return Amount of energy requested (in Wh)
   */
  public Integer getEnergyAmount() {
    return energyAmount;
  }

  /**
   * Sets amount of energy requested (in Wh). This includes energy required for preconditioning.
   *
   * @param energyAmount Amount of energy requested (in Wh)
   */
  public void setEnergyAmount(Integer energyAmount) {
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
  private boolean isValidEnergyAmount(Integer energyAmount) {
    return energyAmount != null;
  }

  /**
   * Gets minimum current (amps) supported by the electric vehicle (per phase).
   *
   * @return Minimum current (amps) supported by the electric vehicle (per phase)
   */
  public Integer getEvMinCurrent() {
    return evMinCurrent;
  }

  /**
   * Sets minimum current (amps) supported by the electric vehicle (per phase).
   *
   * @param evMinCurrent Minimum current (amps) supported by the electric vehicle (per phase)
   */
  public void setEvMinCurrent(Integer evMinCurrent) {
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
  private boolean isValidEvMinCurrent(Integer evMinCurrent) {
    return evMinCurrent != null;
  }

  /**
   * Gets maximum current (amps) supported by the electric vehicle (per phase). Includes cable
   * capacity.
   *
   * @return Maximum current (amps) supported by the electric vehicle (per phase)
   */
  public Integer getEvMaxCurrent() {
    return evMaxCurrent;
  }

  /**
   * Sets maximum current (amps) supported by the electric vehicle (per phase). Includes cable
   * capacity.
   *
   * @param evMaxCurrent Maximum current (amps) supported by the electric vehicle (per phase)
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

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidEnergyAmount(energyAmount)
        && isValidEvMinCurrent(evMinCurrent)
        && isValidEvMaxCurrent(evMaxCurrent)
        && isValidEvMaxVoltage(evMaxVoltage);
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
    return Objects.equals(customData, that.customData)
        && Objects.equals(energyAmount, that.energyAmount)
        && Objects.equals(evMinCurrent, that.evMinCurrent)
        && Objects.equals(evMaxCurrent, that.evMaxCurrent)
        && Objects.equals(evMaxVoltage, that.evMaxVoltage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, energyAmount, evMinCurrent, evMaxCurrent, evMaxVoltage);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("energyAmount", energyAmount)
        .add("evMinCurrent", evMinCurrent)
        .add("evMaxCurrent", evMaxCurrent)
        .add("evMaxVoltage", evMaxVoltage)
        .add("isValid", validate())
        .toString();
  }
}
