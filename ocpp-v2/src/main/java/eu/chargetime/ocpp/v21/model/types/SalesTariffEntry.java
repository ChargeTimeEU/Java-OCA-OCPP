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
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/** SalesTariffEntryType */
public final class SalesTariffEntry {
  /** relativeTimeInterval */
  private RelativeTimeInterval relativeTimeInterval;

  /**
   * The price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the
   * EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more
   * expensive TariffEntry.
   */
  @Nullable private Integer ePriceLevel;

  /** consumptionCost */
  @Nullable private ConsumptionCost[] consumptionCost;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the SalesTariffEntry class
   *
   * @param relativeTimeInterval relativeTimeInterval
   */
  public SalesTariffEntry(RelativeTimeInterval relativeTimeInterval) {
    setRelativeTimeInterval(relativeTimeInterval);
  }

  /**
   * Gets relativeTimeInterval
   *
   * @return relativeTimeInterval
   */
  public RelativeTimeInterval getRelativeTimeInterval() {
    return relativeTimeInterval;
  }

  /**
   * Sets relativeTimeInterval
   *
   * @param relativeTimeInterval relativeTimeInterval
   */
  public void setRelativeTimeInterval(RelativeTimeInterval relativeTimeInterval) {
    if (!isValidRelativeTimeInterval(relativeTimeInterval)) {
      throw new PropertyConstraintException(
          relativeTimeInterval, "relativeTimeInterval is invalid");
    }
    this.relativeTimeInterval = relativeTimeInterval;
  }

  /**
   * Returns whether the given relativeTimeInterval is valid
   *
   * @param relativeTimeInterval the relativeTimeInterval to check the validity of
   * @return {@code true} if relativeTimeInterval is valid, {@code false} if not
   */
  private boolean isValidRelativeTimeInterval(RelativeTimeInterval relativeTimeInterval) {
    return relativeTimeInterval != null && relativeTimeInterval.validate();
  }

  /**
   * Gets the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for
   * the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a
   * more expensive TariffEntry.
   *
   * @return The price level of this SalesTariffEntry (referring to NumEPriceLevels)
   */
  @Nullable
  public Integer getEPriceLevel() {
    return ePriceLevel;
  }

  /**
   * Sets the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for
   * the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a
   * more expensive TariffEntry.
   *
   * @param ePriceLevel The price level of this SalesTariffEntry (referring to NumEPriceLevels)
   */
  public void setEPriceLevel(@Nullable Integer ePriceLevel) {
    if (!isValidEPriceLevel(ePriceLevel)) {
      throw new PropertyConstraintException(ePriceLevel, "ePriceLevel is invalid");
    }
    this.ePriceLevel = ePriceLevel;
  }

  /**
   * Returns whether the given ePriceLevel is valid
   *
   * @param ePriceLevel the ePriceLevel to check the validity of
   * @return {@code true} if ePriceLevel is valid, {@code false} if not
   */
  private boolean isValidEPriceLevel(@Nullable Integer ePriceLevel) {
    return ePriceLevel == null || (ePriceLevel >= 0);
  }

  /**
   * Adds the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for
   * the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a
   * more expensive TariffEntry.
   *
   * @param ePriceLevel The price level of this SalesTariffEntry (referring to NumEPriceLevels)
   * @return this
   */
  public SalesTariffEntry withEPriceLevel(@Nullable Integer ePriceLevel) {
    setEPriceLevel(ePriceLevel);
    return this;
  }

  /**
   * Gets consumptionCost
   *
   * @return consumptionCost
   */
  @Nullable
  public ConsumptionCost[] getConsumptionCost() {
    return consumptionCost;
  }

  /**
   * Sets consumptionCost
   *
   * @param consumptionCost consumptionCost
   */
  public void setConsumptionCost(@Nullable ConsumptionCost[] consumptionCost) {
    if (!isValidConsumptionCost(consumptionCost)) {
      throw new PropertyConstraintException(consumptionCost, "consumptionCost is invalid");
    }
    this.consumptionCost = consumptionCost;
  }

  /**
   * Returns whether the given consumptionCost is valid
   *
   * @param consumptionCost the consumptionCost to check the validity of
   * @return {@code true} if consumptionCost is valid, {@code false} if not
   */
  private boolean isValidConsumptionCost(@Nullable ConsumptionCost[] consumptionCost) {
    return consumptionCost == null
        || (consumptionCost.length >= 1
            && consumptionCost.length <= 3
            && Arrays.stream(consumptionCost).allMatch(item -> item.validate()));
  }

  /**
   * Adds consumptionCost
   *
   * @param consumptionCost consumptionCost
   * @return this
   */
  public SalesTariffEntry withConsumptionCost(@Nullable ConsumptionCost[] consumptionCost) {
    setConsumptionCost(consumptionCost);
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
  public SalesTariffEntry withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidRelativeTimeInterval(relativeTimeInterval)
        && isValidEPriceLevel(ePriceLevel)
        && isValidConsumptionCost(consumptionCost)
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
    SalesTariffEntry that = (SalesTariffEntry) o;
    return Objects.equals(relativeTimeInterval, that.relativeTimeInterval)
        && Objects.equals(ePriceLevel, that.ePriceLevel)
        && Arrays.equals(consumptionCost, that.consumptionCost)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        relativeTimeInterval, ePriceLevel, Arrays.hashCode(consumptionCost), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("relativeTimeInterval", relativeTimeInterval)
        .add("ePriceLevel", ePriceLevel)
        .add("consumptionCost", consumptionCost)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
