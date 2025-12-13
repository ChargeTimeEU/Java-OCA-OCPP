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

/** Price elements and tax for time */
public final class TariffTime {
  /** Tariff with optional conditions for a time duration price. */
  private TariffTimePrice[] prices;

  /** Tax percentage */
  @Nullable private TaxRate[] taxRates;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TariffTime class
   *
   * @param prices Tariff with optional conditions for a time duration price.
   */
  public TariffTime(TariffTimePrice[] prices) {
    setPrices(prices);
  }

  /**
   * Gets tariff with optional conditions for a time duration price.
   *
   * @return Tariff with optional conditions for a time duration price
   */
  public TariffTimePrice[] getPrices() {
    return prices;
  }

  /**
   * Sets tariff with optional conditions for a time duration price.
   *
   * @param prices Tariff with optional conditions for a time duration price
   */
  public void setPrices(TariffTimePrice[] prices) {
    if (!isValidPrices(prices)) {
      throw new PropertyConstraintException(prices, "prices is invalid");
    }
    this.prices = prices;
  }

  /**
   * Returns whether the given prices is valid
   *
   * @param prices the prices to check the validity of
   * @return {@code true} if prices is valid, {@code false} if not
   */
  private boolean isValidPrices(TariffTimePrice[] prices) {
    return prices != null
        && prices.length >= 1
        && Arrays.stream(prices).allMatch(item -> item.validate());
  }

  /**
   * Gets tax percentage
   *
   * @return Tax percentage
   */
  @Nullable
  public TaxRate[] getTaxRates() {
    return taxRates;
  }

  /**
   * Sets tax percentage
   *
   * @param taxRates Tax percentage
   */
  public void setTaxRates(@Nullable TaxRate[] taxRates) {
    if (!isValidTaxRates(taxRates)) {
      throw new PropertyConstraintException(taxRates, "taxRates is invalid");
    }
    this.taxRates = taxRates;
  }

  /**
   * Returns whether the given taxRates is valid
   *
   * @param taxRates the taxRates to check the validity of
   * @return {@code true} if taxRates is valid, {@code false} if not
   */
  private boolean isValidTaxRates(@Nullable TaxRate[] taxRates) {
    return taxRates == null
        || (taxRates.length >= 1
            && taxRates.length <= 5
            && Arrays.stream(taxRates).allMatch(item -> item.validate()));
  }

  /**
   * Adds tax percentage
   *
   * @param taxRates Tax percentage
   * @return this
   */
  public TariffTime withTaxRates(@Nullable TaxRate[] taxRates) {
    setTaxRates(taxRates);
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
  public TariffTime withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidPrices(prices) && isValidTaxRates(taxRates) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TariffTime that = (TariffTime) o;
    return Arrays.equals(prices, that.prices)
        && Arrays.equals(taxRates, that.taxRates)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(prices), Arrays.hashCode(taxRates), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("prices", prices)
        .add("taxRates", taxRates)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
