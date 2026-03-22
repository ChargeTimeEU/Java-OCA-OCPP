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

/** PeriodicEventStreamParamsType */
public final class PeriodicEventStreamParams {
  /** Time in seconds after which stream data is sent. */
  @Nullable private Integer interval;

  /** Number of items to be sent together in stream. */
  @Nullable private Integer values;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the PeriodicEventStreamParams class */
  public PeriodicEventStreamParams() {}

  /**
   * Gets time in seconds after which stream data is sent.
   *
   * @return Time in seconds after which stream data is sent
   */
  @Nullable
  public Integer getInterval() {
    return interval;
  }

  /**
   * Sets time in seconds after which stream data is sent.
   *
   * @param interval Time in seconds after which stream data is sent
   */
  public void setInterval(@Nullable Integer interval) {
    if (!isValidInterval(interval)) {
      throw new PropertyConstraintException(interval, "interval is invalid");
    }
    this.interval = interval;
  }

  /**
   * Returns whether the given interval is valid
   *
   * @param interval the interval to check the validity of
   * @return {@code true} if interval is valid, {@code false} if not
   */
  private boolean isValidInterval(@Nullable Integer interval) {
    return interval == null || (interval >= 0);
  }

  /**
   * Adds time in seconds after which stream data is sent.
   *
   * @param interval Time in seconds after which stream data is sent
   * @return this
   */
  public PeriodicEventStreamParams withInterval(@Nullable Integer interval) {
    setInterval(interval);
    return this;
  }

  /**
   * Gets number of items to be sent together in stream.
   *
   * @return Number of items to be sent together in stream
   */
  @Nullable
  public Integer getValues() {
    return values;
  }

  /**
   * Sets number of items to be sent together in stream.
   *
   * @param values Number of items to be sent together in stream
   */
  public void setValues(@Nullable Integer values) {
    if (!isValidValues(values)) {
      throw new PropertyConstraintException(values, "values is invalid");
    }
    this.values = values;
  }

  /**
   * Returns whether the given values is valid
   *
   * @param values the values to check the validity of
   * @return {@code true} if values is valid, {@code false} if not
   */
  private boolean isValidValues(@Nullable Integer values) {
    return values == null || (values >= 0);
  }

  /**
   * Adds number of items to be sent together in stream.
   *
   * @param values Number of items to be sent together in stream
   * @return this
   */
  public PeriodicEventStreamParams withValues(@Nullable Integer values) {
    setValues(values);
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
  public PeriodicEventStreamParams withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidInterval(interval) && isValidValues(values) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeriodicEventStreamParams that = (PeriodicEventStreamParams) o;
    return Objects.equals(interval, that.interval)
        && Objects.equals(values, that.values)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(interval, values, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("interval", interval)
        .add("values", values)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
