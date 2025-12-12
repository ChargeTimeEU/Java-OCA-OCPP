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

/** Tariff with optional conditions for an energy price. */
public final class TariffEnergyPrice {
  /** Price per kWh (excl. tax) for this element. */
  private Double priceKwh;

  /**
   * These conditions describe if and when a TariffEnergyType or TariffTimeType applies during a
   * transaction.
   *
   * <p>When more than one restriction is set, they are to be treated as a logical AND. All need to
   * be valid before this price is active.
   *
   * <p>For reverse energy flow (discharging) negative values of energy, power and current are used.
   *
   * <p>NOTE: minXXX (where XXX = Kwh/A/Kw) must be read as "closest to zero", and maxXXX as
   * "furthest from zero". For example, a *charging* power range from 10 kW to 50 kWh is given by
   * minPower = 10000 and maxPower = 50000, and a *discharging* power range from -10 kW to -50 kW is
   * given by minPower = -10 and maxPower = -50.
   *
   * <p>NOTE: startTimeOfDay and endTimeOfDay are in local time, because it is the time in the
   * tariff as it is shown to the EV driver at the Charging Station. A Charging Station will convert
   * this to the internal time zone that it uses (which is recommended to be UTC, see section
   * Generic chapter 3.1) when performing cost calculation.
   */
  @Nullable private TariffConditions conditions;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TariffEnergyPrice class
   *
   * @param priceKwh Price per kWh (excl. tax) for this element.
   */
  public TariffEnergyPrice(Double priceKwh) {
    setPriceKwh(priceKwh);
  }

  /**
   * Gets price per kWh (excl. tax) for this element.
   *
   * @return Price per kWh (excl
   */
  public Double getPriceKwh() {
    return priceKwh;
  }

  /**
   * Sets price per kWh (excl. tax) for this element.
   *
   * @param priceKwh Price per kWh (excl
   */
  public void setPriceKwh(Double priceKwh) {
    if (!isValidPriceKwh(priceKwh)) {
      throw new PropertyConstraintException(priceKwh, "priceKwh is invalid");
    }
    this.priceKwh = priceKwh;
  }

  /**
   * Returns whether the given priceKwh is valid
   *
   * @param priceKwh the priceKwh to check the validity of
   * @return {@code true} if priceKwh is valid, {@code false} if not
   */
  private boolean isValidPriceKwh(Double priceKwh) {
    return priceKwh != null;
  }

  /**
   * Gets these conditions describe if and when a TariffEnergyType or TariffTimeType applies during
   * a transaction.
   *
   * @return These conditions describe if and when a TariffEnergyType or TariffTimeType applies
   *     during a transaction
   */
  @Nullable
  public TariffConditions getConditions() {
    return conditions;
  }

  /**
   * Sets these conditions describe if and when a TariffEnergyType or TariffTimeType applies during
   * a transaction.
   *
   * @param conditions These conditions describe if and when a TariffEnergyType or TariffTimeType
   *     applies during a transaction
   */
  public void setConditions(@Nullable TariffConditions conditions) {
    if (!isValidConditions(conditions)) {
      throw new PropertyConstraintException(conditions, "conditions is invalid");
    }
    this.conditions = conditions;
  }

  /**
   * Returns whether the given conditions is valid
   *
   * @param conditions the conditions to check the validity of
   * @return {@code true} if conditions is valid, {@code false} if not
   */
  private boolean isValidConditions(@Nullable TariffConditions conditions) {
    return conditions == null || conditions.validate();
  }

  /**
   * Adds these conditions describe if and when a TariffEnergyType or TariffTimeType applies during
   * a transaction.
   *
   * @param conditions These conditions describe if and when a TariffEnergyType or TariffTimeType
   *     applies during a transaction
   * @return this
   */
  public TariffEnergyPrice withConditions(@Nullable TariffConditions conditions) {
    setConditions(conditions);
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
  public TariffEnergyPrice withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidPriceKwh(priceKwh)
        && isValidConditions(conditions)
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
    TariffEnergyPrice that = (TariffEnergyPrice) o;
    return Objects.equals(priceKwh, that.priceKwh)
        && Objects.equals(conditions, that.conditions)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priceKwh, conditions, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("priceKwh", priceKwh)
        .add("conditions", conditions)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
