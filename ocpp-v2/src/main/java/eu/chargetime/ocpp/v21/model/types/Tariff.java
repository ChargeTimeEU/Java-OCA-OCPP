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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * A tariff is described by fields with prices for: energy, charging time, idle time, fixed fee,
 * reservation time,
 *
 * <pre>
 * reservation fixed fee.
 * Each of these fields may have (optional) conditions that specify when a price is applicable.
 * The description contains a human-readable explanation of the tariff to be shown to the user.
 * The other fields are parameters that define the tariff. These are used by the charging station to
 * calculate the price.
 * </pre>
 */
public final class Tariff {
  /** Unique id of tariff */
  private String tariffId;

  /** Message details, for a message to be displayed on a Charging Station. */
  @Nullable private MessageContent[] description;

  /** Currency code according to ISO 4217 */
  private String currency;

  /** Price elements and tax for energy */
  @Nullable private TariffEnergy energy;

  /** Time when this tariff becomes active. When absent, it is immediately active. */
  @Nullable private ZonedDateTime validFrom;

  /** Price elements and tax for time */
  @Nullable private TariffTime chargingTime;

  /** Price elements and tax for time */
  @Nullable private TariffTime idleTime;

  /** fixedFee */
  @Nullable private TariffFixed fixedFee;

  /** Price elements and tax for time */
  @Nullable private TariffTime reservationTime;

  /** reservationFixed */
  @Nullable private TariffFixed reservationFixed;

  /** Price with and without tax. At least one of exclTax, inclTax must be present. */
  @Nullable private Price minCost;

  /** Price with and without tax. At least one of exclTax, inclTax must be present. */
  @Nullable private Price maxCost;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the Tariff class
   *
   * @param tariffId Unique id of tariff
   * @param currency Currency code according to ISO 4217
   */
  public Tariff(String tariffId, String currency) {
    setTariffId(tariffId);
    setCurrency(currency);
  }

  /**
   * Gets unique id of tariff
   *
   * @return Unique id of tariff
   */
  public String getTariffId() {
    return tariffId;
  }

  /**
   * Sets unique id of tariff
   *
   * @param tariffId Unique id of tariff
   */
  public void setTariffId(String tariffId) {
    if (!isValidTariffId(tariffId)) {
      throw new PropertyConstraintException(tariffId, "tariffId is invalid");
    }
    this.tariffId = tariffId;
  }

  /**
   * Returns whether the given tariffId is valid
   *
   * @param tariffId the tariffId to check the validity of
   * @return {@code true} if tariffId is valid, {@code false} if not
   */
  private boolean isValidTariffId(String tariffId) {
    return tariffId != null && tariffId.length() <= 60;
  }

  /**
   * Gets message details, for a message to be displayed on a Charging Station.
   *
   * @return Message details, for a message to be displayed on a Charging Station
   */
  @Nullable
  public MessageContent[] getDescription() {
    return description;
  }

  /**
   * Sets message details, for a message to be displayed on a Charging Station.
   *
   * @param description Message details, for a message to be displayed on a Charging Station
   */
  public void setDescription(@Nullable MessageContent[] description) {
    if (!isValidDescription(description)) {
      throw new PropertyConstraintException(description, "description is invalid");
    }
    this.description = description;
  }

  /**
   * Returns whether the given description is valid
   *
   * @param description the description to check the validity of
   * @return {@code true} if description is valid, {@code false} if not
   */
  private boolean isValidDescription(@Nullable MessageContent[] description) {
    return description == null
        || (description.length >= 1
            && description.length <= 10
            && Arrays.stream(description).allMatch(item -> item.validate()));
  }

  /**
   * Adds message details, for a message to be displayed on a Charging Station.
   *
   * @param description Message details, for a message to be displayed on a Charging Station
   * @return this
   */
  public Tariff withDescription(@Nullable MessageContent[] description) {
    setDescription(description);
    return this;
  }

  /**
   * Gets currency code according to ISO 4217
   *
   * @return Currency code according to ISO 4217
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets currency code according to ISO 4217
   *
   * @param currency Currency code according to ISO 4217
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
   * Gets price elements and tax for energy
   *
   * @return Price elements and tax for energy
   */
  @Nullable
  public TariffEnergy getEnergy() {
    return energy;
  }

