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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * PublishFirmwareRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class PublishFirmwareRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** A string containing a URI pointing to a location from which to retrieve the firmware. */
  private String location;

  /**
   * How many times Charging Station must try to download the firmware before giving up. If this
   * field is not present, it is left to Charging Station to decide how many times it wants to
   * retry.
   */
  @Nullable private Integer retries;

  /** The MD5 checksum over the entire firmware file as a hexadecimal string of length 32. */
  private String checksum;

  /** The Id of the request. */
  private Integer requestId;

  /**
   * The interval in seconds after which a retry may be attempted. If this field is not present, it
   * is left to Charging Station to decide how long to wait between attempts.
   */
  @Nullable private Integer retryInterval;

  /**
   * Constructor for the PublishFirmwareRequest class
   *
   * @param location A string containing a URI pointing to a location from which to retrieve the
   *     firmware.
   * @param checksum The MD5 checksum over the entire firmware file as a hexadecimal string of
   *     length 32.
   * @param requestId The Id of the request.
   */
  public PublishFirmwareRequest(String location, String checksum, Integer requestId) {
    setLocation(location);
    setChecksum(checksum);
    setRequestId(requestId);
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
  public PublishFirmwareRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets a string containing a URI pointing to a location from which to retrieve the firmware.
   *
   * @return A string containing a URI pointing to a location from which to retrieve the firmware
   */
  public String getLocation() {
    return location;
  }

  /**
   * Sets a string containing a URI pointing to a location from which to retrieve the firmware.
   *
   * @param location A string containing a URI pointing to a location from which to retrieve the
   *     firmware
   */
  public void setLocation(String location) {
    if (!isValidLocation(location)) {
      throw new PropertyConstraintException(location, "location is invalid");
    }
    this.location = location;
  }

  /**
   * Returns whether the given location is valid
   *
   * @param location the location to check the validity of
   * @return {@code true} if location is valid, {@code false} if not
   */
  private boolean isValidLocation(String location) {
    return location != null && location.length() <= 512;
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
  public PublishFirmwareRequest withRetries(@Nullable Integer retries) {
    setRetries(retries);
    return this;
  }

  /**
   * Gets the MD5 checksum over the entire firmware file as a hexadecimal string of length 32.
   *
   * @return The MD5 checksum over the entire firmware file as a hexadecimal string of length 32
   */
  public String getChecksum() {
    return checksum;
  }

  /**
   * Sets the MD5 checksum over the entire firmware file as a hexadecimal string of length 32.
   *
   * @param checksum The MD5 checksum over the entire firmware file as a hexadecimal string of
   *     length 32
   */
  public void setChecksum(String checksum) {
    if (!isValidChecksum(checksum)) {
      throw new PropertyConstraintException(checksum, "checksum is invalid");
    }
    this.checksum = checksum;
  }

  /**
   * Returns whether the given checksum is valid
   *
   * @param checksum the checksum to check the validity of
   * @return {@code true} if checksum is valid, {@code false} if not
   */
  private boolean isValidChecksum(String checksum) {
    return checksum != null && checksum.length() <= 32;
  }

  /**
   * Gets the Id of the request.
   *
   * @return The Id of the request
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the Id of the request.
   *
   * @param requestId The Id of the request
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
  public PublishFirmwareRequest withRetryInterval(@Nullable Integer retryInterval) {
    setRetryInterval(retryInterval);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidLocation(location)
        && isValidChecksum(checksum)
        && isValidRequestId(requestId);
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
    PublishFirmwareRequest that = (PublishFirmwareRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(location, that.location)
        && Objects.equals(retries, that.retries)
        && Objects.equals(checksum, that.checksum)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(retryInterval, that.retryInterval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, location, retries, checksum, requestId, retryInterval);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("location", location)
        .add("retries", retries)
        .add("checksum", checksum)
        .add("requestId", requestId)
        .add("retryInterval", retryInterval)
        .add("isValid", validate())
        .toString();
  }
}
