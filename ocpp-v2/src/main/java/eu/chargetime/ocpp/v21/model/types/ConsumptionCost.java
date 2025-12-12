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

/** ConsumptionCostType */
public final class ConsumptionCost {
  /**
   * The lowest level of consumption that defines the starting point of this consumption block. The
   * block interval extends to the start of the next interval.
   */
  private Double startValue;

  /** cost */
  private Cost[] cost;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ConsumptionCost class
   *
   * @param startValue The lowest level of consumption that defines the starting point of this
   *     consumption block. The block interval extends to the start of the next interval.
   * @param cost cost
   */
  public ConsumptionCost(Double startValue, Cost[] cost) {
    setStartValue(startValue);
    setCost(cost);
  }

  /**
   * Gets the lowest level of consumption that defines the starting point of this consumption block.
   * The block interval extends to the start of the next interval.
   *
   * @return The lowest level of consumption that defines the starting point of this consumption
   *     block
   */
  public Double getStartValue() {
    return startValue;
  }

  /**
   * Sets the lowest level of consumption that defines the starting point of this consumption block.
   * The block interval extends to the start of the next interval.
   *
   * @param startValue The lowest level of consumption that defines the starting point of this
   *     consumption block
   */
  public void setStartValue(Double startValue) {
    if (!isValidStartValue(startValue)) {
      throw new PropertyConstraintException(startValue, "startValue is invalid");
    }
    this.startValue = startValue;
  }

  /**
   * Returns whether the given startValue is valid
   *
   * @param startValue the startValue to check the validity of
   * @return {@code true} if startValue is valid, {@code false} if not
   */
  private boolean isValidStartValue(Double startValue) {
    return startValue != null;
  }

  /**
   * Gets cost
   *
   * @return cost
   */
  public Cost[] getCost() {
    return cost;
  }

  /**
   * Sets cost
   *
   * @param cost cost
   */
  public void setCost(Cost[] cost) {
    if (!isValidCost(cost)) {
      throw new PropertyConstraintException(cost, "cost is invalid");
    }
    this.cost = cost;
  }

  /**
   * Returns whether the given cost is valid
   *
   * @param cost the cost to check the validity of
   * @return {@code true} if cost is valid, {@code false} if not
   */
  private boolean isValidCost(Cost[] cost) {
    return cost != null
        && cost.length >= 1
        && cost.length <= 3
        && Arrays.stream(cost).allMatch(item -> item.validate());
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
  public ConsumptionCost withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidStartValue(startValue) && isValidCost(cost) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsumptionCost that = (ConsumptionCost) o;
    return Objects.equals(startValue, that.startValue)
        && Arrays.equals(cost, that.cost)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startValue, Arrays.hashCode(cost), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("startValue", startValue)
        .add("cost", cost)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
