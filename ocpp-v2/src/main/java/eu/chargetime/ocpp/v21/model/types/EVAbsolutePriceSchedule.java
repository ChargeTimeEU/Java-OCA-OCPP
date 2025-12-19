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

/** Price schedule of EV energy offer. */
public final class EVAbsolutePriceSchedule {
  /** Starting point in time of the EVEnergyOffer. */
  private ZonedDateTime timeAnchor;

  /** Currency code according to ISO 4217. */
  private String currency;

  /** An entry in price schedule over time for which EV is willing to discharge. */
  private EVAbsolutePriceScheduleEntry[] evAbsolutePriceScheduleEntries;

  /** ISO 15118-20 URN of price algorithm: Power, PeakPower, StackedEnergy. */
  private String priceAlgorithm;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the EVAbsolutePriceSchedule class
   *
   * @param timeAnchor Starting point in time of the EVEnergyOffer.
   * @param currency Currency code according to ISO 4217.
   * @param evAbsolutePriceScheduleEntries An entry in price schedule over time for which EV is
   *     willing to discharge.
   * @param priceAlgorithm ISO 15118-20 URN of price algorithm: Power, PeakPower, StackedEnergy.
   */
  public EVAbsolutePriceSchedule(
      ZonedDateTime timeAnchor,
      String currency,
      EVAbsolutePriceScheduleEntry[] evAbsolutePriceScheduleEntries,
      String priceAlgorithm) {
    setTimeAnchor(timeAnchor);
    setCurrency(currency);
    setEvAbsolutePriceScheduleEntries(evAbsolutePriceScheduleEntries);
    setPriceAlgorithm(priceAlgorithm);
  }

  /**
   * Gets starting point in time of the EVEnergyOffer.
   *
   * @return Starting point in time of the EVEnergyOffer
   */
  public ZonedDateTime getTimeAnchor() {
    return timeAnchor;
  }

  /**
   * Sets starting point in time of the EVEnergyOffer.
   *
   * @param timeAnchor Starting point in time of the EVEnergyOffer
   */
  public void setTimeAnchor(ZonedDateTime timeAnchor) {
    if (!isValidTimeAnchor(timeAnchor)) {
      throw new PropertyConstraintException(timeAnchor, "timeAnchor is invalid");
    }
    this.timeAnchor = timeAnchor;
  }

  /**
   * Returns whether the given timeAnchor is valid
   *
   * @param timeAnchor the timeAnchor to check the validity of
   * @return {@code true} if timeAnchor is valid, {@code false} if not
   */
  private boolean isValidTimeAnchor(ZonedDateTime timeAnchor) {
    return timeAnchor != null;
  }

  /**
   * Gets currency code according to ISO 4217.
   *
   * @return Currency code according to ISO 4217
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets currency code according to ISO 4217.
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
   * Gets an entry in price schedule over time for which EV is willing to discharge.
   *
   * @return An entry in price schedule over time for which EV is willing to discharge
   */
  public EVAbsolutePriceScheduleEntry[] getEvAbsolutePriceScheduleEntries() {
    return evAbsolutePriceScheduleEntries;
  }

  /**
   * Sets an entry in price schedule over time for which EV is willing to discharge.
   *
   * @param evAbsolutePriceScheduleEntries An entry in price schedule over time for which EV is
   *     willing to discharge
   */
  public void setEvAbsolutePriceScheduleEntries(
      EVAbsolutePriceScheduleEntry[] evAbsolutePriceScheduleEntries) {
    if (!isValidEvAbsolutePriceScheduleEntries(evAbsolutePriceScheduleEntries)) {
      throw new PropertyConstraintException(
          evAbsolutePriceScheduleEntries, "evAbsolutePriceScheduleEntries is invalid");
    }
    this.evAbsolutePriceScheduleEntries = evAbsolutePriceScheduleEntries;
  }

  /**
   * Returns whether the given evAbsolutePriceScheduleEntries is valid
   *
   * @param evAbsolutePriceScheduleEntries the evAbsolutePriceScheduleEntries to check the validity
   *     of
   * @return {@code true} if evAbsolutePriceScheduleEntries is valid, {@code false} if not
   */
  private boolean isValidEvAbsolutePriceScheduleEntries(
      EVAbsolutePriceScheduleEntry[] evAbsolutePriceScheduleEntries) {
    return evAbsolutePriceScheduleEntries != null
        && evAbsolutePriceScheduleEntries.length >= 1
        && evAbsolutePriceScheduleEntries.length <= 1024
        && Arrays.stream(evAbsolutePriceScheduleEntries).allMatch(item -> item.validate());
  }

  /**
   * Gets ISO 15118-20 URN of price algorithm: Power, PeakPower, StackedEnergy.
   *
   * @return ISO 15118-20 URN of price algorithm: Power, PeakPower, StackedEnergy
   */
  public String getPriceAlgorithm() {
    return priceAlgorithm;
  }

  /**
   * Sets ISO 15118-20 URN of price algorithm: Power, PeakPower, StackedEnergy.
   *
   * @param priceAlgorithm ISO 15118-20 URN of price algorithm: Power, PeakPower, StackedEnergy
   */
  public void setPriceAlgorithm(String priceAlgorithm) {
    if (!isValidPriceAlgorithm(priceAlgorithm)) {
      throw new PropertyConstraintException(priceAlgorithm, "priceAlgorithm is invalid");
    }
    this.priceAlgorithm = priceAlgorithm;
  }

  /**
   * Returns whether the given priceAlgorithm is valid
   *
   * @param priceAlgorithm the priceAlgorithm to check the validity of
   * @return {@code true} if priceAlgorithm is valid, {@code false} if not
   */
  private boolean isValidPriceAlgorithm(String priceAlgorithm) {
    return priceAlgorithm != null && priceAlgorithm.length() <= 2000;
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
  public EVAbsolutePriceSchedule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidTimeAnchor(timeAnchor)
        && isValidCurrency(currency)
        && isValidEvAbsolutePriceScheduleEntries(evAbsolutePriceScheduleEntries)
        && isValidPriceAlgorithm(priceAlgorithm)
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
    EVAbsolutePriceSchedule that = (EVAbsolutePriceSchedule) o;
    return Objects.equals(timeAnchor, that.timeAnchor)
        && Objects.equals(currency, that.currency)
        && Arrays.equals(evAbsolutePriceScheduleEntries, that.evAbsolutePriceScheduleEntries)
        && Objects.equals(priceAlgorithm, that.priceAlgorithm)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        timeAnchor,
        currency,
        Arrays.hashCode(evAbsolutePriceScheduleEntries),
        priceAlgorithm,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("timeAnchor", timeAnchor)
        .add("currency", currency)
        .add("evAbsolutePriceScheduleEntries", evAbsolutePriceScheduleEntries)
        .add("priceAlgorithm", priceAlgorithm)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
