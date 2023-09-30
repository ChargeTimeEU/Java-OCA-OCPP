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
import java.util.Objects;
import javax.annotation.Nullable;

/** Cost */
public final class Cost {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Cost. Cost Kind. Cost Kind Code
   *
   * <p>The kind of cost referred to in the message element amount
   */
  private CostKindEnum costKind;

  /**
   * Cost. Amount. Amount
   *
   * <p>The estimated or actual cost per kWh
   */
  private Integer amount;

  /**
   * Cost. Amount Multiplier. Integer
   *
   * <p>Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value
   * is determined by: amount * 10 ^ amountMultiplier
   */
  @Nullable private Integer amountMultiplier;

  /**
   * Constructor for the Cost class
   *
   * @param costKind The kind of cost referred to in the message element amount
   * @param amount The estimated or actual cost per kWh
   */
  public Cost(CostKindEnum costKind, Integer amount) {
    setCostKind(costKind);
    setAmount(amount);
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
  public Cost withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the kind of cost referred to in the message element amount
   *
   * @return The kind of cost referred to in the message element amount
   */
  public CostKindEnum getCostKind() {
    return costKind;
  }

  /**
   * Sets the kind of cost referred to in the message element amount
   *
   * @param costKind The kind of cost referred to in the message element amount
   */
  public void setCostKind(CostKindEnum costKind) {
    if (!isValidCostKind(costKind)) {
      throw new PropertyConstraintException(costKind, "costKind is invalid");
    }
    this.costKind = costKind;
  }

  /**
   * Returns whether the given costKind is valid
   *
   * @param costKind the costKind to check the validity of
   * @return {@code true} if costKind is valid, {@code false} if not
   */
  private boolean isValidCostKind(CostKindEnum costKind) {
    return costKind != null;
  }

  /**
   * Gets the estimated or actual cost per kWh
   *
   * @return The estimated or actual cost per kWh
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * Sets the estimated or actual cost per kWh
   *
   * @param amount The estimated or actual cost per kWh
   */
  public void setAmount(Integer amount) {
    if (!isValidAmount(amount)) {
      throw new PropertyConstraintException(amount, "amount is invalid");
    }
    this.amount = amount;
  }

  /**
   * Returns whether the given amount is valid
   *
   * @param amount the amount to check the validity of
   * @return {@code true} if amount is valid, {@code false} if not
   */
  private boolean isValidAmount(Integer amount) {
    return amount != null;
  }

  /**
   * Gets values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value
   * is determined by: amount * 10 ^ amountMultiplier
   *
   * @return Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec)
   */
  @Nullable
  public Integer getAmountMultiplier() {
    return amountMultiplier;
  }

  /**
   * Sets values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value
   * is determined by: amount * 10 ^ amountMultiplier
   *
   * @param amountMultiplier Values: -3..3, The amountMultiplier defines the exponent to base 10
   *     (dec)
   */
  public void setAmountMultiplier(@Nullable Integer amountMultiplier) {
    this.amountMultiplier = amountMultiplier;
  }

  /**
   * Adds values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value
   * is determined by: amount * 10 ^ amountMultiplier
   *
   * @param amountMultiplier Values: -3..3, The amountMultiplier defines the exponent to base 10
   *     (dec)
   * @return this
   */
  public Cost withAmountMultiplier(@Nullable Integer amountMultiplier) {
    setAmountMultiplier(amountMultiplier);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData) && isValidCostKind(costKind) && isValidAmount(amount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cost that = (Cost) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(costKind, that.costKind)
        && Objects.equals(amount, that.amount)
        && Objects.equals(amountMultiplier, that.amountMultiplier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, costKind, amount, amountMultiplier);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("costKind", costKind)
        .add("amount", amount)
        .add("amountMultiplier", amountMultiplier)
        .add("isValid", validate())
        .toString();
  }
}
