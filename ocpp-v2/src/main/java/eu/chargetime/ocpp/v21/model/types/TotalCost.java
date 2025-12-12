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

/**
 * The cost calculated during a transaction. It is used both for running cost and final cost of the
 * transaction.
 */
public final class TotalCost {
  /** Currency of the costs in ISO 4217 Code. */
  private String currency;

  /** Type of cost: normal or the minimum or maximum cost. */
  private TariffCostEnum typeOfCost;

  /** Price with and without tax. At least one of exclTax, inclTax must be present. */
  @Nullable private Price fixed;

  /** Price with and without tax. At least one of exclTax, inclTax must be present. */
  @Nullable private Price energy;

  /** Price with and without tax. At least one of exclTax, inclTax must be present. */
  @Nullable private Price chargingTime;

  /** Price with and without tax. At least one of exclTax, inclTax must be present. */
  @Nullable private Price idleTime;

  /** Price with and without tax. At least one of exclTax, inclTax must be present. */
  @Nullable private Price reservationTime;

  /** Price with and without tax. At least one of exclTax, inclTax must be present. */
  @Nullable private Price reservationFixed;

  /**
   * Total cost with and without tax. Contains the total of energy, charging time, idle time, fixed
   * and reservation costs including and/or excluding tax.
   */
  private TotalPrice total;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TotalCost class
   *
   * @param currency Currency of the costs in ISO 4217 Code.
   * @param typeOfCost Type of cost: normal or the minimum or maximum cost.
   * @param total Total cost with and without tax. Contains the total of energy, charging time, idle
   *     time, fixed and reservation costs including and/or excluding tax.
   */
  public TotalCost(String currency, TariffCostEnum typeOfCost, TotalPrice total) {
    setCurrency(currency);
    setTypeOfCost(typeOfCost);
    setTotal(total);
  }

  /**
   * Gets currency of the costs in ISO 4217 Code.
   *
   * @return Currency of the costs in ISO 4217 Code
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets currency of the costs in ISO 4217 Code.
   *
   * @param currency Currency of the costs in ISO 4217 Code
   */
  public void setCurrency(String currency) {
    if (!isValidCurrency(currency)) {
      throw new PropertyConstraintException(currency, "currency is invalid");
    }
    this.currency = currency;
  }

  /**
   * Returns whether the given currency is valid
   *
   * @param currency the currency to check the validity of
   * @return {@code true} if currency is valid, {@code false} if not
   */
  private boolean isValidCurrency(String currency) {
    return currency != null && currency.length() <= 3;
  }

  /**
   * Gets type of cost: normal or the minimum or maximum cost.
   *
   * @return Type of cost: normal or the minimum or maximum cost
   */
  public TariffCostEnum getTypeOfCost() {
    return typeOfCost;
  }

  /**
   * Sets type of cost: normal or the minimum or maximum cost.
   *
   * @param typeOfCost Type of cost: normal or the minimum or maximum cost
   */
  public void setTypeOfCost(TariffCostEnum typeOfCost) {
    if (!isValidTypeOfCost(typeOfCost)) {
      throw new PropertyConstraintException(typeOfCost, "typeOfCost is invalid");
    }
    this.typeOfCost = typeOfCost;
  }

  /**
   * Returns whether the given typeOfCost is valid
   *
   * @param typeOfCost the typeOfCost to check the validity of
   * @return {@code true} if typeOfCost is valid, {@code false} if not
   */
  private boolean isValidTypeOfCost(TariffCostEnum typeOfCost) {
    return typeOfCost != null;
  }

  /**
   * Gets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @return Price with and without tax
   */
  @Nullable
  public Price getFixed() {
    return fixed;
  }

  /**
   * Sets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param fixed Price with and without tax
   */
  public void setFixed(@Nullable Price fixed) {
    if (!isValidFixed(fixed)) {
      throw new PropertyConstraintException(fixed, "fixed is invalid");
    }
    this.fixed = fixed;
  }

  /**
   * Returns whether the given fixed is valid
   *
   * @param fixed the fixed to check the validity of
   * @return {@code true} if fixed is valid, {@code false} if not
   */
  private boolean isValidFixed(@Nullable Price fixed) {
    return fixed == null || fixed.validate();
  }

  /**
   * Adds price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param fixed Price with and without tax
   * @return this
   */
  public TotalCost withFixed(@Nullable Price fixed) {
    setFixed(fixed);
    return this;
  }

  /**
   * Gets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @return Price with and without tax
   */
  @Nullable
  public Price getEnergy() {
    return energy;
  }

  /**
   * Sets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param energy Price with and without tax
   */
  public void setEnergy(@Nullable Price energy) {
    if (!isValidEnergy(energy)) {
      throw new PropertyConstraintException(energy, "energy is invalid");
    }
    this.energy = energy;
  }

  /**
   * Returns whether the given energy is valid
   *
   * @param energy the energy to check the validity of
   * @return {@code true} if energy is valid, {@code false} if not
   */
  private boolean isValidEnergy(@Nullable Price energy) {
    return energy == null || energy.validate();
  }

  /**
   * Adds price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param energy Price with and without tax
   * @return this
   */
  public TotalCost withEnergy(@Nullable Price energy) {
    setEnergy(energy);
    return this;
  }

  /**
   * Gets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @return Price with and without tax
   */
  @Nullable
  public Price getChargingTime() {
    return chargingTime;
  }

  /**
   * Sets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param chargingTime Price with and without tax
   */
  public void setChargingTime(@Nullable Price chargingTime) {
    if (!isValidChargingTime(chargingTime)) {
      throw new PropertyConstraintException(chargingTime, "chargingTime is invalid");
    }
    this.chargingTime = chargingTime;
  }

