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
import eu.chargetime.ocpp.v21.model.types.ChargingProfile;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * SetChargingProfileRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class SetChargingProfileRequest extends RequestWithId {
  /**
   * For TxDefaultProfile an evseId=0 applies the profile to each individual evse. For
   * ChargingStationMaxProfile and ChargingStationExternalConstraints an evseId=0 contains an overal
   * limit for the whole Charging Station.
   */
  private Integer evseId;

  /**
   * A ChargingProfile consists of 1 to 3 ChargingSchedules with a list of ChargingSchedulePeriods,
   * describing the amount of power or current that can be delivered per time interval.
   *
   * <p>image::images/ChargingProfile-Simple.png[]
   */
  private ChargingProfile chargingProfile;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the SetChargingProfileRequest class
   *
   * @param evseId For TxDefaultProfile an evseId=0 applies the profile to each individual evse. For
   *     ChargingStationMaxProfile and ChargingStationExternalConstraints an evseId=0 contains an
   *     overal limit for the whole Charging Station.
   * @param chargingProfile A ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   *     ChargingSchedulePeriods, describing the amount of power or current that can be delivered
   *     per time interval.
   */
  public SetChargingProfileRequest(Integer evseId, ChargingProfile chargingProfile) {
    setEvseId(evseId);
    setChargingProfile(chargingProfile);
  }

  /**
   * Gets for TxDefaultProfile an evseId=0 applies the profile to each individual evse. For
   * ChargingStationMaxProfile and ChargingStationExternalConstraints an evseId=0 contains an overal
   * limit for the whole Charging Station.
   *
   * @return For TxDefaultProfile an evseId=0 applies the profile to each individual evse
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets for TxDefaultProfile an evseId=0 applies the profile to each individual evse. For
   * ChargingStationMaxProfile and ChargingStationExternalConstraints an evseId=0 contains an overal
   * limit for the whole Charging Station.
   *
   * @param evseId For TxDefaultProfile an evseId=0 applies the profile to each individual evse
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
    return evseId != null && evseId >= 0;
  }

  /**
   * Gets a ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   * ChargingSchedulePeriods, describing the amount of power or current that can be delivered per
   * time interval.
   *
   * @return A ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   *     ChargingSchedulePeriods, describing the amount of power or current that can be delivered
   *     per time interval
   */
  public ChargingProfile getChargingProfile() {
    return chargingProfile;
  }

  /**
   * Sets a ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   * ChargingSchedulePeriods, describing the amount of power or current that can be delivered per
   * time interval.
   *
   * @param chargingProfile A ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   *     ChargingSchedulePeriods, describing the amount of power or current that can be delivered
   *     per time interval
   */
  public void setChargingProfile(ChargingProfile chargingProfile) {
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
  private boolean isValidChargingProfile(ChargingProfile chargingProfile) {
    return chargingProfile != null && chargingProfile.validate();
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
  public SetChargingProfileRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidEvseId(evseId)
        && isValidChargingProfile(chargingProfile)
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
    SetChargingProfileRequest that = (SetChargingProfileRequest) o;
    return Objects.equals(evseId, that.evseId)
        && Objects.equals(chargingProfile, that.chargingProfile)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evseId, chargingProfile, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evseId", evseId)
        .add("chargingProfile", chargingProfile)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
