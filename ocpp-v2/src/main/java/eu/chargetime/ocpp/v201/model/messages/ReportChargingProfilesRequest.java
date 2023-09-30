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
import eu.chargetime.ocpp.v201.model.types.ChargingLimitSourceEnum;
import eu.chargetime.ocpp.v201.model.types.ChargingProfile;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ReportChargingProfilesRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class ReportChargingProfilesRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Id used to match the GetChargingProfilesRequest message with the resulting
   * ReportChargingProfilesRequest messages. When the CSMS provided a requestId in the
   * GetChargingProfilesRequest, this field SHALL contain the same value.
   */
  private Integer requestId;

  /** Source that has installed this charging profile. */
  private ChargingLimitSourceEnum chargingLimitSource;

  /**
   * Charging Profile
   *
   * <p>A ChargingProfile consists of ChargingSchedule, describing the amount of power or current
   * that can be delivered per time interval.
   */
  private ChargingProfile[] chargingProfile;

  /**
   * To Be Continued. Default value when omitted: false. false indicates that there are no further
   * messages as part of this report.
   */
  @Nullable private Boolean tbc;

  /**
   * The evse to which the charging profile applies. If evseId = 0, the message contains an overall
   * limit for the Charging Station.
   */
  private Integer evseId;

  /**
   * Constructor for the ReportChargingProfilesRequest class
   *
   * @param requestId Id used to match the GetChargingProfilesRequest message with the resulting
   *     ReportChargingProfilesRequest messages. When the CSMS provided a requestId in the
   *     GetChargingProfilesRequest, this field SHALL contain the same value.
   * @param chargingLimitSource Source that has installed this charging profile.
   * @param chargingProfile A ChargingProfile consists of ChargingSchedule, describing the amount of
   *     power or current that can be delivered per time interval.
   * @param evseId The evse to which the charging profile applies. If evseId = 0, the message
   *     contains an overall limit for the Charging Station.
   */
  public ReportChargingProfilesRequest(
      Integer requestId,
      ChargingLimitSourceEnum chargingLimitSource,
      ChargingProfile[] chargingProfile,
      Integer evseId) {
    setRequestId(requestId);
    setChargingLimitSource(chargingLimitSource);
    setChargingProfile(chargingProfile);
    setEvseId(evseId);
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
  public ReportChargingProfilesRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets id used to match the GetChargingProfilesRequest message with the resulting
   * ReportChargingProfilesRequest messages. When the CSMS provided a requestId in the
   * GetChargingProfilesRequest, this field SHALL contain the same value.
   *
   * @return Id used to match the GetChargingProfilesRequest message with the resulting
   *     ReportChargingProfilesRequest messages
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets id used to match the GetChargingProfilesRequest message with the resulting
   * ReportChargingProfilesRequest messages. When the CSMS provided a requestId in the
   * GetChargingProfilesRequest, this field SHALL contain the same value.
   *
   * @param requestId Id used to match the GetChargingProfilesRequest message with the resulting
   *     ReportChargingProfilesRequest messages
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
   * Gets source that has installed this charging profile.
   *
   * @return Source that has installed this charging profile
   */
  public ChargingLimitSourceEnum getChargingLimitSource() {
    return chargingLimitSource;
  }

  /**
   * Sets source that has installed this charging profile.
   *
   * @param chargingLimitSource Source that has installed this charging profile
   */
  public void setChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
    if (!isValidChargingLimitSource(chargingLimitSource)) {
      throw new PropertyConstraintException(chargingLimitSource, "chargingLimitSource is invalid");
    }
    this.chargingLimitSource = chargingLimitSource;
  }

  /**
   * Returns whether the given chargingLimitSource is valid
   *
   * @param chargingLimitSource the chargingLimitSource to check the validity of
   * @return {@code true} if chargingLimitSource is valid, {@code false} if not
   */
  private boolean isValidChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
    return chargingLimitSource != null;
  }

  /**
   * Gets a ChargingProfile consists of ChargingSchedule, describing the amount of power or current
   * that can be delivered per time interval.
   *
   * @return A ChargingProfile consists of ChargingSchedule, describing the amount of power or
   *     current that can be delivered per time interval
   */
  public ChargingProfile[] getChargingProfile() {
    return chargingProfile;
  }

  /**
   * Sets a ChargingProfile consists of ChargingSchedule, describing the amount of power or current
   * that can be delivered per time interval.
   *
   * @param chargingProfile A ChargingProfile consists of ChargingSchedule, describing the amount of
   *     power or current that can be delivered per time interval
   */
  public void setChargingProfile(ChargingProfile[] chargingProfile) {
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
  private boolean isValidChargingProfile(ChargingProfile[] chargingProfile) {
    return chargingProfile != null
        && chargingProfile.length >= 1
        && Arrays.stream(chargingProfile).allMatch(item -> item.validate());
  }

  /**
   * Gets to Be Continued. Default value when omitted: false. false indicates that there are no
   * further messages as part of this report.
   *
   * @return To Be Continued
   */
  public Boolean getTbc() {
    return tbc != null ? tbc : false;
  }

  /**
   * Sets to Be Continued. Default value when omitted: false. false indicates that there are no
   * further messages as part of this report.
   *
   * @param tbc To Be Continued
   */
  public void setTbc(@Nullable Boolean tbc) {
    this.tbc = tbc;
  }

  /**
   * Adds to Be Continued. Default value when omitted: false. false indicates that there are no
   * further messages as part of this report.
   *
   * @param tbc To Be Continued
   * @return this
   */
  public ReportChargingProfilesRequest withTbc(@Nullable Boolean tbc) {
    setTbc(tbc);
    return this;
  }

  /**
   * Gets the evse to which the charging profile applies. If evseId = 0, the message contains an
   * overall limit for the Charging Station.
   *
   * @return The evse to which the charging profile applies
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the evse to which the charging profile applies. If evseId = 0, the message contains an
   * overall limit for the Charging Station.
   *
   * @param evseId The evse to which the charging profile applies
   */
  public void setEvseId(Integer evseId) {
    if (!isValidEvseId(evseId)) {
      throw new PropertyConstraintException(evseId, "evseId is invalid");
    }
    this.evseId = evseId;
  }

  /**
   * Returns whether the given evseId is valid
   *
   * @param evseId the evseId to check the validity of
   * @return {@code true} if evseId is valid, {@code false} if not
   */
  private boolean isValidEvseId(Integer evseId) {
    return evseId != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidRequestId(requestId)
        && isValidChargingLimitSource(chargingLimitSource)
        && isValidChargingProfile(chargingProfile)
        && isValidEvseId(evseId);
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
    ReportChargingProfilesRequest that = (ReportChargingProfilesRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(chargingLimitSource, that.chargingLimitSource)
        && Arrays.equals(chargingProfile, that.chargingProfile)
        && Objects.equals(tbc, that.tbc)
        && Objects.equals(evseId, that.evseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, requestId, chargingLimitSource, Arrays.hashCode(chargingProfile), tbc, evseId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("requestId", requestId)
        .add("chargingLimitSource", chargingLimitSource)
        .add("chargingProfile", chargingProfile)
        .add("tbc", tbc)
        .add("evseId", evseId)
        .add("isValid", validate())
        .toString();
  }
}