  /**
   * Returns whether the given chargingTime is valid
   *
   * @param chargingTime the chargingTime to check the validity of
   * @return {@code true} if chargingTime is valid, {@code false} if not
   */
  private boolean isValidChargingTime(@Nullable Price chargingTime) {
    return chargingTime == null || chargingTime.validate();
  }

  /**
   * Adds price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param chargingTime Price with and without tax
   * @return this
   */
  public TotalCost withChargingTime(@Nullable Price chargingTime) {
    setChargingTime(chargingTime);
    return this;
  }

  /**
   * Gets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @return Price with and without tax
   */
  @Nullable
  public Price getIdleTime() {
    return idleTime;
  }

  /**
   * Sets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param idleTime Price with and without tax
   */
  public void setIdleTime(@Nullable Price idleTime) {
    if (!isValidIdleTime(idleTime)) {
      throw new PropertyConstraintException(idleTime, "idleTime is invalid");
    }
    this.idleTime = idleTime;
  }

  /**
   * Returns whether the given idleTime is valid
   *
   * @param idleTime the idleTime to check the validity of
   * @return {@code true} if idleTime is valid, {@code false} if not
   */
  private boolean isValidIdleTime(@Nullable Price idleTime) {
    return idleTime == null || idleTime.validate();
  }

  /**
   * Adds price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param idleTime Price with and without tax
   * @return this
   */
  public TotalCost withIdleTime(@Nullable Price idleTime) {
    setIdleTime(idleTime);
    return this;
  }

  /**
   * Gets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @return Price with and without tax
   */
  @Nullable
  public Price getReservationTime() {
    return reservationTime;
  }

  /**
   * Sets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param reservationTime Price with and without tax
   */
  public void setReservationTime(@Nullable Price reservationTime) {
    if (!isValidReservationTime(reservationTime)) {
      throw new PropertyConstraintException(reservationTime, "reservationTime is invalid");
    }
    this.reservationTime = reservationTime;
  }

  /**
   * Returns whether the given reservationTime is valid
   *
   * @param reservationTime the reservationTime to check the validity of
   * @return {@code true} if reservationTime is valid, {@code false} if not
   */
  private boolean isValidReservationTime(@Nullable Price reservationTime) {
    return reservationTime == null || reservationTime.validate();
  }

  /**
   * Adds price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param reservationTime Price with and without tax
   * @return this
   */
  public TotalCost withReservationTime(@Nullable Price reservationTime) {
    setReservationTime(reservationTime);
    return this;
  }

  /**
   * Gets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @return Price with and without tax
   */
  @Nullable
  public Price getReservationFixed() {
    return reservationFixed;
  }

  /**
   * Sets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param reservationFixed Price with and without tax
   */
  public void setReservationFixed(@Nullable Price reservationFixed) {
    if (!isValidReservationFixed(reservationFixed)) {
      throw new PropertyConstraintException(reservationFixed, "reservationFixed is invalid");
    }
    this.reservationFixed = reservationFixed;
  }

  /**
   * Returns whether the given reservationFixed is valid
   *
   * @param reservationFixed the reservationFixed to check the validity of
   * @return {@code true} if reservationFixed is valid, {@code false} if not
   */
  private boolean isValidReservationFixed(@Nullable Price reservationFixed) {
    return reservationFixed == null || reservationFixed.validate();
  }

  /**
   * Adds price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param reservationFixed Price with and without tax
   * @return this
   */
  public TotalCost withReservationFixed(@Nullable Price reservationFixed) {
    setReservationFixed(reservationFixed);
    return this;
  }

  /**
   * Gets total cost with and without tax. Contains the total of energy, charging time, idle time,
   * fixed and reservation costs including and/or excluding tax.
   *
   * @return Total cost with and without tax
   */
  public TotalPrice getTotal() {
    return total;
  }

  /**
   * Sets total cost with and without tax. Contains the total of energy, charging time, idle time,
   * fixed and reservation costs including and/or excluding tax.
   *
   * @param total Total cost with and without tax
   */
  public void setTotal(TotalPrice total) {
    if (!isValidTotal(total)) {
      throw new PropertyConstraintException(total, "total is invalid");
    }
    this.total = total;
  }

  /**
   * Returns whether the given total is valid
   *
   * @param total the total to check the validity of
   * @return {@code true} if total is valid, {@code false} if not
   */
  private boolean isValidTotal(TotalPrice total) {
    return total != null && total.validate();
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
  public TotalCost withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidCurrency(currency)
        && isValidTypeOfCost(typeOfCost)
        && isValidFixed(fixed)
        && isValidEnergy(energy)
        && isValidChargingTime(chargingTime)
        && isValidIdleTime(idleTime)
        && isValidReservationTime(reservationTime)
        && isValidReservationFixed(reservationFixed)
        && isValidTotal(total)
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
    TotalCost that = (TotalCost) o;
    return Objects.equals(currency, that.currency)
        && Objects.equals(typeOfCost, that.typeOfCost)
        && Objects.equals(fixed, that.fixed)
        && Objects.equals(energy, that.energy)
        && Objects.equals(chargingTime, that.chargingTime)
        && Objects.equals(idleTime, that.idleTime)
        && Objects.equals(reservationTime, that.reservationTime)
        && Objects.equals(reservationFixed, that.reservationFixed)
        && Objects.equals(total, that.total)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        currency,
        typeOfCost,
        fixed,
        energy,
        chargingTime,
        idleTime,
        reservationTime,
        reservationFixed,
        total,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("currency", currency)
        .add("typeOfCost", typeOfCost)
        .add("fixed", fixed)
        .add("energy", energy)
        .add("chargingTime", chargingTime)
        .add("idleTime", idleTime)
        .add("reservationTime", reservationTime)
        .add("reservationFixed", reservationFixed)
        .add("total", total)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
