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

package eu.chargetime.ocpp.v201.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.Firmware;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * UpdateFirmwareRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class UpdateFirmwareRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * How many times Charging Station must try to download the firmware before giving up. If this
   * field is not present, it is left to Charging Station to decide how many times it wants to
   * retry.
   */
  @Nullable private Integer retries;

  /**
   * The interval in seconds after which a retry may be attempted. If this field is not present, it
   * is left to Charging Station to decide how long to wait between attempts.
   */
  @Nullable private Integer retryInterval;

  /** The Id of this request */
  private Integer requestId;

  /**
   * Firmware
   *
   * <p>A copy of the firmware that can be loaded/updated on the Charging Station.
   */
  private Firmware firmware;

  /**
   * Constructor for the UpdateFirmwareRequest class
   *
   * @param requestId The Id of this request
   * @param firmware A copy of the firmware that can be loaded/updated on the Charging Station.
   */
  public UpdateFirmwareRequest(Integer requestId, Firmware firmware) {
    setRequestId(requestId);
    setFirmware(firmware);
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
  public UpdateFirmwareRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets how many times Charging Station must try to download the firmware before giving up. If
   * this field is not present, it is left to Charging Station to decide how many times it wants to
   * retry.
   *
   * @return How many times Charging Station must try to download the firmware before giving up
   */
  @Nullable
  public Integer getRetries() {
    return retries;
  }

  /**
   * Sets how many times Charging Station must try to download the firmware before giving up. If
   * this field is not present, it is left to Charging Station to decide how many times it wants to
   * retry.
   *
   * @param retries How many times Charging Station must try to download the firmware before giving
   *     up
   */
  public void setRetries(@Nullable Integer retries) {
    this.retries = retries;
  }

  /**
   * Adds how many times Charging Station must try to download the firmware before giving up. If
   * this field is not present, it is left to Charging Station to decide how many times it wants to
   * retry.
   *
   * @param retries How many times Charging Station must try to download the firmware before giving
   *     up
   * @return this
   */
  public UpdateFirmwareRequest withRetries(@Nullable Integer retries) {
    setRetries(retries);
    return this;
  }

  /**
   * Gets the interval in seconds after which a retry may be attempted. If this field is not
   * present, it is left to Charging Station to decide how long to wait between attempts.
   *
   * @return The interval in seconds after which a retry may be attempted
   */
  @Nullable
  public Integer getRetryInterval() {
    return retryInterval;
  }

  /**
   * Sets the interval in seconds after which a retry may be attempted. If this field is not
   * present, it is left to Charging Station to decide how long to wait between attempts.
   *
   * @param retryInterval The interval in seconds after which a retry may be attempted
   */
  public void setRetryInterval(@Nullable Integer retryInterval) {
    this.retryInterval = retryInterval;
  }

  /**
   * Adds the interval in seconds after which a retry may be attempted. If this field is not
   * present, it is left to Charging Station to decide how long to wait between attempts.
   *
   * @param retryInterval The interval in seconds after which a retry may be attempted
   * @return this
   */
  public UpdateFirmwareRequest withRetryInterval(@Nullable Integer retryInterval) {
    setRetryInterval(retryInterval);
    return this;
  }

  /**
   * Gets the Id of this request
   *
   * @return The Id of this request
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the Id of this request
   *
   * @param requestId The Id of this request
   */
  public void setRequestId(Integer requestId) {
    if (!isValidRequestId(requestId)) {
      throw new PropertyConstraintException(requestId, "requestId is invalid");
    }
    this.requestId = requestId;
  }

  /**
   * Returns whether the given requestId is valid
   *
   * @param requestId the requestId to check the validity of
   * @return {@code true} if requestId is valid, {@code false} if not
   */
  private boolean isValidRequestId(Integer requestId) {
    return requestId != null;
  }

  /**
   * Gets a copy of the firmware that can be loaded/updated on the Charging Station.
   *
   * @return A copy of the firmware that can be loaded/updated on the Charging Station
   */
  public Firmware getFirmware() {
    return firmware;
  }

  /**
   * Sets a copy of the firmware that can be loaded/updated on the Charging Station.
   *
   * @param firmware A copy of the firmware that can be loaded/updated on the Charging Station
   */
  public void setFirmware(Firmware firmware) {
    if (!isValidFirmware(firmware)) {
      throw new PropertyConstraintException(firmware, "firmware is invalid");
    }
    this.firmware = firmware;
  }

  /**
   * Returns whether the given firmware is valid
   *
   * @param firmware the firmware to check the validity of
   * @return {@code true} if firmware is valid, {@code false} if not
   */
  private boolean isValidFirmware(Firmware firmware) {
    return firmware != null && firmware.validate();
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidRequestId(requestId)
        && isValidFirmware(firmware);
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
    UpdateFirmwareRequest that = (UpdateFirmwareRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(retries, that.retries)
        && Objects.equals(retryInterval, that.retryInterval)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(firmware, that.firmware);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, retries, retryInterval, requestId, firmware);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("retries", retries)
        .add("retryInterval", retryInterval)
        .add("requestId", requestId)
        .add("firmware", firmware)
        .add("isValid", validate())
        .toString();
  }
}
