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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * A ChargingProfile consists of 1 to 3 ChargingSchedules with a list of ChargingSchedulePeriods,
 * describing the amount of power or current that can be delivered per time interval.
 *
 * <p>image::images/ChargingProfile-Simple.png[]
 */
public final class ChargingProfile {
  /**
   * Id of ChargingProfile. Unique within charging station. Id can have a negative value. This is
   * useful to distinguish charging profiles from an external actor (external constraints) from
   * charging profiles received from CSMS.
   */
  private Integer id;

  /**
   * Value determining level in hierarchy stack of profiles. Higher values have precedence over
   * lower values. Lowest level is 0.
   */
  private Integer stackLevel;

  /** The purpose of the schedule transferred by this profile */
  private ChargingProfilePurposeEnum chargingProfilePurpose;

  /** The kind of schedule. */
  private ChargingProfileKindEnum chargingProfileKind;

  /** The start point of a recurrence. */
  @Nullable private RecurrencyKindEnum recurrencyKind;

  /**
   * Point in time at which the profile starts to be valid. If absent, the profile is valid as soon
   * as it is received by the Charging Station.
   */
  @Nullable private ZonedDateTime validFrom;

  /**
   * Point in time at which the profile stops to be valid. If absent, the profile is valid until it
   * is replaced by another profile.
   */
  @Nullable private ZonedDateTime validTo;

  /**
   * SHALL only be included if ChargingProfilePurpose is set to TxProfile in a
   * SetChargingProfileRequest. The transactionId is used to match the profile to a specific
   * transaction.
   */
  @Nullable private String transactionId;

  /**
   * Period in seconds that this charging profile remains valid after the Charging Station has gone
   * offline. After this period the charging profile becomes invalid for as long as it is offline
   * and the Charging Station reverts back to a valid profile with a lower stack level. If
   * invalidAfterOfflineDuration is true, then this charging profile will become permanently
   * invalid. A value of 0 means that the charging profile is immediately invalid while offline.
   * When the field is absent, then no timeout applies and the charging profile remains valid when
   * offline.
   */
  @Nullable private Integer maxOfflineDuration;

  /**
   * Charging schedule structure defines a list of charging periods, as used in:
   * NotifyEVChargingScheduleRequest and ChargingProfileType. When used in a
   * NotifyEVChargingScheduleRequest only duration and chargingSchedulePeriod are relevant and
   * chargingRateUnit must be 'W'.
   *
   * <p>An ISO 15118-20 session may provide either an absolutePriceSchedule or a priceLevelSchedule.
   * An ISO 15118-2 session can only provide asalesTariff element. The field digestValue is used
   * when price schedule or sales tariff are signed.
   *
   * <p>image::images/ChargingSchedule-Simple.png[]
   */
  private ChargingSchedule[] chargingSchedule;

  /**
   * When set to true this charging profile will not be valid anymore after being offline for more
   * than maxOfflineDuration.
   *
   * <p>When absent defaults to false.
   */
  @Nullable private Boolean invalidAfterOfflineDuration;

  /**
   * Interval in seconds after receipt of last update, when to request a profile update by sending a
   * PullDynamicScheduleUpdateRequest message.
   *
   * <pre>
   * A value of 0 or no value means that no update interval applies.
   * Only relevant in a dynamic charging profile.
   * </pre>
   */
  @Nullable private Integer dynUpdateInterval;

  /**
   * Time at which limits or setpoints in this charging profile were last updated by a
   * PullDynamicScheduleUpdateRequest or UpdateDynamicScheduleRequest or by an external actor.
   *
   * <p>Only relevant in a dynamic charging profile.
   */
  @Nullable private ZonedDateTime dynUpdateTime;

