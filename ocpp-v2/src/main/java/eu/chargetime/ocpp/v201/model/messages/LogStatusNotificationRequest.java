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
import eu.chargetime.ocpp.v201.model.types.UploadLogStatusEnum;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * LogStatusNotificationRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class LogStatusNotificationRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The status of the log upload. */
  private UploadLogStatusEnum status;

  /**
   * The request id that was provided in GetLogRequest that started this log upload. This field is
   * mandatory, unless the message was triggered by a TriggerMessageRequest AND there is no log
   * upload ongoing.
   */
  @Nullable private Integer requestId;

  /**
   * Constructor for the LogStatusNotificationRequest class
   *
   * @param status The status of the log upload.
   */
  public LogStatusNotificationRequest(UploadLogStatusEnum status) {
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
  public LogStatusNotificationRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the status of the log upload.
   *
   * @return The status of the log upload
   */
  public UploadLogStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets the status of the log upload.
   *
   * @param status The status of the log upload
   */
  public void setStatus(UploadLogStatusEnum status) {
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
  private boolean isValidStatus(UploadLogStatusEnum status) {
    return status != null;
  }

  /**
   * Gets the request id that was provided in GetLogRequest that started this log upload. This field
   * is mandatory, unless the message was triggered by a TriggerMessageRequest AND there is no log
   * upload ongoing.
   *
   * @return The request id that was provided in GetLogRequest that started this log upload
   */
  @Nullable
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the request id that was provided in GetLogRequest that started this log upload. This field
   * is mandatory, unless the message was triggered by a TriggerMessageRequest AND there is no log
   * upload ongoing.
   *
   * @param requestId The request id that was provided in GetLogRequest that started this log upload
   */
  public void setRequestId(@Nullable Integer requestId) {
    this.requestId = requestId;
  }

  /**
   * Adds the request id that was provided in GetLogRequest that started this log upload. This field
   * is mandatory, unless the message was triggered by a TriggerMessageRequest AND there is no log
   * upload ongoing.
   *
   * @param requestId The request id that was provided in GetLogRequest that started this log upload
   * @return this
   */
  public LogStatusNotificationRequest withRequestId(@Nullable Integer requestId) {
    setRequestId(requestId);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidStatus(status);
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
    LogStatusNotificationRequest that = (LogStatusNotificationRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(status, that.status)
        && Objects.equals(requestId, that.requestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, status, requestId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("status", status)
        .add("requestId", requestId)
        .add("isValid", validate())
        .toString();
  }
}
