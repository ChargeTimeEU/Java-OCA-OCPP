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
import eu.chargetime.ocpp.v201.model.types.PublishFirmwareStatusEnum;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * PublishFirmwareStatusNotificationRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class PublishFirmwareStatusNotificationRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The progress status of the publishfirmware installation. */
  private PublishFirmwareStatusEnum status;

  /**
   * Required if status is Published. Can be multiple URI’s, if the Local Controller supports e.g.
   * HTTP, HTTPS, and FTP.
   */
  @Nullable private String[] location;

  /** The request id that was provided in the PublishFirmwareRequest which triggered this action. */
  @Nullable private Integer requestId;

  /**
   * Constructor for the PublishFirmwareStatusNotificationRequest class
   *
   * @param status The progress status of the publishfirmware installation.
   */
  public PublishFirmwareStatusNotificationRequest(PublishFirmwareStatusEnum status) {
    setStatus(status);
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
  public PublishFirmwareStatusNotificationRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the progress status of the publishfirmware installation.
   *
   * @return The progress status of the publishfirmware installation
   */
  public PublishFirmwareStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets the progress status of the publishfirmware installation.
   *
   * @param status The progress status of the publishfirmware installation
   */
  public void setStatus(PublishFirmwareStatusEnum status) {
    if (!isValidStatus(status)) {
      throw new PropertyConstraintException(status, "status is invalid");
    }
    this.status = status;
  }

  /**
   * Returns whether the given status is valid
   *
   * @param status the status to check the validity of
   * @return {@code true} if status is valid, {@code false} if not
   */
  private boolean isValidStatus(PublishFirmwareStatusEnum status) {
    return status != null;
  }

  /**
   * Gets required if status is Published. Can be multiple URI’s, if the Local Controller supports
   * e.g. HTTP, HTTPS, and FTP.
   *
   * @return Required if status is Published
   */
  @Nullable
  public String[] getLocation() {
    return location;
  }

  /**
   * Sets required if status is Published. Can be multiple URI’s, if the Local Controller supports
   * e.g. HTTP, HTTPS, and FTP.
   *
   * @param location Required if status is Published
   */
  public void setLocation(@Nullable String[] location) {
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
  private boolean isValidLocation(@Nullable String[] location) {
    return location == null
        || (location.length >= 1 && Arrays.stream(location).allMatch(item -> item.length() <= 512));
  }

  /**
   * Adds required if status is Published. Can be multiple URI’s, if the Local Controller supports
   * e.g. HTTP, HTTPS, and FTP.
   *
   * @param location Required if status is Published
   * @return this
   */
  public PublishFirmwareStatusNotificationRequest withLocation(@Nullable String[] location) {
    setLocation(location);
    return this;
  }

  /**
   * Gets the request id that was provided in the PublishFirmwareRequest which triggered this
   * action.
   *
   * @return The request id that was provided in the PublishFirmwareRequest which triggered this
   *     action
   */
  @Nullable
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the request id that was provided in the PublishFirmwareRequest which triggered this
   * action.
   *
   * @param requestId The request id that was provided in the PublishFirmwareRequest which triggered
   *     this action
   */
  public void setRequestId(@Nullable Integer requestId) {
    this.requestId = requestId;
  }

  /**
   * Adds the request id that was provided in the PublishFirmwareRequest which triggered this
   * action.
   *
   * @param requestId The request id that was provided in the PublishFirmwareRequest which triggered
   *     this action
   * @return this
   */
  public PublishFirmwareStatusNotificationRequest withRequestId(@Nullable Integer requestId) {
    setRequestId(requestId);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidStatus(status) && isValidLocation(location);
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
    PublishFirmwareStatusNotificationRequest that = (PublishFirmwareStatusNotificationRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(status, that.status)
        && Arrays.equals(location, that.location)
        && Objects.equals(requestId, that.requestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, status, Arrays.hashCode(location), requestId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("status", status)
        .add("location", location)
        .add("requestId", requestId)
        .add("isValid", validate())
        .toString();
  }
}
