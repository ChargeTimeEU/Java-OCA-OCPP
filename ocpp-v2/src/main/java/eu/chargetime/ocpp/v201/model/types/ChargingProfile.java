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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Charging Profile
 *
 * <p>A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that
 * can be delivered per time interval.
 */
public final class ChargingProfile {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Identified Object. MRID. Numeric Identifier
   *
   * <p>Id of ChargingProfile.
   */
  private Integer id;

  /**
   * Charging Profile. Stack Level. Counter
   *
   * <p>Value determining level in hierarchy stack of profiles. Higher values have precedence over
   * lower values. Lowest level is 0.
   */
  private Integer stackLevel;

  /**
   * Charging Profile. Charging Profile Purpose. Charging Profile Purpose Code
   *
   * <p>The purpose of the schedule transferred by this profile
   */
  private ChargingProfilePurposeEnum chargingProfilePurpose;

  /**
   * Charging Profile. Charging Profile Kind. Charging Profile Kind Code
   *
   * <p>The kind of schedule.
   */
  private ChargingProfileKindEnum chargingProfileKind;

  /**
   * Charging Profile. Recurrency Kind. Recurrency Kind Code
   *
   * <p>The start point of a recurrence.
   */
  @Nullable private RecurrencyKindEnum recurrencyKind;

  /**
   * Charging Profile. Valid From. Date Time
   *
   * <p>Point in time at which the profile starts to be valid. If absent, the profile is valid as
   * soon as it is received by the Charging Station.
   */
  @Nullable private ZonedDateTime validFrom;

  /**
   * Charging Profile. Valid To. Date Time
   *
   * <p>Point in time at which the profile stops to be valid. If absent, the profile is valid until
   * it is replaced by another profile.
   */
  @Nullable private ZonedDateTime validTo;

  /**
   * Charging Schedule
   *
   * <p>Charging schedule structure defines a list of charging periods, as used in:
   * GetCompositeSchedule.conf and ChargingProfile.
   */
  private ChargingSchedule[] chargingSchedule;

  /**
   * SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is used
   * to match the profile to a specific transaction.
   */
  @Nullable private String transactionId;

  /**
   * Constructor for the ChargingProfile class
   *
   * @param id Id of ChargingProfile.
   * @param stackLevel Value determining level in hierarchy stack of profiles. Higher values have
   *     precedence over lower values. Lowest level is 0.
   * @param chargingProfilePurpose The purpose of the schedule transferred by this profile
   * @param chargingProfileKind The kind of schedule.
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: GetCompositeSchedule.conf and ChargingProfile.
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

  /**
   * Gets id of ChargingProfile.
   *
   * @return Id of ChargingProfile
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets id of ChargingProfile.
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
    return stackLevel != null;
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
   * Gets charging schedule structure defines a list of charging periods, as used in:
   * GetCompositeSchedule.conf and ChargingProfile.
   *
   * @return Charging schedule structure defines a list of charging periods, as used in:
   *     GetCompositeSchedule.conf and ChargingProfile
   */
  public ChargingSchedule[] getChargingSchedule() {
    return chargingSchedule;
  }

  /**
   * Sets charging schedule structure defines a list of charging periods, as used in:
   * GetCompositeSchedule.conf and ChargingProfile.
   *
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: GetCompositeSchedule.conf and ChargingProfile
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
   * Gets SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is
   * used to match the profile to a specific transaction.
   *
   * @return SHALL only be included if ChargingProfilePurpose is set to TxProfile
   */
  @Nullable
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is
   * used to match the profile to a specific transaction.
   *
   * @param transactionId SHALL only be included if ChargingProfilePurpose is set to TxProfile
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
   * Adds SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is
   * used to match the profile to a specific transaction.
   *
   * @param transactionId SHALL only be included if ChargingProfilePurpose is set to TxProfile
   * @return this
   */
  public ChargingProfile withTransactionId(@Nullable String transactionId) {
    setTransactionId(transactionId);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidId(id)
        && isValidStackLevel(stackLevel)
        && isValidChargingProfilePurpose(chargingProfilePurpose)
        && isValidChargingProfileKind(chargingProfileKind)
        && isValidChargingSchedule(chargingSchedule)
        && isValidTransactionId(transactionId);
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
    return Objects.equals(customData, that.customData)
        && Objects.equals(id, that.id)
        && Objects.equals(stackLevel, that.stackLevel)
        && Objects.equals(chargingProfilePurpose, that.chargingProfilePurpose)
        && Objects.equals(chargingProfileKind, that.chargingProfileKind)
        && Objects.equals(recurrencyKind, that.recurrencyKind)
        && Objects.equals(validFrom, that.validFrom)
        && Objects.equals(validTo, that.validTo)
        && Arrays.equals(chargingSchedule, that.chargingSchedule)
        && Objects.equals(transactionId, that.transactionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        id,
        stackLevel,
        chargingProfilePurpose,
        chargingProfileKind,
        recurrencyKind,
        validFrom,
        validTo,
        Arrays.hashCode(chargingSchedule),
        transactionId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("id", id)
        .add("stackLevel", stackLevel)
        .add("chargingProfilePurpose", chargingProfilePurpose)
        .add("chargingProfileKind", chargingProfileKind)
        .add("recurrencyKind", recurrencyKind)
        .add("validFrom", validFrom)
        .add("validTo", validTo)
        .add("chargingSchedule", chargingSchedule)
        .add("transactionId", transactionId)
        .add("isValid", validate())
        .toString();
  }
}
