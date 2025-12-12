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
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.ChargingProfileStatusEnum;
import eu.chargetime.ocpp.v21.model.types.ChargingScheduleUpdate;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.StatusInfo;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * PullDynamicScheduleUpdateResponse
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class PullDynamicScheduleUpdateResponse extends Confirmation {
  /** Updates to a ChargingSchedulePeriodType for dynamic charging profiles. */
  @Nullable private ChargingScheduleUpdate scheduleUpdate;

  /** Result of request. */
  private ChargingProfileStatusEnum status;

  /** More information about the status. */
  @Nullable private StatusInfo statusInfo;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the PullDynamicScheduleUpdateResponse class
   *
   * @param status Result of request.
   */
  public PullDynamicScheduleUpdateResponse(ChargingProfileStatusEnum status) {
    setStatus(status);
  }

  /**
   * Gets updates to a ChargingSchedulePeriodType for dynamic charging profiles.
   *
   * @return Updates to a ChargingSchedulePeriodType for dynamic charging profiles
   */
  @Nullable
  public ChargingScheduleUpdate getScheduleUpdate() {
    return scheduleUpdate;
  }

  /**
   * Sets updates to a ChargingSchedulePeriodType for dynamic charging profiles.
   *
   * @param scheduleUpdate Updates to a ChargingSchedulePeriodType for dynamic charging profiles
   */
  public void setScheduleUpdate(@Nullable ChargingScheduleUpdate scheduleUpdate) {
    if (!isValidScheduleUpdate(scheduleUpdate)) {
      throw new PropertyConstraintException(scheduleUpdate, "scheduleUpdate is invalid");
    }
    this.scheduleUpdate = scheduleUpdate;
  }

  /**
   * Returns whether the given scheduleUpdate is valid
   *
   * @param scheduleUpdate the scheduleUpdate to check the validity of
   * @return {@code true} if scheduleUpdate is valid, {@code false} if not
   */
  private boolean isValidScheduleUpdate(@Nullable ChargingScheduleUpdate scheduleUpdate) {
    return scheduleUpdate == null || scheduleUpdate.validate();
  }

  /**
   * Adds updates to a ChargingSchedulePeriodType for dynamic charging profiles.
   *
   * @param scheduleUpdate Updates to a ChargingSchedulePeriodType for dynamic charging profiles
   * @return this
   */
  public PullDynamicScheduleUpdateResponse withScheduleUpdate(
      @Nullable ChargingScheduleUpdate scheduleUpdate) {
    setScheduleUpdate(scheduleUpdate);
    return this;
  }

  /**
   * Gets result of request.
   *
   * @return Result of request
   */
  public ChargingProfileStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets result of request.
   *
   * @param status Result of request
   */
  public void setStatus(ChargingProfileStatusEnum status) {
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
  private boolean isValidStatus(ChargingProfileStatusEnum status) {
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
  public PullDynamicScheduleUpdateResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
    setStatusInfo(statusInfo);
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
  public PullDynamicScheduleUpdateResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidScheduleUpdate(scheduleUpdate)
        && isValidStatus(status)
        && isValidStatusInfo(statusInfo)
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
    PullDynamicScheduleUpdateResponse that = (PullDynamicScheduleUpdateResponse) o;
    return Objects.equals(scheduleUpdate, that.scheduleUpdate)
        && Objects.equals(status, that.status)
        && Objects.equals(statusInfo, that.statusInfo)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scheduleUpdate, status, statusInfo, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("scheduleUpdate", scheduleUpdate)
        .add("status", status)
        .add("statusInfo", statusInfo)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
