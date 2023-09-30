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
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.LogStatusEnum;
import eu.chargetime.ocpp.v201.model.types.StatusInfo;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetLogResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetLogResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** This field indicates whether the Charging Station was able to accept the request. */
  private LogStatusEnum status;

  /** More information about the status. */
  @Nullable private StatusInfo statusInfo;

  /**
   * The name of the log file that will be uploaded. This field is not present when no logging
   * information is available.
   */
  @Nullable private String filename;

  /**
   * Constructor for the GetLogResponse class
   *
   * @param status This field indicates whether the Charging Station was able to accept the request.
   */
  public GetLogResponse(LogStatusEnum status) {
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
  public GetLogResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets this field indicates whether the Charging Station was able to accept the request.
   *
   * @return This field indicates whether the Charging Station was able to accept the request
   */
  public LogStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets this field indicates whether the Charging Station was able to accept the request.
   *
   * @param status This field indicates whether the Charging Station was able to accept the request
   */
  public void setStatus(LogStatusEnum status) {
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
  private boolean isValidStatus(LogStatusEnum status) {
    return status != null;
  }

  /**
   * Gets more information about the status.
   *
   * @return More information about the status
   */
  @Nullable
  public StatusInfo getStatusInfo() {
    return statusInfo;
  }

  /**
   * Sets more information about the status.
   *
   * @param statusInfo More information about the status
   */
  public void setStatusInfo(@Nullable StatusInfo statusInfo) {
    if (!isValidStatusInfo(statusInfo)) {
      throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
    }
    this.statusInfo = statusInfo;
  }

  /**
   * Returns whether the given statusInfo is valid
   *
   * @param statusInfo the statusInfo to check the validity of
   * @return {@code true} if statusInfo is valid, {@code false} if not
   */
  private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
    return statusInfo == null || statusInfo.validate();
  }

  /**
   * Adds more information about the status.
   *
   * @param statusInfo More information about the status
   * @return this
   */
  public GetLogResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
    setStatusInfo(statusInfo);
    return this;
  }

  /**
   * Gets the name of the log file that will be uploaded. This field is not present when no logging
   * information is available.
   *
   * @return The name of the log file that will be uploaded
   */
  @Nullable
  public String getFilename() {
    return filename;
  }

  /**
   * Sets the name of the log file that will be uploaded. This field is not present when no logging
   * information is available.
   *
   * @param filename The name of the log file that will be uploaded
   */
  public void setFilename(@Nullable String filename) {
    if (!isValidFilename(filename)) {
      throw new PropertyConstraintException(filename, "filename is invalid");
    }
    this.filename = filename;
  }

  /**
   * Returns whether the given filename is valid
   *
   * @param filename the filename to check the validity of
   * @return {@code true} if filename is valid, {@code false} if not
   */
  private boolean isValidFilename(@Nullable String filename) {
    return filename == null || filename.length() <= 255;
  }

  /**
   * Adds the name of the log file that will be uploaded. This field is not present when no logging
   * information is available.
   *
   * @param filename The name of the log file that will be uploaded
   * @return this
   */
  public GetLogResponse withFilename(@Nullable String filename) {
    setFilename(filename);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidStatus(status)
        && isValidStatusInfo(statusInfo)
        && isValidFilename(filename);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLogResponse that = (GetLogResponse) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(status, that.status)
        && Objects.equals(statusInfo, that.statusInfo)
        && Objects.equals(filename, that.filename);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, status, statusInfo, filename);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("status", status)
        .add("statusInfo", statusInfo)
        .add("filename", filename)
        .add("isValid", validate())
        .toString();
  }
}