  /**
   * ISO 15118-20 signature for all price schedules in chargingSchedules.
   *
   * <p>Note: for 256-bit elliptic curves (like secp256k1) the ECDSA signature is 512 bits (64
   * bytes) and for 521-bit curves (like secp521r1) the signature is 1042 bits. This equals 131
   * bytes, which can be encoded as base64 in 176 bytes.
   */
  @Nullable private String priceScheduleSignature;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ChargingProfile class
   *
   * @param id Id of ChargingProfile. Unique within charging station. Id can have a negative value.
   *     This is useful to distinguish charging profiles from an external actor (external
   *     constraints) from charging profiles received from CSMS.
   * @param stackLevel Value determining level in hierarchy stack of profiles. Higher values have
   *     precedence over lower values. Lowest level is 0.
   * @param chargingProfilePurpose The purpose of the schedule transferred by this profile
   * @param chargingProfileKind The kind of schedule.
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: NotifyEVChargingScheduleRequest and ChargingProfileType. When used in a
   *     NotifyEVChargingScheduleRequest only duration and chargingSchedulePeriod are relevant and
   *     chargingRateUnit must be 'W'.
   */
  public ChargingProfile(
      Integer id,
      Integer stackLevel,
      ChargingProfilePurposeEnum chargingProfilePurpose,
      ChargingProfileKindEnum chargingProfileKind,
      ChargingSchedule[] chargingSchedule) {
    setId(id);
    setStackLevel(stackLevel);
    setChargingProfilePurpose(chargingProfilePurpose);
    setChargingProfileKind(chargingProfileKind);
    setChargingSchedule(chargingSchedule);
  }

  /**
   * Gets id of ChargingProfile. Unique within charging station. Id can have a negative value. This
   * is useful to distinguish charging profiles from an external actor (external constraints) from
   * charging profiles received from CSMS.
   *
   * @return Id of ChargingProfile
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets id of ChargingProfile. Unique within charging station. Id can have a negative value. This
   * is useful to distinguish charging profiles from an external actor (external constraints) from
   * charging profiles received from CSMS.
   *
   * @param id Id of ChargingProfile
   */
  public void setId(Integer id) {
    if (!isValidId(id)) {
      throw new PropertyConstraintException(id, "id is invalid");
    }
    this.id = id;
  }

  /**
   * Returns whether the given id is valid
   *
   * @param id the id to check the validity of
   * @return {@code true} if id is valid, {@code false} if not
   */
  private boolean isValidId(Integer id) {
    return id != null;
  }

  /**
   * Gets value determining level in hierarchy stack of profiles. Higher values have precedence over
   * lower values. Lowest level is 0.
   *
   * @return Value determining level in hierarchy stack of profiles
   */
  public Integer getStackLevel() {
    return stackLevel;
  }

