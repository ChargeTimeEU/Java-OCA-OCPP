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

/** Price with and without tax. At least one of exclTax, inclTax must be present. */
public final class Price {
  /** Price/cost excluding tax. Can be absent if inclTax is present. */
  @Nullable private Double exclTax;

  /** Price/cost including tax. Can be absent if exclTax is present. */
  @Nullable private Double inclTax;

  /** Tax percentage */
  @Nullable private TaxRate[] taxRates;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the Price class */
  public Price() {}

  /**
   * Gets price/cost excluding tax. Can be absent if inclTax is present.
   *
   * @return Price/cost excluding tax
   */
  @Nullable
  public Double getExclTax() {
    return exclTax;
  }

  /**
   * Sets price/cost excluding tax. Can be absent if inclTax is present.
   *
   * @param exclTax Price/cost excluding tax
   */
  public void setExclTax(@Nullable Double exclTax) {
    this.exclTax = exclTax;
  }

  /**
   * Adds price/cost excluding tax. Can be absent if inclTax is present.
   *
   * @param exclTax Price/cost excluding tax
   * @return this
   */
  public Price withExclTax(@Nullable Double exclTax) {
    setExclTax(exclTax);
    return this;
  }

  /**
   * Gets price/cost including tax. Can be absent if exclTax is present.
   *
   * @return Price/cost including tax
   */
  @Nullable
  public Double getInclTax() {
    return inclTax;
  }

  /**
   * Sets price/cost including tax. Can be absent if exclTax is present.
   *
   * @param inclTax Price/cost including tax
   */
  public void setInclTax(@Nullable Double inclTax) {
    this.inclTax = inclTax;
  }

  /**
   * Adds price/cost including tax. Can be absent if exclTax is present.
   *
   * @param inclTax Price/cost including tax
   * @return this
   */
  public Price withInclTax(@Nullable Double inclTax) {
    setInclTax(inclTax);
    return this;
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
  public Price withTaxRates(@Nullable TaxRate[] taxRates) {
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
  public Price withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidTaxRates(taxRates) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Price that = (Price) o;
    return Objects.equals(exclTax, that.exclTax)
        && Objects.equals(inclTax, that.inclTax)
        && Arrays.equals(taxRates, that.taxRates)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exclTax, inclTax, Arrays.hashCode(taxRates), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("exclTax", exclTax)
        .add("inclTax", inclTax)
        .add("taxRates", taxRates)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
