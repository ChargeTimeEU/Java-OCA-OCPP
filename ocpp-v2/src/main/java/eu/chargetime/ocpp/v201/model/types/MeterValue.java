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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Meter Value
 *
 * <p>Collection of one or more sampled values in MeterValuesRequest and TransactionEvent. All
 * sampled values in a MeterValue are sampled at the same point in time.
 */
public final class MeterValue {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Sampled Value
   *
   * <p>Single sampled value in MeterValues. Each value can be accompanied by optional fields.
   *
   * <p>To save on mobile data usage, default values of all of the optional fields are such that.
   * The value without any additional fields will be interpreted, as a register reading of active
   * import energy in Wh (Watt-hour) units.
   */
  private SampledValue[] sampledValue;

  /**
   * Meter Value. Timestamp. Date Time
   *
   * <p>Timestamp for measured value(s).
   */
  private ZonedDateTime timestamp;

  /**
   * Constructor for the MeterValue class
   *
   * @param sampledValue Single sampled value in MeterValues. Each value can be accompanied by
   *     optional fields.
   * @param timestamp Timestamp for measured value(s).
   */
  public MeterValue(SampledValue[] sampledValue, ZonedDateTime timestamp) {
    setSampledValue(sampledValue);
    setTimestamp(timestamp);
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
  public MeterValue withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets single sampled value in MeterValues. Each value can be accompanied by optional fields.
   *
   * @return Single sampled value in MeterValues
   */
  public SampledValue[] getSampledValue() {
    return sampledValue;
  }

  /**
   * Sets single sampled value in MeterValues. Each value can be accompanied by optional fields.
   *
   * @param sampledValue Single sampled value in MeterValues
   */
  public void setSampledValue(SampledValue[] sampledValue) {
    if (!isValidSampledValue(sampledValue)) {
      throw new PropertyConstraintException(sampledValue, "sampledValue is invalid");
    }
    this.sampledValue = sampledValue;
  }

  /**
   * Returns whether the given sampledValue is valid
   *
   * @param sampledValue the sampledValue to check the validity of
   * @return {@code true} if sampledValue is valid, {@code false} if not
   */
  private boolean isValidSampledValue(SampledValue[] sampledValue) {
    return sampledValue != null
        && sampledValue.length >= 1
        && Arrays.stream(sampledValue).allMatch(item -> item.validate());
  }

  /**
   * Gets timestamp for measured value(s).
   *
   * @return Timestamp for measured value(s)
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets timestamp for measured value(s).
   *
   * @param timestamp Timestamp for measured value(s)
   */
  public void setTimestamp(ZonedDateTime timestamp) {
    if (!isValidTimestamp(timestamp)) {
      throw new PropertyConstraintException(timestamp, "timestamp is invalid");
    }
    this.timestamp = timestamp;
  }

  /**
   * Returns whether the given timestamp is valid
   *
   * @param timestamp the timestamp to check the validity of
   * @return {@code true} if timestamp is valid, {@code false} if not
   */
  private boolean isValidTimestamp(ZonedDateTime timestamp) {
    return timestamp != null;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidSampledValue(sampledValue)
        && isValidTimestamp(timestamp);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MeterValue that = (MeterValue) o;
    return Objects.equals(customData, that.customData)
        && Arrays.equals(sampledValue, that.sampledValue)
        && Objects.equals(timestamp, that.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, Arrays.hashCode(sampledValue), timestamp);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("sampledValue", sampledValue)
        .add("timestamp", timestamp)
        .add("isValid", validate())
        .toString();
  }
}