  /**
   * Sets price elements and tax for energy
   *
   * @param energy Price elements and tax for energy
   */
  public void setEnergy(@Nullable TariffEnergy energy) {
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
  private boolean isValidEnergy(@Nullable TariffEnergy energy) {
    return energy == null || energy.validate();
  }

  /**
   * Adds price elements and tax for energy
   *
   * @param energy Price elements and tax for energy
   * @return this
   */
  public Tariff withEnergy(@Nullable TariffEnergy energy) {
    setEnergy(energy);
    return this;
  }

  /**
   * Gets time when this tariff becomes active. When absent, it is immediately active.
   *
   * @return Time when this tariff becomes active
   */
  @Nullable
  public ZonedDateTime getValidFrom() {
    return validFrom;
  }

  /**
   * Sets time when this tariff becomes active. When absent, it is immediately active.
   *
   * @param validFrom Time when this tariff becomes active
   */
  public void setValidFrom(@Nullable ZonedDateTime validFrom) {
    this.validFrom = validFrom;
  }

  /**
   * Adds time when this tariff becomes active. When absent, it is immediately active.
   *
   * @param validFrom Time when this tariff becomes active
   * @return this
   */
  public Tariff withValidFrom(@Nullable ZonedDateTime validFrom) {
    setValidFrom(validFrom);
    return this;
  }

  /**
   * Gets price elements and tax for time
   *
   * @return Price elements and tax for time
   */
  @Nullable
  public TariffTime getChargingTime() {
    return chargingTime;
  }

  /**
   * Sets price elements and tax for time
   *
   * @param chargingTime Price elements and tax for time
   */
  public void setChargingTime(@Nullable TariffTime chargingTime) {
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
  private boolean isValidChargingTime(@Nullable TariffTime chargingTime) {
    return chargingTime == null || chargingTime.validate();
  }

  /**
   * Adds price elements and tax for time
   *
   * @param chargingTime Price elements and tax for time
   * @return this
   */
  public Tariff withChargingTime(@Nullable TariffTime chargingTime) {
    setChargingTime(chargingTime);
    return this;
  }

  /**
   * Gets price elements and tax for time
   *
   * @return Price elements and tax for time
   */
  @Nullable
  public TariffTime getIdleTime() {
    return idleTime;
  }

  /**
   * Sets price elements and tax for time
   *
   * @param idleTime Price elements and tax for time
   */
  public void setIdleTime(@Nullable TariffTime idleTime) {
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
  private boolean isValidIdleTime(@Nullable TariffTime idleTime) {
    return idleTime == null || idleTime.validate();
  }

  /**
   * Adds price elements and tax for time
   *
   * @param idleTime Price elements and tax for time
   * @return this
   */
  public Tariff withIdleTime(@Nullable TariffTime idleTime) {
    setIdleTime(idleTime);
    return this;
  }

  /**
   * Gets fixedFee
   *
   * @return fixedFee
   */
  @Nullable
  public TariffFixed getFixedFee() {
    return fixedFee;
  }

  /**
   * Sets fixedFee
   *
   * @param fixedFee fixedFee
   */
  public void setFixedFee(@Nullable TariffFixed fixedFee) {
    if (!isValidFixedFee(fixedFee)) {
      throw new PropertyConstraintException(fixedFee, "fixedFee is invalid");
    }
    this.fixedFee = fixedFee;
  }

  /**
   * Returns whether the given fixedFee is valid
   *
   * @param fixedFee the fixedFee to check the validity of
   * @return {@code true} if fixedFee is valid, {@code false} if not
   */
  private boolean isValidFixedFee(@Nullable TariffFixed fixedFee) {
    return fixedFee == null || fixedFee.validate();
  }

  /**
   * Adds fixedFee
   *
   * @param fixedFee fixedFee
   * @return this
   */
  public Tariff withFixedFee(@Nullable TariffFixed fixedFee) {
    setFixedFee(fixedFee);
    return this;
  }

  /**
   * Gets price elements and tax for time
   *
   * @return Price elements and tax for time
   */
  @Nullable
  public TariffTime getReservationTime() {
    return reservationTime;
  }

  /**
   * Sets price elements and tax for time
   *
   * @param reservationTime Price elements and tax for time
   */
  public void setReservationTime(@Nullable TariffTime reservationTime) {
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
  private boolean isValidReservationTime(@Nullable TariffTime reservationTime) {
    return reservationTime == null || reservationTime.validate();
  }

  /**
   * Adds price elements and tax for time
   *
   * @param reservationTime Price elements and tax for time
   * @return this
   */
  public Tariff withReservationTime(@Nullable TariffTime reservationTime) {
    setReservationTime(reservationTime);
    return this;
  }

  /**
   * Gets reservationFixed
   *
   * @return reservationFixed
   */
  @Nullable
  public TariffFixed getReservationFixed() {
    return reservationFixed;
  }

  /**
   * Sets reservationFixed
   *
   * @param reservationFixed reservationFixed
   */
  public void setReservationFixed(@Nullable TariffFixed reservationFixed) {
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
  private boolean isValidReservationFixed(@Nullable TariffFixed reservationFixed) {
    return reservationFixed == null || reservationFixed.validate();
  }

  /**
   * Adds reservationFixed
   *
   * @param reservationFixed reservationFixed
   * @return this
   */
  public Tariff withReservationFixed(@Nullable TariffFixed reservationFixed) {
    setReservationFixed(reservationFixed);
    return this;
  }

  /**
   * Gets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @return Price with and without tax
   */
  @Nullable
  public Price getMinCost() {
    return minCost;
  }

  /**
   * Sets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param minCost Price with and without tax
   */
  public void setMinCost(@Nullable Price minCost) {
    if (!isValidMinCost(minCost)) {
      throw new PropertyConstraintException(minCost, "minCost is invalid");
    }
    this.minCost = minCost;
  }

  /**
   * Returns whether the given minCost is valid
   *
   * @param minCost the minCost to check the validity of
   * @return {@code true} if minCost is valid, {@code false} if not
   */
  private boolean isValidMinCost(@Nullable Price minCost) {
    return minCost == null || minCost.validate();
  }

  /**
   * Adds price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param minCost Price with and without tax
   * @return this
   */
  public Tariff withMinCost(@Nullable Price minCost) {
    setMinCost(minCost);
    return this;
  }

  /**
   * Gets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @return Price with and without tax
   */
  @Nullable
  public Price getMaxCost() {
    return maxCost;
  }

  /**
   * Sets price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param maxCost Price with and without tax
   */
  public void setMaxCost(@Nullable Price maxCost) {
    if (!isValidMaxCost(maxCost)) {
      throw new PropertyConstraintException(maxCost, "maxCost is invalid");
    }
    this.maxCost = maxCost;
  }

  /**
   * Returns whether the given maxCost is valid
   *
   * @param maxCost the maxCost to check the validity of
   * @return {@code true} if maxCost is valid, {@code false} if not
   */
  private boolean isValidMaxCost(@Nullable Price maxCost) {
    return maxCost == null || maxCost.validate();
  }

  /**
   * Adds price with and without tax. At least one of exclTax, inclTax must be present.
   *
   * @param maxCost Price with and without tax
   * @return this
   */
  public Tariff withMaxCost(@Nullable Price maxCost) {
    setMaxCost(maxCost);
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
  public Tariff withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidTariffId(tariffId)
        && isValidDescription(description)
        && isValidCurrency(currency)
        && isValidEnergy(energy)
        && isValidChargingTime(chargingTime)
        && isValidIdleTime(idleTime)
        && isValidFixedFee(fixedFee)
        && isValidReservationTime(reservationTime)
        && isValidReservationFixed(reservationFixed)
        && isValidMinCost(minCost)
        && isValidMaxCost(maxCost)
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
    Tariff that = (Tariff) o;
    return Objects.equals(tariffId, that.tariffId)
        && Arrays.equals(description, that.description)
        && Objects.equals(currency, that.currency)
        && Objects.equals(energy, that.energy)
        && Objects.equals(validFrom, that.validFrom)
        && Objects.equals(chargingTime, that.chargingTime)
        && Objects.equals(idleTime, that.idleTime)
        && Objects.equals(fixedFee, that.fixedFee)
        && Objects.equals(reservationTime, that.reservationTime)
        && Objects.equals(reservationFixed, that.reservationFixed)
        && Objects.equals(minCost, that.minCost)
        && Objects.equals(maxCost, that.maxCost)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        tariffId,
        Arrays.hashCode(description),
        currency,
        energy,
        validFrom,
        chargingTime,
        idleTime,
        fixedFee,
        reservationTime,
        reservationFixed,
        minCost,
        maxCost,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("tariffId", tariffId)
        .add("description", description)
        .add("currency", currency)
        .add("energy", energy)
        .add("validFrom", validFrom)
        .add("chargingTime", chargingTime)
        .add("idleTime", idleTime)
        .add("fixedFee", fixedFee)
        .add("reservationTime", reservationTime)
        .add("reservationFixed", reservationFixed)
        .add("minCost", minCost)
        .add("maxCost", maxCost)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
