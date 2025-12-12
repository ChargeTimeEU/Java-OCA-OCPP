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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * A ChargingPeriodType consists of a start time, and a list of possible values that influence this
 * period, for example: amount of energy charged this period, maximum current during this period
 * etc.
 */
public final class ChargingPeriod {
  /** Volume consumed of cost dimension. */
  @Nullable private CostDimension[] dimensions;

  /**
   * Unique identifier of the Tariff that was used to calculate cost. If not provided, then cost was
   * calculated by some other means.
   */
  @Nullable private String tariffId;

  /**
   * Start timestamp of charging period. A period ends when the next period starts. The last period
   * ends when the session ends.
   */
  private ZonedDateTime startPeriod;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ChargingPeriod class
   *
   * @param startPeriod Start timestamp of charging period. A period ends when the next period
   *     starts. The last period ends when the session ends.
   */
  public ChargingPeriod(ZonedDateTime startPeriod) {
    setStartPeriod(startPeriod);
  }

  /**
   * Gets volume consumed of cost dimension.
   *
   * @return Volume consumed of cost dimension
   */
  @Nullable
  public CostDimension[] getDimensions() {
    return dimensions;
  }

  /**
   * Sets volume consumed of cost dimension.
   *
   * @param dimensions Volume consumed of cost dimension
   */
  public void setDimensions(@Nullable CostDimension[] dimensions) {
    if (!isValidDimensions(dimensions)) {
      throw new PropertyConstraintException(dimensions, "dimensions is invalid");
    }
    this.dimensions = dimensions;
  }

  /**
   * Returns whether the given dimensions is valid
   *
   * @param dimensions the dimensions to check the validity of
   * @return {@code true} if dimensions is valid, {@code false} if not
   */
  private boolean isValidDimensions(@Nullable CostDimension[] dimensions) {
    return dimensions == null
        || (dimensions.length >= 1 && Arrays.stream(dimensions).allMatch(item -> item.validate()));
  }

  /**
   * Adds volume consumed of cost dimension.
   *
   * @param dimensions Volume consumed of cost dimension
   * @return this
   */
  public ChargingPeriod withDimensions(@Nullable CostDimension[] dimensions) {
    setDimensions(dimensions);
    return this;
  }

  /**
   * Gets unique identifier of the Tariff that was used to calculate cost. If not provided, then
   * cost was calculated by some other means.
   *
   * @return Unique identifier of the Tariff that was used to calculate cost
   */
  @Nullable
  public String getTariffId() {
    return tariffId;
  }

  /**
   * Sets unique identifier of the Tariff that was used to calculate cost. If not provided, then
   * cost was calculated by some other means.
   *
   * @param tariffId Unique identifier of the Tariff that was used to calculate cost
   */
  public void setTariffId(@Nullable String tariffId) {
    if (!isValidTariffId(tariffId)) {
      throw new PropertyConstraintException(tariffId, "tariffId is invalid");
    }
    this.tariffId = tariffId;
  }

  /**
   * Returns whether the given tariffId is valid
   *
   * @param tariffId the tariffId to check the validity of
   * @return {@code true} if tariffId is valid, {@code false} if not
   */
  private boolean isValidTariffId(@Nullable String tariffId) {
    return tariffId == null || tariffId.length() <= 60;
  }

  /**
   * Adds unique identifier of the Tariff that was used to calculate cost. If not provided, then
   * cost was calculated by some other means.
   *
   * @param tariffId Unique identifier of the Tariff that was used to calculate cost
   * @return this
   */
  public ChargingPeriod withTariffId(@Nullable String tariffId) {
    setTariffId(tariffId);
    return this;
  }

  /**
   * Gets start timestamp of charging period. A period ends when the next period starts. The last
   * period ends when the session ends.
   *
   * @return Start timestamp of charging period
   */
  public ZonedDateTime getStartPeriod() {
    return startPeriod;
  }

  /**
   * Sets start timestamp of charging period. A period ends when the next period starts. The last
   * period ends when the session ends.
   *
   * @param startPeriod Start timestamp of charging period
   */
  public void setStartPeriod(ZonedDateTime startPeriod) {
    if (!isValidStartPeriod(startPeriod)) {
      throw new PropertyConstraintException(startPeriod, "startPeriod is invalid");
    }
    this.startPeriod = startPeriod;
  }

  /**
   * Returns whether the given startPeriod is valid
   *
   * @param startPeriod the startPeriod to check the validity of
   * @return {@code true} if startPeriod is valid, {@code false} if not
   */
  private boolean isValidStartPeriod(ZonedDateTime startPeriod) {
    return startPeriod != null;
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
  public ChargingPeriod withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidDimensions(dimensions)
        && isValidTariffId(tariffId)
        && isValidStartPeriod(startPeriod)
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
    ChargingPeriod that = (ChargingPeriod) o;
    return Arrays.equals(dimensions, that.dimensions)
        && Objects.equals(tariffId, that.tariffId)
        && Objects.equals(startPeriod, that.startPeriod)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(dimensions), tariffId, startPeriod, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("dimensions", dimensions)
        .add("tariffId", tariffId)
        .add("startPeriod", startPeriod)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
