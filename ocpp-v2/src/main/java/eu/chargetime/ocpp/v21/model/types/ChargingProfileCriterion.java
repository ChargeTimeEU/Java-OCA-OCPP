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

package eu.chargetime.ocpp.v21.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * A ChargingProfileCriterionType is a filter for charging profiles to be selected by a
 * GetChargingProfilesRequest.
 */
public final class ChargingProfileCriterion {
  /** The purpose of the schedule transferred by this profile */
  @Nullable private ChargingProfilePurposeEnum chargingProfilePurpose;

  /**
   * Value determining level in hierarchy stack of profiles. Higher values have precedence over
   * lower values. Lowest level is 0.
   */
  @Nullable private Integer stackLevel;

  /**
   * List of all the chargingProfileIds requested. Any ChargingProfile that matches one of these
   * profiles will be reported. If omitted, the Charging Station SHALL not filter on
   * chargingProfileId. This field SHALL NOT contain more ids than set in
   * ChargingProfileEntries.maxLimit
   */
  @Nullable private Integer[] chargingProfileId;

  /**
   * For which charging limit sources, charging profiles SHALL be reported. If omitted, the Charging
   * Station SHALL not filter on chargingLimitSource. Values defined in Appendix as
   * ChargingLimitSourceEnumStringType.
   */
  @Nullable private String[] chargingLimitSource;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the ChargingProfileCriterion class */
  public ChargingProfileCriterion() {}

  /**
   * Gets the purpose of the schedule transferred by this profile
   *
   * @return The purpose of the schedule transferred by this profile
   */
  @Nullable
  public ChargingProfilePurposeEnum getChargingProfilePurpose() {
    return chargingProfilePurpose;
  }

  /**
   * Sets the purpose of the schedule transferred by this profile
   *
   * @param chargingProfilePurpose The purpose of the schedule transferred by this profile
   */
  public void setChargingProfilePurpose(
      @Nullable ChargingProfilePurposeEnum chargingProfilePurpose) {
    this.chargingProfilePurpose = chargingProfilePurpose;
  }

  /**
   * Adds the purpose of the schedule transferred by this profile
   *
   * @param chargingProfilePurpose The purpose of the schedule transferred by this profile
   * @return this
   */
  public ChargingProfileCriterion withChargingProfilePurpose(
      @Nullable ChargingProfilePurposeEnum chargingProfilePurpose) {
    setChargingProfilePurpose(chargingProfilePurpose);
    return this;
  }

  /**
   * Gets value determining level in hierarchy stack of profiles. Higher values have precedence over
   * lower values. Lowest level is 0.
   *
   * @return Value determining level in hierarchy stack of profiles
   */
  @Nullable
  public Integer getStackLevel() {
    return stackLevel;
  }

  /**
   * Sets value determining level in hierarchy stack of profiles. Higher values have precedence over
   * lower values. Lowest level is 0.
   *
   * @param stackLevel Value determining level in hierarchy stack of profiles
   */
  public void setStackLevel(@Nullable Integer stackLevel) {
    if (!isValidStackLevel(stackLevel)) {
      throw new PropertyConstraintException(stackLevel, "stackLevel is invalid");
    }
    this.stackLevel = stackLevel;
  }

  /**
   * Returns whether the given stackLevel is valid
   *
   * @param stackLevel the stackLevel to check the validity of
   * @return {@code true} if stackLevel is valid, {@code false} if not
   */
  private boolean isValidStackLevel(@Nullable Integer stackLevel) {
    return stackLevel == null || (stackLevel >= 0);
  }

  /**
   * Adds value determining level in hierarchy stack of profiles. Higher values have precedence over
   * lower values. Lowest level is 0.
   *
   * @param stackLevel Value determining level in hierarchy stack of profiles
   * @return this
   */
  public ChargingProfileCriterion withStackLevel(@Nullable Integer stackLevel) {
    setStackLevel(stackLevel);
    return this;
  }

