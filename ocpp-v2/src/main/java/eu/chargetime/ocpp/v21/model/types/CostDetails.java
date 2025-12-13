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

/**
 * CostDetailsType contains the cost as calculated by Charging Station based on provided TariffType.
 *
 * <p>NOTE: Reservation is not shown as a chargingPeriod, because it took place outside of the
 * transaction.
 */
public final class CostDetails {
  /**
   * A ChargingPeriodType consists of a start time, and a list of possible values that influence
   * this period, for example: amount of energy charged this period, maximum current during this
   * period etc.
   */
  @Nullable private ChargingPeriod[] chargingPeriods;

  /**
   * The cost calculated during a transaction. It is used both for running cost and final cost of
   * the transaction.
   */
  private TotalCost totalCost;

  /** The calculated usage of energy, charging time and idle time during a transaction. */
  private TotalUsage totalUsage;

  /** If set to true, then Charging Station has failed to calculate the cost. */
  @Nullable private Boolean failureToCalculate;

  /** Optional human-readable reason text in case of failure to calculate. */
  @Nullable private String failureReason;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the CostDetails class
   *
   * @param totalCost The cost calculated during a transaction. It is used both for running cost and
   *     final cost of the transaction.
   * @param totalUsage The calculated usage of energy, charging time and idle time during a
   *     transaction.
   */
  public CostDetails(TotalCost totalCost, TotalUsage totalUsage) {
    setTotalCost(totalCost);
    setTotalUsage(totalUsage);
  }

  /**
   * Gets a ChargingPeriodType consists of a start time, and a list of possible values that
   * influence this period, for example: amount of energy charged this period, maximum current
   * during this period etc.
   *
   * @return A ChargingPeriodType consists of a start time, and a list of possible values that
   *     influence this period, for example: amount of energy charged this period, maximum current
   *     during this period etc
   */
  @Nullable
  public ChargingPeriod[] getChargingPeriods() {
    return chargingPeriods;
  }

  /**
   * Sets a ChargingPeriodType consists of a start time, and a list of possible values that
   * influence this period, for example: amount of energy charged this period, maximum current
   * during this period etc.
   *
   * @param chargingPeriods A ChargingPeriodType consists of a start time, and a list of possible
   *     values that influence this period, for example: amount of energy charged this period,
   *     maximum current during this period etc
   */
  public void setChargingPeriods(@Nullable ChargingPeriod[] chargingPeriods) {
    if (!isValidChargingPeriods(chargingPeriods)) {
      throw new PropertyConstraintException(chargingPeriods, "chargingPeriods is invalid");
    }
    this.chargingPeriods = chargingPeriods;
  }

  /**
   * Returns whether the given chargingPeriods is valid
   *
   * @param chargingPeriods the chargingPeriods to check the validity of
   * @return {@code true} if chargingPeriods is valid, {@code false} if not
   */
  private boolean isValidChargingPeriods(@Nullable ChargingPeriod[] chargingPeriods) {
    return chargingPeriods == null
        || (chargingPeriods.length >= 1
            && Arrays.stream(chargingPeriods).allMatch(item -> item.validate()));
  }

  /**
   * Adds a ChargingPeriodType consists of a start time, and a list of possible values that
   * influence this period, for example: amount of energy charged this period, maximum current
   * during this period etc.
   *
   * @param chargingPeriods A ChargingPeriodType consists of a start time, and a list of possible
   *     values that influence this period, for example: amount of energy charged this period,
   *     maximum current during this period etc
   * @return this
   */
  public CostDetails withChargingPeriods(@Nullable ChargingPeriod[] chargingPeriods) {
    setChargingPeriods(chargingPeriods);
    return this;
  }

  /**
   * Gets the cost calculated during a transaction. It is used both for running cost and final cost
   * of the transaction.
   *
   * @return The cost calculated during a transaction
   */
  public TotalCost getTotalCost() {
    return totalCost;
  }

  /**
   * Sets the cost calculated during a transaction. It is used both for running cost and final cost
   * of the transaction.
   *
   * @param totalCost The cost calculated during a transaction
   */
  public void setTotalCost(TotalCost totalCost) {
    if (!isValidTotalCost(totalCost)) {
      throw new PropertyConstraintException(totalCost, "totalCost is invalid");
    }
    this.totalCost = totalCost;
  }

