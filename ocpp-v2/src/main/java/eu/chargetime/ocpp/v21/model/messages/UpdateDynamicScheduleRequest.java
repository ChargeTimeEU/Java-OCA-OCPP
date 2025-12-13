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
import eu.chargetime.ocpp.v21.model.types.ChargingScheduleUpdate;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Id of dynamic charging profile to update.
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class UpdateDynamicScheduleRequest extends RequestWithId {
  /** Id of charging profile to update. */
  private Integer chargingProfileId;

  /** Updates to a ChargingSchedulePeriodType for dynamic charging profiles. */
  private ChargingScheduleUpdate scheduleUpdate;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the UpdateDynamicScheduleRequest class
   *
   * @param chargingProfileId Id of charging profile to update.
   * @param scheduleUpdate Updates to a ChargingSchedulePeriodType for dynamic charging profiles.
   */
  public UpdateDynamicScheduleRequest(
      Integer chargingProfileId, ChargingScheduleUpdate scheduleUpdate) {
    setChargingProfileId(chargingProfileId);
    setScheduleUpdate(scheduleUpdate);
  }

  /**
   * Gets id of charging profile to update.
   *
   * @return Id of charging profile to update
   */
  public Integer getChargingProfileId() {
    return chargingProfileId;
  }

  /**
   * Sets id of charging profile to update.
   *
   * @param chargingProfileId Id of charging profile to update
   */
  public void setChargingProfileId(Integer chargingProfileId) {
    if (!isValidChargingProfileId(chargingProfileId)) {
      throw new PropertyConstraintException(chargingProfileId, "chargingProfileId is invalid");
    }
    this.chargingProfileId = chargingProfileId;
  }

  /**
   * Returns whether the given chargingProfileId is valid
   *
   * @param chargingProfileId the chargingProfileId to check the validity of
   * @return {@code true} if chargingProfileId is valid, {@code false} if not
   */
  private boolean isValidChargingProfileId(Integer chargingProfileId) {
    return chargingProfileId != null;
  }

  /**
   * Gets updates to a ChargingSchedulePeriodType for dynamic charging profiles.
   *
   * @return Updates to a ChargingSchedulePeriodType for dynamic charging profiles
   */
  public ChargingScheduleUpdate getScheduleUpdate() {
    return scheduleUpdate;
  }

  /**
   * Sets updates to a ChargingSchedulePeriodType for dynamic charging profiles.
   *
   * @param scheduleUpdate Updates to a ChargingSchedulePeriodType for dynamic charging profiles
   */
  public void setScheduleUpdate(ChargingScheduleUpdate scheduleUpdate) {
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
  private boolean isValidScheduleUpdate(ChargingScheduleUpdate scheduleUpdate) {
    return scheduleUpdate != null && scheduleUpdate.validate();
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
  public UpdateDynamicScheduleRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidChargingProfileId(chargingProfileId)
        && isValidScheduleUpdate(scheduleUpdate)
        && isValidCustomData(customData);
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
    UpdateDynamicScheduleRequest that = (UpdateDynamicScheduleRequest) o;
    return Objects.equals(chargingProfileId, that.chargingProfileId)
        && Objects.equals(scheduleUpdate, that.scheduleUpdate)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chargingProfileId, scheduleUpdate, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("chargingProfileId", chargingProfileId)
        .add("scheduleUpdate", scheduleUpdate)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
