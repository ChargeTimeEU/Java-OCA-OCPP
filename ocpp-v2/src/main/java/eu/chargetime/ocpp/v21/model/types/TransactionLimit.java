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

/** Cost, energy, time or SoC limit for a transaction. */
public final class TransactionLimit {
  /** Maximum allowed cost of transaction in currency of tariff. */
  @Nullable private Double maxCost;

  /** Maximum allowed energy in Wh to charge in transaction. */
  @Nullable private Double maxEnergy;

  /** Maximum duration of transaction in seconds from start to end. */
  @Nullable private Integer maxTime;

  /** Maximum State of Charge of EV in percentage. */
  @Nullable private Integer maxSoC;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the TransactionLimit class */
  public TransactionLimit() {}

  /**
   * Gets maximum allowed cost of transaction in currency of tariff.
   *
   * @return Maximum allowed cost of transaction in currency of tariff
   */
  @Nullable
  public Double getMaxCost() {
    return maxCost;
  }

  /**
   * Sets maximum allowed cost of transaction in currency of tariff.
   *
   * @param maxCost Maximum allowed cost of transaction in currency of tariff
   */
  public void setMaxCost(@Nullable Double maxCost) {
    this.maxCost = maxCost;
  }

  /**
   * Adds maximum allowed cost of transaction in currency of tariff.
   *
   * @param maxCost Maximum allowed cost of transaction in currency of tariff
   * @return this
   */
  public TransactionLimit withMaxCost(@Nullable Double maxCost) {
    setMaxCost(maxCost);
    return this;
  }

  /**
   * Gets maximum allowed energy in Wh to charge in transaction.
   *
   * @return Maximum allowed energy in Wh to charge in transaction
   */
  @Nullable
  public Double getMaxEnergy() {
    return maxEnergy;
  }

  /**
   * Sets maximum allowed energy in Wh to charge in transaction.
   *
   * @param maxEnergy Maximum allowed energy in Wh to charge in transaction
   */
  public void setMaxEnergy(@Nullable Double maxEnergy) {
    this.maxEnergy = maxEnergy;
  }

  /**
   * Adds maximum allowed energy in Wh to charge in transaction.
   *
   * @param maxEnergy Maximum allowed energy in Wh to charge in transaction
   * @return this
   */
  public TransactionLimit withMaxEnergy(@Nullable Double maxEnergy) {
    setMaxEnergy(maxEnergy);
    return this;
  }

  /**
   * Gets maximum duration of transaction in seconds from start to end.
   *
   * @return Maximum duration of transaction in seconds from start to end
   */
  @Nullable
  public Integer getMaxTime() {
    return maxTime;
  }

  /**
   * Sets maximum duration of transaction in seconds from start to end.
   *
   * @param maxTime Maximum duration of transaction in seconds from start to end
   */
  public void setMaxTime(@Nullable Integer maxTime) {
    this.maxTime = maxTime;
  }

  /**
   * Adds maximum duration of transaction in seconds from start to end.
   *
   * @param maxTime Maximum duration of transaction in seconds from start to end
   * @return this
   */
  public TransactionLimit withMaxTime(@Nullable Integer maxTime) {
    setMaxTime(maxTime);
    return this;
  }

  /**
   * Gets maximum State of Charge of EV in percentage.
   *
   * @return Maximum State of Charge of EV in percentage
   */
  @Nullable
  public Integer getMaxSoC() {
    return maxSoC;
  }

  /**
   * Sets maximum State of Charge of EV in percentage.
   *
   * @param maxSoC Maximum State of Charge of EV in percentage
   */
  public void setMaxSoC(@Nullable Integer maxSoC) {
    if (!isValidMaxSoC(maxSoC)) {
      throw new PropertyConstraintException(maxSoC, "maxSoC is invalid");
    }
    this.maxSoC = maxSoC;
  }

  /**
   * Returns whether the given maxSoC is valid
   *
   * @param maxSoC the maxSoC to check the validity of
   * @return {@code true} if maxSoC is valid, {@code false} if not
   */
  private boolean isValidMaxSoC(@Nullable Integer maxSoC) {
    return maxSoC == null || (maxSoC >= 0 && maxSoC <= 100);
  }

  /**
   * Adds maximum State of Charge of EV in percentage.
   *
   * @param maxSoC Maximum State of Charge of EV in percentage
   * @return this
   */
  public TransactionLimit withMaxSoC(@Nullable Integer maxSoC) {
    setMaxSoC(maxSoC);
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
  public TransactionLimit withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidMaxSoC(maxSoC) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionLimit that = (TransactionLimit) o;
    return Objects.equals(maxCost, that.maxCost)
        && Objects.equals(maxEnergy, that.maxEnergy)
        && Objects.equals(maxTime, that.maxTime)
        && Objects.equals(maxSoC, that.maxSoC)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxCost, maxEnergy, maxTime, maxSoC, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("maxCost", maxCost)
        .add("maxEnergy", maxEnergy)
        .add("maxTime", maxTime)
        .add("maxSoC", maxSoC)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