  /**
   * Sets value determining level in hierarchy stack of profiles. Higher values have precedence over
   * lower values. Lowest level is 0.
   *
   * @param stackLevel Value determining level in hierarchy stack of profiles
   */
  public void setStackLevel(Integer stackLevel) {
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
  private boolean isValidStackLevel(Integer stackLevel) {
    return stackLevel != null && stackLevel >= 0;
  }

  /**
   * Gets the purpose of the schedule transferred by this profile
   *
   * @return The purpose of the schedule transferred by this profile
   */
  public ChargingProfilePurposeEnum getChargingProfilePurpose() {
    return chargingProfilePurpose;
  }

  /**
   * Sets the purpose of the schedule transferred by this profile
   *
   * @param chargingProfilePurpose The purpose of the schedule transferred by this profile
   */
  public void setChargingProfilePurpose(ChargingProfilePurposeEnum chargingProfilePurpose) {
    if (!isValidChargingProfilePurpose(chargingProfilePurpose)) {
      throw new PropertyConstraintException(
          chargingProfilePurpose, "chargingProfilePurpose is invalid");
    }
    this.chargingProfilePurpose = chargingProfilePurpose;
  }

  /**
   * Returns whether the given chargingProfilePurpose is valid
   *
   * @param chargingProfilePurpose the chargingProfilePurpose to check the validity of
   * @return {@code true} if chargingProfilePurpose is valid, {@code false} if not
   */
  private boolean isValidChargingProfilePurpose(ChargingProfilePurposeEnum chargingProfilePurpose) {
    return chargingProfilePurpose != null;
  }

  /**
   * Gets the kind of schedule.
   *
   * @return The kind of schedule
   */
  public ChargingProfileKindEnum getChargingProfileKind() {
    return chargingProfileKind;
  }

  /**
   * Sets the kind of schedule.
   *
   * @param chargingProfileKind The kind of schedule
   */
  public void setChargingProfileKind(ChargingProfileKindEnum chargingProfileKind) {
    if (!isValidChargingProfileKind(chargingProfileKind)) {
      throw new PropertyConstraintException(chargingProfileKind, "chargingProfileKind is invalid");
    }
    this.chargingProfileKind = chargingProfileKind;
  }

  /**
   * Returns whether the given chargingProfileKind is valid
   *
   * @param chargingProfileKind the chargingProfileKind to check the validity of
   * @return {@code true} if chargingProfileKind is valid, {@code false} if not
   */
  private boolean isValidChargingProfileKind(ChargingProfileKindEnum chargingProfileKind) {
    return chargingProfileKind != null;
  }

  /**
   * Gets the start point of a recurrence.
   *
   * @return The start point of a recurrence
   */
  @Nullable
  public RecurrencyKindEnum getRecurrencyKind() {
    return recurrencyKind;
  }

  /**
   * Sets the start point of a recurrence.
   *
   * @param recurrencyKind The start point of a recurrence
   */
  public void setRecurrencyKind(@Nullable RecurrencyKindEnum recurrencyKind) {
    this.recurrencyKind = recurrencyKind;
  }

  /**
   * Adds the start point of a recurrence.
   *
   * @param recurrencyKind The start point of a recurrence
   * @return this
   */
  public ChargingProfile withRecurrencyKind(@Nullable RecurrencyKindEnum recurrencyKind) {
    setRecurrencyKind(recurrencyKind);
    return this;
  }

  /**
   * Gets point in time at which the profile starts to be valid. If absent, the profile is valid as
   * soon as it is received by the Charging Station.
   *
   * @return Point in time at which the profile starts to be valid
   */
  @Nullable
  public ZonedDateTime getValidFrom() {
    return validFrom;
  }

  /**
   * Sets point in time at which the profile starts to be valid. If absent, the profile is valid as
   * soon as it is received by the Charging Station.
   *
   * @param validFrom Point in time at which the profile starts to be valid
   */
  public void setValidFrom(@Nullable ZonedDateTime validFrom) {
    this.validFrom = validFrom;
  }

  /**
   * Adds point in time at which the profile starts to be valid. If absent, the profile is valid as
   * soon as it is received by the Charging Station.
   *
   * @param validFrom Point in time at which the profile starts to be valid
   * @return this
   */
  public ChargingProfile withValidFrom(@Nullable ZonedDateTime validFrom) {
    setValidFrom(validFrom);
    return this;
  }

  /**
   * Gets point in time at which the profile stops to be valid. If absent, the profile is valid
   * until it is replaced by another profile.
   *
   * @return Point in time at which the profile stops to be valid
   */
  @Nullable
  public ZonedDateTime getValidTo() {
    return validTo;
  }

  /**
   * Sets point in time at which the profile stops to be valid. If absent, the profile is valid
   * until it is replaced by another profile.
   *
   * @param validTo Point in time at which the profile stops to be valid
   */
  public void setValidTo(@Nullable ZonedDateTime validTo) {
    this.validTo = validTo;
  }

  /**
   * Adds point in time at which the profile stops to be valid. If absent, the profile is valid
   * until it is replaced by another profile.
   *
   * @param validTo Point in time at which the profile stops to be valid
   * @return this
   */
  public ChargingProfile withValidTo(@Nullable ZonedDateTime validTo) {
    setValidTo(validTo);
    return this;
  }

  /**
   * Gets SHALL only be included if ChargingProfilePurpose is set to TxProfile in a
   * SetChargingProfileRequest. The transactionId is used to match the profile to a specific
   * transaction.
   *
   * @return SHALL only be included if ChargingProfilePurpose is set to TxProfile in a
   *     SetChargingProfileRequest
   */
  @Nullable
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets SHALL only be included if ChargingProfilePurpose is set to TxProfile in a
   * SetChargingProfileRequest. The transactionId is used to match the profile to a specific
   * transaction.
   *
   * @param transactionId SHALL only be included if ChargingProfilePurpose is set to TxProfile in a
   *     SetChargingProfileRequest
   */
  public void setTransactionId(@Nullable String transactionId) {
    if (!isValidTransactionId(transactionId)) {
      throw new PropertyConstraintException(transactionId, "transactionId is invalid");
    }
    this.transactionId = transactionId;
  }

  /**
   * Returns whether the given transactionId is valid
   *
   * @param transactionId the transactionId to check the validity of
   * @return {@code true} if transactionId is valid, {@code false} if not
   */
  private boolean isValidTransactionId(@Nullable String transactionId) {
    return transactionId == null || transactionId.length() <= 36;
  }

  /**
   * Adds SHALL only be included if ChargingProfilePurpose is set to TxProfile in a
   * SetChargingProfileRequest. The transactionId is used to match the profile to a specific
   * transaction.
   *
   * @param transactionId SHALL only be included if ChargingProfilePurpose is set to TxProfile in a
   *     SetChargingProfileRequest
   * @return this
   */
  public ChargingProfile withTransactionId(@Nullable String transactionId) {
    setTransactionId(transactionId);
    return this;
  }

  /**
   * Gets period in seconds that this charging profile remains valid after the Charging Station has
   * gone offline. After this period the charging profile becomes invalid for as long as it is
   * offline and the Charging Station reverts back to a valid profile with a lower stack level. If
   * invalidAfterOfflineDuration is true, then this charging profile will become permanently
   * invalid. A value of 0 means that the charging profile is immediately invalid while offline.
   * When the field is absent, then no timeout applies and the charging profile remains valid when
   * offline.
   *
   * @return Period in seconds that this charging profile remains valid after the Charging Station
   *     has gone offline
   */
  @Nullable
  public Integer getMaxOfflineDuration() {
    return maxOfflineDuration;
  }

  /**
   * Sets period in seconds that this charging profile remains valid after the Charging Station has
   * gone offline. After this period the charging profile becomes invalid for as long as it is
   * offline and the Charging Station reverts back to a valid profile with a lower stack level. If
   * invalidAfterOfflineDuration is true, then this charging profile will become permanently
   * invalid. A value of 0 means that the charging profile is immediately invalid while offline.
   * When the field is absent, then no timeout applies and the charging profile remains valid when
   * offline.
   *
   * @param maxOfflineDuration Period in seconds that this charging profile remains valid after the
   *     Charging Station has gone offline
   */
  public void setMaxOfflineDuration(@Nullable Integer maxOfflineDuration) {
    this.maxOfflineDuration = maxOfflineDuration;
  }

  /**
   * Adds period in seconds that this charging profile remains valid after the Charging Station has
   * gone offline. After this period the charging profile becomes invalid for as long as it is
   * offline and the Charging Station reverts back to a valid profile with a lower stack level. If
   * invalidAfterOfflineDuration is true, then this charging profile will become permanently
   * invalid. A value of 0 means that the charging profile is immediately invalid while offline.
   * When the field is absent, then no timeout applies and the charging profile remains valid when
   * offline.
   *
   * @param maxOfflineDuration Period in seconds that this charging profile remains valid after the
   *     Charging Station has gone offline
   * @return this
   */
  public ChargingProfile withMaxOfflineDuration(@Nullable Integer maxOfflineDuration) {
    setMaxOfflineDuration(maxOfflineDuration);
    return this;
  }

  /**
   * Gets charging schedule structure defines a list of charging periods, as used in:
   * NotifyEVChargingScheduleRequest and ChargingProfileType. When used in a
   * NotifyEVChargingScheduleRequest only duration and chargingSchedulePeriod are relevant and
   * chargingRateUnit must be 'W'.
   *
   * @return Charging schedule structure defines a list of charging periods, as used in:
   *     NotifyEVChargingScheduleRequest and ChargingProfileType
   */
  public ChargingSchedule[] getChargingSchedule() {
    return chargingSchedule;
  }

  /**
   * Sets charging schedule structure defines a list of charging periods, as used in:
   * NotifyEVChargingScheduleRequest and ChargingProfileType. When used in a
   * NotifyEVChargingScheduleRequest only duration and chargingSchedulePeriod are relevant and
   * chargingRateUnit must be 'W'.
   *
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: NotifyEVChargingScheduleRequest and ChargingProfileType
   */
  public void setChargingSchedule(ChargingSchedule[] chargingSchedule) {
    if (!isValidChargingSchedule(chargingSchedule)) {
      throw new PropertyConstraintException(chargingSchedule, "chargingSchedule is invalid");
    }
    this.chargingSchedule = chargingSchedule;
  }

  /**
   * Returns whether the given chargingSchedule is valid
   *
   * @param chargingSchedule the chargingSchedule to check the validity of
   * @return {@code true} if chargingSchedule is valid, {@code false} if not
   */
  private boolean isValidChargingSchedule(ChargingSchedule[] chargingSchedule) {
    return chargingSchedule != null
        && chargingSchedule.length >= 1
        && chargingSchedule.length <= 3
        && Arrays.stream(chargingSchedule).allMatch(item -> item.validate());
  }

  /**
   * Gets when set to true this charging profile will not be valid anymore after being offline for
   * more than maxOfflineDuration.
   *
   * @return When set to true this charging profile will not be valid anymore after being offline
   *     for more than maxOfflineDuration
   */
  @Nullable
  public Boolean getInvalidAfterOfflineDuration() {
    return invalidAfterOfflineDuration;
  }

  /**
   * Sets when set to true this charging profile will not be valid anymore after being offline for
   * more than maxOfflineDuration.
   *
   * @param invalidAfterOfflineDuration When set to true this charging profile will not be valid
   *     anymore after being offline for more than maxOfflineDuration
   */
  public void setInvalidAfterOfflineDuration(@Nullable Boolean invalidAfterOfflineDuration) {
    this.invalidAfterOfflineDuration = invalidAfterOfflineDuration;
  }

  /**
   * Adds when set to true this charging profile will not be valid anymore after being offline for
   * more than maxOfflineDuration.
   *
   * @param invalidAfterOfflineDuration When set to true this charging profile will not be valid
   *     anymore after being offline for more than maxOfflineDuration
   * @return this
   */
  public ChargingProfile withInvalidAfterOfflineDuration(
      @Nullable Boolean invalidAfterOfflineDuration) {
    setInvalidAfterOfflineDuration(invalidAfterOfflineDuration);
    return this;
  }

  /**
   * Gets interval in seconds after receipt of last update, when to request a profile update by
   * sending a PullDynamicScheduleUpdateRequest message.
   *
   * @return Interval in seconds after receipt of last update, when to request a profile update by
   *     sending a PullDynamicScheduleUpdateRequest message
   */
  @Nullable
  public Integer getDynUpdateInterval() {
    return dynUpdateInterval;
  }

  /**
   * Sets interval in seconds after receipt of last update, when to request a profile update by
   * sending a PullDynamicScheduleUpdateRequest message.
   *
   * @param dynUpdateInterval Interval in seconds after receipt of last update, when to request a
   *     profile update by sending a PullDynamicScheduleUpdateRequest message
   */
  public void setDynUpdateInterval(@Nullable Integer dynUpdateInterval) {
    this.dynUpdateInterval = dynUpdateInterval;
  }

  /**
   * Adds interval in seconds after receipt of last update, when to request a profile update by
   * sending a PullDynamicScheduleUpdateRequest message.
   *
   * @param dynUpdateInterval Interval in seconds after receipt of last update, when to request a
   *     profile update by sending a PullDynamicScheduleUpdateRequest message
   * @return this
   */
  public ChargingProfile withDynUpdateInterval(@Nullable Integer dynUpdateInterval) {
    setDynUpdateInterval(dynUpdateInterval);
    return this;
  }

  /**
   * Gets time at which limits or setpoints in this charging profile were last updated by a
   * PullDynamicScheduleUpdateRequest or UpdateDynamicScheduleRequest or by an external actor.
   *
   * @return Time at which limits or setpoints in this charging profile were last updated by a
   *     PullDynamicScheduleUpdateRequest or UpdateDynamicScheduleRequest or by an external actor
   */
  @Nullable
  public ZonedDateTime getDynUpdateTime() {
    return dynUpdateTime;
  }

  /**
   * Sets time at which limits or setpoints in this charging profile were last updated by a
   * PullDynamicScheduleUpdateRequest or UpdateDynamicScheduleRequest or by an external actor.
   *
   * @param dynUpdateTime Time at which limits or setpoints in this charging profile were last
   *     updated by a PullDynamicScheduleUpdateRequest or UpdateDynamicScheduleRequest or by an
   *     external actor
   */
  public void setDynUpdateTime(@Nullable ZonedDateTime dynUpdateTime) {
    this.dynUpdateTime = dynUpdateTime;
  }

  /**
   * Adds time at which limits or setpoints in this charging profile were last updated by a
   * PullDynamicScheduleUpdateRequest or UpdateDynamicScheduleRequest or by an external actor.
   *
   * @param dynUpdateTime Time at which limits or setpoints in this charging profile were last
   *     updated by a PullDynamicScheduleUpdateRequest or UpdateDynamicScheduleRequest or by an
   *     external actor
   * @return this
   */
  public ChargingProfile withDynUpdateTime(@Nullable ZonedDateTime dynUpdateTime) {
    setDynUpdateTime(dynUpdateTime);
    return this;
  }

  /**
   * Gets ISO 15118-20 signature for all price schedules in chargingSchedules.
   *
   * @return ISO 15118-20 signature for all price schedules in chargingSchedules
   */
  @Nullable
  public String getPriceScheduleSignature() {
    return priceScheduleSignature;
  }

  /**
   * Sets ISO 15118-20 signature for all price schedules in chargingSchedules.
   *
   * @param priceScheduleSignature ISO 15118-20 signature for all price schedules in
   *     chargingSchedules
   */
  public void setPriceScheduleSignature(@Nullable String priceScheduleSignature) {
    if (!isValidPriceScheduleSignature(priceScheduleSignature)) {
      throw new PropertyConstraintException(
          priceScheduleSignature, "priceScheduleSignature is invalid");
    }
    this.priceScheduleSignature = priceScheduleSignature;
  }

  /**
   * Returns whether the given priceScheduleSignature is valid
   *
   * @param priceScheduleSignature the priceScheduleSignature to check the validity of
   * @return {@code true} if priceScheduleSignature is valid, {@code false} if not
   */
  private boolean isValidPriceScheduleSignature(@Nullable String priceScheduleSignature) {
    return priceScheduleSignature == null || priceScheduleSignature.length() <= 256;
  }

  /**
   * Adds ISO 15118-20 signature for all price schedules in chargingSchedules.
   *
   * @param priceScheduleSignature ISO 15118-20 signature for all price schedules in
   *     chargingSchedules
   * @return this
   */
  public ChargingProfile withPriceScheduleSignature(@Nullable String priceScheduleSignature) {
    setPriceScheduleSignature(priceScheduleSignature);
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
  public ChargingProfile withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidId(id)
        && isValidStackLevel(stackLevel)
        && isValidChargingProfilePurpose(chargingProfilePurpose)
        && isValidChargingProfileKind(chargingProfileKind)
        && isValidTransactionId(transactionId)
        && isValidChargingSchedule(chargingSchedule)
        && isValidPriceScheduleSignature(priceScheduleSignature)
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
    ChargingProfile that = (ChargingProfile) o;
    return Objects.equals(id, that.id)
        && Objects.equals(stackLevel, that.stackLevel)
        && Objects.equals(chargingProfilePurpose, that.chargingProfilePurpose)
        && Objects.equals(chargingProfileKind, that.chargingProfileKind)
        && Objects.equals(recurrencyKind, that.recurrencyKind)
        && Objects.equals(validFrom, that.validFrom)
        && Objects.equals(validTo, that.validTo)
        && Objects.equals(transactionId, that.transactionId)
        && Objects.equals(maxOfflineDuration, that.maxOfflineDuration)
        && Arrays.equals(chargingSchedule, that.chargingSchedule)
        && Objects.equals(invalidAfterOfflineDuration, that.invalidAfterOfflineDuration)
        && Objects.equals(dynUpdateInterval, that.dynUpdateInterval)
        && Objects.equals(dynUpdateTime, that.dynUpdateTime)
        && Objects.equals(priceScheduleSignature, that.priceScheduleSignature)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        stackLevel,
        chargingProfilePurpose,
        chargingProfileKind,
        recurrencyKind,
        validFrom,
        validTo,
        transactionId,
        maxOfflineDuration,
        Arrays.hashCode(chargingSchedule),
        invalidAfterOfflineDuration,
        dynUpdateInterval,
        dynUpdateTime,
        priceScheduleSignature,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("stackLevel", stackLevel)
        .add("chargingProfilePurpose", chargingProfilePurpose)
        .add("chargingProfileKind", chargingProfileKind)
        .add("recurrencyKind", recurrencyKind)
        .add("validFrom", validFrom)
        .add("validTo", validTo)
        .add("transactionId", transactionId)
        .add("maxOfflineDuration", maxOfflineDuration)
        .add("chargingSchedule", chargingSchedule)
        .add("invalidAfterOfflineDuration", invalidAfterOfflineDuration)
        .add("dynUpdateInterval", dynUpdateInterval)
        .add("dynUpdateTime", dynUpdateTime)
        .add("priceScheduleSignature", priceScheduleSignature)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
