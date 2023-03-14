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
import eu.chargetime.ocpp.v201.model.types.ClearChargingProfile;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ClearChargingProfileRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class ClearChargingProfileRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The Id of the charging profile to clear. */
  @Nullable private Integer chargingProfileId;

  /**
   * Charging Profile
   *
   * <p>A ChargingProfile consists of a ChargingSchedule, describing the amount of power or current
   * that can be delivered per time interval.
   */
  @Nullable private ClearChargingProfile chargingProfileCriteria;

  /** Constructor for the ClearChargingProfileRequest class */
  public ClearChargingProfileRequest() {}

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
  public ClearChargingProfileRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the Id of the charging profile to clear.
   *
   * @return The Id of the charging profile to clear
   */
  @Nullable
  public Integer getChargingProfileId() {
    return chargingProfileId;
  }

  /**
   * Sets the Id of the charging profile to clear.
   *
   * @param chargingProfileId The Id of the charging profile to clear
   */
  public void setChargingProfileId(@Nullable Integer chargingProfileId) {
    this.chargingProfileId = chargingProfileId;
  }

  /**
   * Adds the Id of the charging profile to clear.
   *
   * @param chargingProfileId The Id of the charging profile to clear
   * @return this
   */
  public ClearChargingProfileRequest withChargingProfileId(@Nullable Integer chargingProfileId) {
    setChargingProfileId(chargingProfileId);
    return this;
  }

  /**
   * Gets a ChargingProfile consists of a ChargingSchedule, describing the amount of power or
   * current that can be delivered per time interval.
   *
   * @return A ChargingProfile consists of a ChargingSchedule, describing the amount of power or
   *     current that can be delivered per time interval
   */
  @Nullable
  public ClearChargingProfile getChargingProfileCriteria() {
    return chargingProfileCriteria;
  }

  /**
   * Sets a ChargingProfile consists of a ChargingSchedule, describing the amount of power or
   * current that can be delivered per time interval.
   *
   * @param chargingProfileCriteria A ChargingProfile consists of a ChargingSchedule, describing the
   *     amount of power or current that can be delivered per time interval
   */
  public void setChargingProfileCriteria(@Nullable ClearChargingProfile chargingProfileCriteria) {
    if (!isValidChargingProfileCriteria(chargingProfileCriteria)) {
      throw new PropertyConstraintException(
          chargingProfileCriteria, "chargingProfileCriteria is invalid");
    }
    this.chargingProfileCriteria = chargingProfileCriteria;
  }

  /**
   * Returns whether the given chargingProfileCriteria is valid
   *
   * @param chargingProfileCriteria the chargingProfileCriteria to check the validity of
   * @return {@code true} if chargingProfileCriteria is valid, {@code false} if not
   */
  private boolean isValidChargingProfileCriteria(
      @Nullable ClearChargingProfile chargingProfileCriteria) {
    return chargingProfileCriteria == null || chargingProfileCriteria.validate();
  }

  /**
   * Adds a ChargingProfile consists of a ChargingSchedule, describing the amount of power or
   * current that can be delivered per time interval.
   *
   * @param chargingProfileCriteria A ChargingProfile consists of a ChargingSchedule, describing the
   *     amount of power or current that can be delivered per time interval
   * @return this
   */
  public ClearChargingProfileRequest withChargingProfileCriteria(
      @Nullable ClearChargingProfile chargingProfileCriteria) {
    setChargingProfileCriteria(chargingProfileCriteria);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidChargingProfileCriteria(chargingProfileCriteria);
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
    ClearChargingProfileRequest that = (ClearChargingProfileRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(chargingProfileId, that.chargingProfileId)
        && Objects.equals(chargingProfileCriteria, that.chargingProfileCriteria);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, chargingProfileId, chargingProfileCriteria);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("chargingProfileId", chargingProfileId)
        .add("chargingProfileCriteria", chargingProfileCriteria)
        .add("isValid", validate())
        .toString();
  }
}