  /**
   * Returns whether the given totalCost is valid
   *
   * @param totalCost the totalCost to check the validity of
   * @return {@code true} if totalCost is valid, {@code false} if not
   */
  private boolean isValidTotalCost(TotalCost totalCost) {
    return totalCost != null && totalCost.validate();
  }

  /**
   * Gets the calculated usage of energy, charging time and idle time during a transaction.
   *
   * @return The calculated usage of energy, charging time and idle time during a transaction
   */
  public TotalUsage getTotalUsage() {
    return totalUsage;
  }

  /**
   * Sets the calculated usage of energy, charging time and idle time during a transaction.
   *
   * @param totalUsage The calculated usage of energy, charging time and idle time during a
   *     transaction
   */
  public void setTotalUsage(TotalUsage totalUsage) {
    if (!isValidTotalUsage(totalUsage)) {
      throw new PropertyConstraintException(totalUsage, "totalUsage is invalid");
    }
    this.totalUsage = totalUsage;
  }

  /**
   * Returns whether the given totalUsage is valid
   *
   * @param totalUsage the totalUsage to check the validity of
   * @return {@code true} if totalUsage is valid, {@code false} if not
   */
  private boolean isValidTotalUsage(TotalUsage totalUsage) {
    return totalUsage != null && totalUsage.validate();
  }

  /**
   * Gets if set to true, then Charging Station has failed to calculate the cost.
   *
   * @return If set to true, then Charging Station has failed to calculate the cost
   */
  @Nullable
  public Boolean getFailureToCalculate() {
    return failureToCalculate;
  }

  /**
   * Sets if set to true, then Charging Station has failed to calculate the cost.
   *
   * @param failureToCalculate If set to true, then Charging Station has failed to calculate the
   *     cost
   */
  public void setFailureToCalculate(@Nullable Boolean failureToCalculate) {
    this.failureToCalculate = failureToCalculate;
  }

  /**
   * Adds if set to true, then Charging Station has failed to calculate the cost.
   *
   * @param failureToCalculate If set to true, then Charging Station has failed to calculate the
   *     cost
   * @return this
   */
  public CostDetails withFailureToCalculate(@Nullable Boolean failureToCalculate) {
    setFailureToCalculate(failureToCalculate);
    return this;
  }

  /**
   * Gets optional human-readable reason text in case of failure to calculate.
   *
   * @return Optional human-readable reason text in case of failure to calculate
   */
  @Nullable
  public String getFailureReason() {
    return failureReason;
  }

  /**
   * Sets optional human-readable reason text in case of failure to calculate.
   *
   * @param failureReason Optional human-readable reason text in case of failure to calculate
   */
  public void setFailureReason(@Nullable String failureReason) {
    if (!isValidFailureReason(failureReason)) {
      throw new PropertyConstraintException(failureReason, "failureReason is invalid");
    }
    this.failureReason = failureReason;
  }

  /**
   * Returns whether the given failureReason is valid
   *
   * @param failureReason the failureReason to check the validity of
   * @return {@code true} if failureReason is valid, {@code false} if not
   */
  private boolean isValidFailureReason(@Nullable String failureReason) {
    return failureReason == null || failureReason.length() <= 500;
  }

  /**
   * Adds optional human-readable reason text in case of failure to calculate.
   *
   * @param failureReason Optional human-readable reason text in case of failure to calculate
   * @return this
   */
  public CostDetails withFailureReason(@Nullable String failureReason) {
    setFailureReason(failureReason);
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
  public CostDetails withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidChargingPeriods(chargingPeriods)
        && isValidTotalCost(totalCost)
        && isValidTotalUsage(totalUsage)
        && isValidFailureReason(failureReason)
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
    CostDetails that = (CostDetails) o;
    return Arrays.equals(chargingPeriods, that.chargingPeriods)
        && Objects.equals(totalCost, that.totalCost)
        && Objects.equals(totalUsage, that.totalUsage)
        && Objects.equals(failureToCalculate, that.failureToCalculate)
        && Objects.equals(failureReason, that.failureReason)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        Arrays.hashCode(chargingPeriods),
        totalCost,
        totalUsage,
        failureToCalculate,
        failureReason,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("chargingPeriods", chargingPeriods)
        .add("totalCost", totalCost)
        .add("totalUsage", totalUsage)
        .add("failureToCalculate", failureToCalculate)
        .add("failureReason", failureReason)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
