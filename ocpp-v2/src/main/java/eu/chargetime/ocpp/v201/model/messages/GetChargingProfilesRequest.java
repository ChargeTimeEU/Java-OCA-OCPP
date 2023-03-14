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
import eu.chargetime.ocpp.v201.model.types.ChargingProfileCriterion;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetChargingProfilesRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetChargingProfilesRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Reference identification that is to be used by the Charging Station in the
   * ReportChargingProfilesRequest when provided.
   */
  private Integer requestId;

  /**
   * For which EVSE installed charging profiles SHALL be reported. If 0, only charging profiles
   * installed on the Charging Station itself (the grid connection) SHALL be reported. If omitted,
   * all installed charging profiles SHALL be reported.
   */
  @Nullable private Integer evseId;

  /**
   * Charging Profile
   *
   * <p>A ChargingProfile consists of ChargingSchedule, describing the amount of power or current
   * that can be delivered per time interval.
   */
  private ChargingProfileCriterion chargingProfile;

  /**
   * Constructor for the GetChargingProfilesRequest class
   *
   * @param requestId Reference identification that is to be used by the Charging Station in the
   *     ReportChargingProfilesRequest when provided.
   * @param chargingProfile A ChargingProfile consists of ChargingSchedule, describing the amount of
   *     power or current that can be delivered per time interval.
   */
  public GetChargingProfilesRequest(Integer requestId, ChargingProfileCriterion chargingProfile) {
    setRequestId(requestId);
    setChargingProfile(chargingProfile);
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
  public GetChargingProfilesRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets reference identification that is to be used by the Charging Station in the
   * ReportChargingProfilesRequest when provided.
   *
   * @return Reference identification that is to be used by the Charging Station in the
   *     ReportChargingProfilesRequest when provided
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets reference identification that is to be used by the Charging Station in the
   * ReportChargingProfilesRequest when provided.
   *
   * @param requestId Reference identification that is to be used by the Charging Station in the
   *     ReportChargingProfilesRequest when provided
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
   * Gets for which EVSE installed charging profiles SHALL be reported. If 0, only charging profiles
   * installed on the Charging Station itself (the grid connection) SHALL be reported. If omitted,
   * all installed charging profiles SHALL be reported.
   *
   * @return For which EVSE installed charging profiles SHALL be reported
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets for which EVSE installed charging profiles SHALL be reported. If 0, only charging profiles
   * installed on the Charging Station itself (the grid connection) SHALL be reported. If omitted,
   * all installed charging profiles SHALL be reported.
   *
   * @param evseId For which EVSE installed charging profiles SHALL be reported
   */
  public void setEvseId(@Nullable Integer evseId) {
    this.evseId = evseId;
  }

  /**
   * Adds for which EVSE installed charging profiles SHALL be reported. If 0, only charging profiles
   * installed on the Charging Station itself (the grid connection) SHALL be reported. If omitted,
   * all installed charging profiles SHALL be reported.
   *
   * @param evseId For which EVSE installed charging profiles SHALL be reported
   * @return this
   */
  public GetChargingProfilesRequest withEvseId(@Nullable Integer evseId) {
    setEvseId(evseId);
    return this;
  }

  /**
   * Gets a ChargingProfile consists of ChargingSchedule, describing the amount of power or current
   * that can be delivered per time interval.
   *
   * @return A ChargingProfile consists of ChargingSchedule, describing the amount of power or
   *     current that can be delivered per time interval
   */
  public ChargingProfileCriterion getChargingProfile() {
    return chargingProfile;
  }

  /**
   * Sets a ChargingProfile consists of ChargingSchedule, describing the amount of power or current
   * that can be delivered per time interval.
   *
   * @param chargingProfile A ChargingProfile consists of ChargingSchedule, describing the amount of
   *     power or current that can be delivered per time interval
   */
  public void setChargingProfile(ChargingProfileCriterion chargingProfile) {
    if (!isValidChargingProfile(chargingProfile)) {
      throw new PropertyConstraintException(chargingProfile, "chargingProfile is invalid");
    }
    this.chargingProfile = chargingProfile;
  }

  /**
   * Returns whether the given chargingProfile is valid
   *
   * @param chargingProfile the chargingProfile to check the validity of
   * @return {@code true} if chargingProfile is valid, {@code false} if not
   */
  private boolean isValidChargingProfile(ChargingProfileCriterion chargingProfile) {
    return chargingProfile != null && chargingProfile.validate();
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidRequestId(requestId)
        && isValidChargingProfile(chargingProfile);
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
    GetChargingProfilesRequest that = (GetChargingProfilesRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(chargingProfile, that.chargingProfile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, requestId, evseId, chargingProfile);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("requestId", requestId)
        .add("evseId", evseId)
        .add("chargingProfile", chargingProfile)
        .add("isValid", validate())
        .toString();
  }
}
