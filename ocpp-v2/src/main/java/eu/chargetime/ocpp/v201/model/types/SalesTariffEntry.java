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
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/** Sales Tariff Entry */
public final class SalesTariffEntry {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Relative Timer Interval */
  private RelativeTimeInterval relativeTimeInterval;

  /**
   * Sales Tariff Entry. E Price Level. Unsigned Integer
   *
   * <p>The price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for
   * the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a
   * more expensive TariffEntry.
   */
  @Nullable private Integer ePriceLevel;

  /** Consumption Cost */
  @Nullable private ConsumptionCost[] consumptionCost;

  /**
   * Constructor for the SalesTariffEntry class
   *
   * @param relativeTimeInterval Relative Timer Interval
   */
  public SalesTariffEntry(RelativeTimeInterval relativeTimeInterval) {
    setRelativeTimeInterval(relativeTimeInterval);
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

  /**
   * Gets relative Timer Interval
   *
   * @return Relative Timer Interval
   */
  public RelativeTimeInterval getRelativeTimeInterval() {
    return relativeTimeInterval;
  }

  /**
   * Sets relative Timer Interval
   *
   * @param relativeTimeInterval Relative Timer Interval
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
   * Gets consumption Cost
   *
   * @return Consumption Cost
   */
  @Nullable
  public ConsumptionCost[] getConsumptionCost() {
    return consumptionCost;
  }

  /**
   * Sets consumption Cost
   *
   * @param consumptionCost Consumption Cost
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
   * Adds consumption Cost
   *
   * @param consumptionCost Consumption Cost
   * @return this
   */
  public SalesTariffEntry withConsumptionCost(@Nullable ConsumptionCost[] consumptionCost) {
    setConsumptionCost(consumptionCost);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidRelativeTimeInterval(relativeTimeInterval)
        && isValidEPriceLevel(ePriceLevel)
        && isValidConsumptionCost(consumptionCost);
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
    return Objects.equals(customData, that.customData)
        && Objects.equals(relativeTimeInterval, that.relativeTimeInterval)
        && Objects.equals(ePriceLevel, that.ePriceLevel)
        && Arrays.equals(consumptionCost, that.consumptionCost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, relativeTimeInterval, ePriceLevel, Arrays.hashCode(consumptionCost));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("relativeTimeInterval", relativeTimeInterval)
        .add("ePriceLevel", ePriceLevel)
        .add("consumptionCost", consumptionCost)
        .add("isValid", validate())
        .toString();
  }
}
