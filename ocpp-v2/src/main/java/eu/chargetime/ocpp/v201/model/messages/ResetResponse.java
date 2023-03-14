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
import eu.chargetime.ocpp.v201.model.types.ResetStatusEnum;
import eu.chargetime.ocpp.v201.model.types.StatusInfo;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ResetResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class ResetResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Whether the Charging Station is able to perform the reset. */
  private ResetStatusEnum status;

  /** More information about the status. */
  @Nullable private StatusInfo statusInfo;

  /**
   * Constructor for the ResetResponse class
   *
   * @param status Whether the Charging Station is able to perform the reset.
   */
  public ResetResponse(ResetStatusEnum status) {
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
  public ResetResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets whether the Charging Station is able to perform the reset.
   *
   * @return Whether the Charging Station is able to perform the reset
   */
  public ResetStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets whether the Charging Station is able to perform the reset.
   *
   * @param status Whether the Charging Station is able to perform the reset
   */
  public void setStatus(ResetStatusEnum status) {
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
  private boolean isValidStatus(ResetStatusEnum status) {
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
  public ResetResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
    setStatusInfo(statusInfo);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResetResponse that = (ResetResponse) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(status, that.status)
        && Objects.equals(statusInfo, that.statusInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, status, statusInfo);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("status", status)
        .add("statusInfo", statusInfo)
        .add("isValid", validate())
        .toString();
  }
}
