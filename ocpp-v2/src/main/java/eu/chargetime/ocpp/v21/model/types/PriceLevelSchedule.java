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
 * The PriceLevelScheduleType is modeled after the same type that is defined in ISO 15118-20, such
 * that if it is supplied by an EMSP as a signed EXI message, the conversion from EXI to JSON (in
 * OCPP) and back to EXI (for ISO 15118-20) does not change the digest and therefore does not
 * invalidate the signature.
 */
public final class PriceLevelSchedule {
  /** Part of ISO 15118-20 price schedule. */
  private PriceLevelScheduleEntry[] priceLevelScheduleEntries;

  /** Starting point of this price schedule. */
  private ZonedDateTime timeAnchor;

  /** Unique ID of this price schedule. */
  private Integer priceScheduleId;

  /** Description of the price schedule. */
  @Nullable private String priceScheduleDescription;

  /** The overall number of distinct price level elements used across all PriceLevelSchedules. */
  private Integer numberOfPriceLevels;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the PriceLevelSchedule class
   *
   * @param priceLevelScheduleEntries Part of ISO 15118-20 price schedule.
   * @param timeAnchor Starting point of this price schedule.
   * @param priceScheduleId Unique ID of this price schedule.
   * @param numberOfPriceLevels The overall number of distinct price level elements used across all
   *     PriceLevelSchedules.
   */
  public PriceLevelSchedule(
      PriceLevelScheduleEntry[] priceLevelScheduleEntries,
      ZonedDateTime timeAnchor,
      Integer priceScheduleId,
      Integer numberOfPriceLevels) {
    setPriceLevelScheduleEntries(priceLevelScheduleEntries);
    setTimeAnchor(timeAnchor);
    setPriceScheduleId(priceScheduleId);
    setNumberOfPriceLevels(numberOfPriceLevels);
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  public PriceLevelScheduleEntry[] getPriceLevelScheduleEntries() {
    return priceLevelScheduleEntries;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param priceLevelScheduleEntries Part of ISO 15118-20 price schedule
   */
  public void setPriceLevelScheduleEntries(PriceLevelScheduleEntry[] priceLevelScheduleEntries) {
    if (!isValidPriceLevelScheduleEntries(priceLevelScheduleEntries)) {
      throw new PropertyConstraintException(
          priceLevelScheduleEntries, "priceLevelScheduleEntries is invalid");
    }
    this.priceLevelScheduleEntries = priceLevelScheduleEntries;
  }

  /**
   * Returns whether the given priceLevelScheduleEntries is valid
   *
   * @param priceLevelScheduleEntries the priceLevelScheduleEntries to check the validity of
   * @return {@code true} if priceLevelScheduleEntries is valid, {@code false} if not
   */
  private boolean isValidPriceLevelScheduleEntries(
      PriceLevelScheduleEntry[] priceLevelScheduleEntries) {
    return priceLevelScheduleEntries != null
        && priceLevelScheduleEntries.length >= 1
        && priceLevelScheduleEntries.length <= 100
        && Arrays.stream(priceLevelScheduleEntries).allMatch(item -> item.validate());
  }

  /**
   * Gets starting point of this price schedule.
   *
   * @return Starting point of this price schedule
   */
  public ZonedDateTime getTimeAnchor() {
    return timeAnchor;
  }

  /**
   * Sets starting point of this price schedule.
   *
   * @param timeAnchor Starting point of this price schedule
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
   * Gets unique ID of this price schedule.
   *
   * @return Unique ID of this price schedule
   */
  public Integer getPriceScheduleId() {
    return priceScheduleId;
  }

  /**
   * Sets unique ID of this price schedule.
   *
   * @param priceScheduleId Unique ID of this price schedule
   */
  public void setPriceScheduleId(Integer priceScheduleId) {
    if (!isValidPriceScheduleId(priceScheduleId)) {
      throw new PropertyConstraintException(priceScheduleId, "priceScheduleId is invalid");
    }
    this.priceScheduleId = priceScheduleId;
  }

  /**
   * Returns whether the given priceScheduleId is valid
   *
   * @param priceScheduleId the priceScheduleId to check the validity of
   * @return {@code true} if priceScheduleId is valid, {@code false} if not
   */
  private boolean isValidPriceScheduleId(Integer priceScheduleId) {
    return priceScheduleId != null && priceScheduleId >= 0;
  }

  /**
   * Gets description of the price schedule.
   *
   * @return Description of the price schedule
   */
  @Nullable
  public String getPriceScheduleDescription() {
    return priceScheduleDescription;
  }

  /**
   * Sets description of the price schedule.
   *
   * @param priceScheduleDescription Description of the price schedule
   */
  public void setPriceScheduleDescription(@Nullable String priceScheduleDescription) {
    if (!isValidPriceScheduleDescription(priceScheduleDescription)) {
      throw new PropertyConstraintException(
          priceScheduleDescription, "priceScheduleDescription is invalid");
    }
    this.priceScheduleDescription = priceScheduleDescription;
  }

  /**
   * Returns whether the given priceScheduleDescription is valid
   *
   * @param priceScheduleDescription the priceScheduleDescription to check the validity of
   * @return {@code true} if priceScheduleDescription is valid, {@code false} if not
   */
  private boolean isValidPriceScheduleDescription(@Nullable String priceScheduleDescription) {
    return priceScheduleDescription == null || priceScheduleDescription.length() <= 32;
  }

  /**
   * Adds description of the price schedule.
   *
   * @param priceScheduleDescription Description of the price schedule
   * @return this
   */
  public PriceLevelSchedule withPriceScheduleDescription(
      @Nullable String priceScheduleDescription) {
    setPriceScheduleDescription(priceScheduleDescription);
    return this;
  }

  /**
   * Gets the overall number of distinct price level elements used across all PriceLevelSchedules.
   *
   * @return The overall number of distinct price level elements used across all PriceLevelSchedules
   */
  public Integer getNumberOfPriceLevels() {
    return numberOfPriceLevels;
  }

  /**
   * Sets the overall number of distinct price level elements used across all PriceLevelSchedules.
   *
   * @param numberOfPriceLevels The overall number of distinct price level elements used across all
   *     PriceLevelSchedules
   */
  public void setNumberOfPriceLevels(Integer numberOfPriceLevels) {
    if (!isValidNumberOfPriceLevels(numberOfPriceLevels)) {
      throw new PropertyConstraintException(numberOfPriceLevels, "numberOfPriceLevels is invalid");
    }
    this.numberOfPriceLevels = numberOfPriceLevels;
  }

  /**
   * Returns whether the given numberOfPriceLevels is valid
   *
   * @param numberOfPriceLevels the numberOfPriceLevels to check the validity of
   * @return {@code true} if numberOfPriceLevels is valid, {@code false} if not
   */
  private boolean isValidNumberOfPriceLevels(Integer numberOfPriceLevels) {
    return numberOfPriceLevels != null && numberOfPriceLevels >= 0;
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
  public PriceLevelSchedule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidPriceLevelScheduleEntries(priceLevelScheduleEntries)
        && isValidTimeAnchor(timeAnchor)
        && isValidPriceScheduleId(priceScheduleId)
        && isValidPriceScheduleDescription(priceScheduleDescription)
        && isValidNumberOfPriceLevels(numberOfPriceLevels)
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
    PriceLevelSchedule that = (PriceLevelSchedule) o;
    return Arrays.equals(priceLevelScheduleEntries, that.priceLevelScheduleEntries)
        && Objects.equals(timeAnchor, that.timeAnchor)
        && Objects.equals(priceScheduleId, that.priceScheduleId)
        && Objects.equals(priceScheduleDescription, that.priceScheduleDescription)
        && Objects.equals(numberOfPriceLevels, that.numberOfPriceLevels)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        Arrays.hashCode(priceLevelScheduleEntries),
        timeAnchor,
        priceScheduleId,
        priceScheduleDescription,
        numberOfPriceLevels,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("priceLevelScheduleEntries", priceLevelScheduleEntries)
        .add("timeAnchor", timeAnchor)
        .add("priceScheduleId", priceScheduleId)
        .add("priceScheduleDescription", priceScheduleDescription)
        .add("numberOfPriceLevels", numberOfPriceLevels)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
