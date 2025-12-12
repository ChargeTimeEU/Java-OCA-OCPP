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

/** Tariff with optional conditions for a fixed price. */
public final class TariffFixedPrice {
  /**
   * These conditions describe if a FixedPrice applies at start of the transaction.
   *
   * <p>When more than one restriction is set, they are to be treated as a logical AND. All need to
   * be valid before this price is active.
   *
   * <p>NOTE: startTimeOfDay and endTimeOfDay are in local time, because it is the time in the
   * tariff as it is shown to the EV driver at the Charging Station. A Charging Station will convert
   * this to the internal time zone that it uses (which is recommended to be UTC, see section
   * Generic chapter 3.1) when performing cost calculation.
   */
  @Nullable private TariffConditionsFixed conditions;

  /** Fixed price for this element e.g. a start fee. */
  private Double priceFixed;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TariffFixedPrice class
   *
   * @param priceFixed Fixed price for this element e.g. a start fee.
   */
  public TariffFixedPrice(Double priceFixed) {
    setPriceFixed(priceFixed);
  }

  /**
   * Gets these conditions describe if a FixedPrice applies at start of the transaction.
   *
   * @return These conditions describe if a FixedPrice applies at start of the transaction
   */
  @Nullable
  public TariffConditionsFixed getConditions() {
    return conditions;
  }

  /**
   * Sets these conditions describe if a FixedPrice applies at start of the transaction.
   *
   * @param conditions These conditions describe if a FixedPrice applies at start of the transaction
   */
  public void setConditions(@Nullable TariffConditionsFixed conditions) {
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
  private boolean isValidConditions(@Nullable TariffConditionsFixed conditions) {
    return conditions == null || conditions.validate();
  }

  /**
   * Adds these conditions describe if a FixedPrice applies at start of the transaction.
   *
   * @param conditions These conditions describe if a FixedPrice applies at start of the transaction
   * @return this
   */
  public TariffFixedPrice withConditions(@Nullable TariffConditionsFixed conditions) {
    setConditions(conditions);
    return this;
  }

  /**
   * Gets fixed price for this element e.g. a start fee.
   *
   * @return Fixed price for this element e.g. a start fee
   */
  public Double getPriceFixed() {
    return priceFixed;
  }

  /**
   * Sets fixed price for this element e.g. a start fee.
   *
   * @param priceFixed Fixed price for this element e.g. a start fee
   */
  public void setPriceFixed(Double priceFixed) {
    if (!isValidPriceFixed(priceFixed)) {
      throw new PropertyConstraintException(priceFixed, "priceFixed is invalid");
    }
    this.priceFixed = priceFixed;
  }

  /**
   * Returns whether the given priceFixed is valid
   *
   * @param priceFixed the priceFixed to check the validity of
   * @return {@code true} if priceFixed is valid, {@code false} if not
   */
  private boolean isValidPriceFixed(Double priceFixed) {
    return priceFixed != null;
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
  public TariffFixedPrice withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidConditions(conditions)
        && isValidPriceFixed(priceFixed)
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
    TariffFixedPrice that = (TariffFixedPrice) o;
    return Objects.equals(conditions, that.conditions)
        && Objects.equals(priceFixed, that.priceFixed)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conditions, priceFixed, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("conditions", conditions)
        .add("priceFixed", priceFixed)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
