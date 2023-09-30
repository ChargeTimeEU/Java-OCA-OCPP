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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Charging Profile
 *
 * <p>A ChargingProfile consists of a ChargingSchedule, describing the amount of power or current
 * that can be delivered per time interval.
 */
public final class ClearChargingProfile {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Identified Object. MRID. Numeric Identifier
   *
   * <p>Specifies the id of the EVSE for which to clear charging profiles. An evseId of zero (0)
   * specifies the charging profile for the overall Charging Station. Absence of this parameter
   * means the clearing applies to all charging profiles that match the other criteria in the
   * request.
   */
  @Nullable private Integer evseId;

  /**
   * Charging Profile. Charging Profile Purpose. Charging Profile Purpose Code
   *
   * <p>Specifies to purpose of the charging profiles that will be cleared, if they meet the other
   * criteria in the request.
   */
  @Nullable private ChargingProfilePurposeEnum chargingProfilePurpose;

  /**
   * Charging Profile. Stack Level. Counter
   *
   * <p>Specifies the stackLevel for which charging profiles will be cleared, if they meet the other
   * criteria in the request.
   */
  @Nullable private Integer stackLevel;

  /** Constructor for the ClearChargingProfile class */
  public ClearChargingProfile() {}

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
  public ClearChargingProfile withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets specifies the id of the EVSE for which to clear charging profiles. An evseId of zero (0)
   * specifies the charging profile for the overall Charging Station. Absence of this parameter
   * means the clearing applies to all charging profiles that match the other criteria in the
   * request.
   *
   * @return Specifies the id of the EVSE for which to clear charging profiles
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets specifies the id of the EVSE for which to clear charging profiles. An evseId of zero (0)
   * specifies the charging profile for the overall Charging Station. Absence of this parameter
   * means the clearing applies to all charging profiles that match the other criteria in the
   * request.
   *
   * @param evseId Specifies the id of the EVSE for which to clear charging profiles
   */
  public void setEvseId(@Nullable Integer evseId) {
    this.evseId = evseId;
  }

  /**
   * Adds specifies the id of the EVSE for which to clear charging profiles. An evseId of zero (0)
   * specifies the charging profile for the overall Charging Station. Absence of this parameter
   * means the clearing applies to all charging profiles that match the other criteria in the
   * request.
   *
   * @param evseId Specifies the id of the EVSE for which to clear charging profiles
   * @return this
   */
  public ClearChargingProfile withEvseId(@Nullable Integer evseId) {
    setEvseId(evseId);
    return this;
  }

  /**
   * Gets specifies to purpose of the charging profiles that will be cleared, if they meet the other
   * criteria in the request.
   *
   * @return Specifies to purpose of the charging profiles that will be cleared, if they meet the
   *     other criteria in the request
   */
  @Nullable
  public ChargingProfilePurposeEnum getChargingProfilePurpose() {
    return chargingProfilePurpose;
  }

  /**
   * Sets specifies to purpose of the charging profiles that will be cleared, if they meet the other
   * criteria in the request.
   *
   * @param chargingProfilePurpose Specifies to purpose of the charging profiles that will be
   *     cleared, if they meet the other criteria in the request
   */
  public void setChargingProfilePurpose(
      @Nullable ChargingProfilePurposeEnum chargingProfilePurpose) {
    this.chargingProfilePurpose = chargingProfilePurpose;
  }

  /**
   * Adds specifies to purpose of the charging profiles that will be cleared, if they meet the other
   * criteria in the request.
   *
   * @param chargingProfilePurpose Specifies to purpose of the charging profiles that will be
   *     cleared, if they meet the other criteria in the request
   * @return this
   */
  public ClearChargingProfile withChargingProfilePurpose(
      @Nullable ChargingProfilePurposeEnum chargingProfilePurpose) {
    setChargingProfilePurpose(chargingProfilePurpose);
    return this;
  }

  /**
   * Gets specifies the stackLevel for which charging profiles will be cleared, if they meet the
   * other criteria in the request.
   *
   * @return Specifies the stackLevel for which charging profiles will be cleared, if they meet the
   *     other criteria in the request
   */
  @Nullable
  public Integer getStackLevel() {
    return stackLevel;
  }

  /**
   * Sets specifies the stackLevel for which charging profiles will be cleared, if they meet the
   * other criteria in the request.
   *
   * @param stackLevel Specifies the stackLevel for which charging profiles will be cleared, if they
   *     meet the other criteria in the request
   */
  public void setStackLevel(@Nullable Integer stackLevel) {
    this.stackLevel = stackLevel;
  }

  /**
   * Adds specifies the stackLevel for which charging profiles will be cleared, if they meet the
   * other criteria in the request.
   *
   * @param stackLevel Specifies the stackLevel for which charging profiles will be cleared, if they
   *     meet the other criteria in the request
   * @return this
   */
  public ClearChargingProfile withStackLevel(@Nullable Integer stackLevel) {
    setStackLevel(stackLevel);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClearChargingProfile that = (ClearChargingProfile) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(chargingProfilePurpose, that.chargingProfilePurpose)
        && Objects.equals(stackLevel, that.stackLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, evseId, chargingProfilePurpose, stackLevel);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("evseId", evseId)
        .add("chargingProfilePurpose", chargingProfilePurpose)
        .add("stackLevel", stackLevel)
        .add("isValid", validate())
        .toString();
  }
}
