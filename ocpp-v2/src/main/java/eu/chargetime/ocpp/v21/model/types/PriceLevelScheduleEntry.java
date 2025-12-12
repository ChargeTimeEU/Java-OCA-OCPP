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

/** Part of ISO 15118-20 price schedule. */
public final class PriceLevelScheduleEntry {
  /** The amount of seconds that define the duration of this given PriceLevelScheduleEntry. */
  private Integer duration;

  /**
   * The price level of this PriceLevelScheduleEntry (referring to NumberOfPriceLevels). Small
   * values for the PriceLevel represent a cheaper PriceLevelScheduleEntry. Large values for the
   * PriceLevel represent a more expensive PriceLevelScheduleEntry.
   */
  private Integer priceLevel;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the PriceLevelScheduleEntry class
   *
   * @param duration The amount of seconds that define the duration of this given
   *     PriceLevelScheduleEntry.
   * @param priceLevel The price level of this PriceLevelScheduleEntry (referring to
   *     NumberOfPriceLevels). Small values for the PriceLevel represent a cheaper
   *     PriceLevelScheduleEntry. Large values for the PriceLevel represent a more expensive
   *     PriceLevelScheduleEntry.
   */
  public PriceLevelScheduleEntry(Integer duration, Integer priceLevel) {
    setDuration(duration);
    setPriceLevel(priceLevel);
  }

  /**
   * Gets the amount of seconds that define the duration of this given PriceLevelScheduleEntry.
   *
   * @return The amount of seconds that define the duration of this given PriceLevelScheduleEntry
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets the amount of seconds that define the duration of this given PriceLevelScheduleEntry.
   *
   * @param duration The amount of seconds that define the duration of this given
   *     PriceLevelScheduleEntry
   */
  public void setDuration(Integer duration) {
    if (!isValidDuration(duration)) {
      throw new PropertyConstraintException(duration, "duration is invalid");
    }
    this.duration = duration;
  }

  /**
   * Returns whether the given duration is valid
   *
   * @param duration the duration to check the validity of
   * @return {@code true} if duration is valid, {@code false} if not
   */
  private boolean isValidDuration(Integer duration) {
    return duration != null;
  }

  /**
   * Gets the price level of this PriceLevelScheduleEntry (referring to NumberOfPriceLevels). Small
   * values for the PriceLevel represent a cheaper PriceLevelScheduleEntry. Large values for the
   * PriceLevel represent a more expensive PriceLevelScheduleEntry.
   *
   * @return The price level of this PriceLevelScheduleEntry (referring to NumberOfPriceLevels)
   */
  public Integer getPriceLevel() {
    return priceLevel;
  }

  /**
   * Sets the price level of this PriceLevelScheduleEntry (referring to NumberOfPriceLevels). Small
   * values for the PriceLevel represent a cheaper PriceLevelScheduleEntry. Large values for the
   * PriceLevel represent a more expensive PriceLevelScheduleEntry.
   *
   * @param priceLevel The price level of this PriceLevelScheduleEntry (referring to
   *     NumberOfPriceLevels)
   */
  public void setPriceLevel(Integer priceLevel) {
    if (!isValidPriceLevel(priceLevel)) {
      throw new PropertyConstraintException(priceLevel, "priceLevel is invalid");
    }
    this.priceLevel = priceLevel;
  }

  /**
   * Returns whether the given priceLevel is valid
   *
   * @param priceLevel the priceLevel to check the validity of
   * @return {@code true} if priceLevel is valid, {@code false} if not
   */
  private boolean isValidPriceLevel(Integer priceLevel) {
    return priceLevel != null && priceLevel >= 0;
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
  public PriceLevelScheduleEntry withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidDuration(duration)
        && isValidPriceLevel(priceLevel)
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
    PriceLevelScheduleEntry that = (PriceLevelScheduleEntry) o;
    return Objects.equals(duration, that.duration)
        && Objects.equals(priceLevel, that.priceLevel)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(duration, priceLevel, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("duration", duration)
        .add("priceLevel", priceLevel)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