  /**
   * Gets list of all the chargingProfileIds requested. Any ChargingProfile that matches one of
   * these profiles will be reported. If omitted, the Charging Station SHALL not filter on
   * chargingProfileId. This field SHALL NOT contain more ids than set in
   * ChargingProfileEntries.maxLimit
   *
   * @return List of all the chargingProfileIds requested
   */
  @Nullable
  public Integer[] getChargingProfileId() {
    return chargingProfileId;
  }

  /**
   * Sets list of all the chargingProfileIds requested. Any ChargingProfile that matches one of
   * these profiles will be reported. If omitted, the Charging Station SHALL not filter on
   * chargingProfileId. This field SHALL NOT contain more ids than set in
   * ChargingProfileEntries.maxLimit
   *
   * @param chargingProfileId List of all the chargingProfileIds requested
   */
  public void setChargingProfileId(@Nullable Integer[] chargingProfileId) {
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
  private boolean isValidChargingProfileId(@Nullable Integer[] chargingProfileId) {
    return chargingProfileId == null || (chargingProfileId.length >= 1);
  }

  /**
   * Adds list of all the chargingProfileIds requested. Any ChargingProfile that matches one of
   * these profiles will be reported. If omitted, the Charging Station SHALL not filter on
   * chargingProfileId. This field SHALL NOT contain more ids than set in
   * ChargingProfileEntries.maxLimit
   *
   * @param chargingProfileId List of all the chargingProfileIds requested
   * @return this
   */
  public ChargingProfileCriterion withChargingProfileId(@Nullable Integer[] chargingProfileId) {
    setChargingProfileId(chargingProfileId);
    return this;
  }

  /**
   * Gets for which charging limit sources, charging profiles SHALL be reported. If omitted, the
   * Charging Station SHALL not filter on chargingLimitSource. Values defined in Appendix as
   * ChargingLimitSourceEnumStringType.
   *
   * @return For which charging limit sources, charging profiles SHALL be reported
   */
  @Nullable
  public String[] getChargingLimitSource() {
    return chargingLimitSource;
  }

  /**
   * Sets for which charging limit sources, charging profiles SHALL be reported. If omitted, the
   * Charging Station SHALL not filter on chargingLimitSource. Values defined in Appendix as
   * ChargingLimitSourceEnumStringType.
   *
   * @param chargingLimitSource For which charging limit sources, charging profiles SHALL be
   *     reported
   */
  public void setChargingLimitSource(@Nullable String[] chargingLimitSource) {
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
  private boolean isValidChargingLimitSource(@Nullable String[] chargingLimitSource) {
    return chargingLimitSource == null
        || (chargingLimitSource.length >= 1
            && chargingLimitSource.length <= 4
            && Arrays.stream(chargingLimitSource).allMatch(item -> item.length() <= 20));
  }

  /**
   * Adds for which charging limit sources, charging profiles SHALL be reported. If omitted, the
   * Charging Station SHALL not filter on chargingLimitSource. Values defined in Appendix as
   * ChargingLimitSourceEnumStringType.
   *
   * @param chargingLimitSource For which charging limit sources, charging profiles SHALL be
   *     reported
   * @return this
   */
  public ChargingProfileCriterion withChargingLimitSource(@Nullable String[] chargingLimitSource) {
    setChargingLimitSource(chargingLimitSource);
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
  public ChargingProfileCriterion withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidStackLevel(stackLevel)
        && isValidChargingProfileId(chargingProfileId)
        && isValidChargingLimitSource(chargingLimitSource)
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
    ChargingProfileCriterion that = (ChargingProfileCriterion) o;
    return Objects.equals(chargingProfilePurpose, that.chargingProfilePurpose)
        && Objects.equals(stackLevel, that.stackLevel)
        && Arrays.equals(chargingProfileId, that.chargingProfileId)
        && Arrays.equals(chargingLimitSource, that.chargingLimitSource)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        chargingProfilePurpose,
        stackLevel,
        Arrays.hashCode(chargingProfileId),
        Arrays.hashCode(chargingLimitSource),
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("chargingProfilePurpose", chargingProfilePurpose)
        .add("stackLevel", stackLevel)
        .add("chargingProfileId", chargingProfileId)
        .add("chargingLimitSource", chargingLimitSource)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
