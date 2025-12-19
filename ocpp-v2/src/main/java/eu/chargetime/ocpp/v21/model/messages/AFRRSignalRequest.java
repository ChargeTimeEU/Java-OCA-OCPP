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

package eu.chargetime.ocpp.v21.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * AFRRSignalRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class AFRRSignalRequest extends RequestWithId {
  /** Time when signal becomes active. */
  private ZonedDateTime timestamp;

  /** Value of signal in v2xSignalWattCurve. */
  private Integer signal;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the AFRRSignalRequest class
   *
   * @param timestamp Time when signal becomes active.
   * @param signal Value of signal in v2xSignalWattCurve.
   */
  public AFRRSignalRequest(ZonedDateTime timestamp, Integer signal) {
    setTimestamp(timestamp);
    setSignal(signal);
  }

  /**
   * Gets time when signal becomes active.
   *
   * @return Time when signal becomes active
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets time when signal becomes active.
   *
   * @param timestamp Time when signal becomes active
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

  /**
   * Gets value of signal in v2xSignalWattCurve.
   *
   * @return Value of signal in v2xSignalWattCurve
   */
  public Integer getSignal() {
    return signal;
  }

  /**
   * Sets value of signal in v2xSignalWattCurve.
   *
   * @param signal Value of signal in v2xSignalWattCurve
   */
  public void setSignal(Integer signal) {
    if (!isValidSignal(signal)) {
      throw new PropertyConstraintException(signal, "signal is invalid");
    }
    this.signal = signal;
  }

  /**
   * Returns whether the given signal is valid
   *
   * @param signal the signal to check the validity of
   * @return {@code true} if signal is valid, {@code false} if not
   */
  private boolean isValidSignal(Integer signal) {
    return signal != null;
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
  public AFRRSignalRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidTimestamp(timestamp) && isValidSignal(signal) && isValidCustomData(customData);
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AFRRSignalRequest that = (AFRRSignalRequest) o;
    return Objects.equals(timestamp, that.timestamp)
        && Objects.equals(signal, that.signal)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, signal, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("timestamp", timestamp)
        .add("signal", signal)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
