package eu.chargetime.ocpp.model.core;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
 * Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
@XmlRootElement
@XmlType(
    propOrder = {
      "chargingProfileId",
      "transactionId",
      "stackLevel",
      "chargingProfilePurpose",
      "chargingProfileKind",
      "recurrencyKind",
      "validFrom",
      "validTo",
      "chargingSchedule"
    })
public class ChargingProfile implements Validatable {
  private Integer chargingProfileId;
  private Integer transactionId;
  private Integer stackLevel;
  private ChargingProfilePurposeType chargingProfilePurpose;
  private ChargingProfileKindType chargingProfileKind;
  private RecurrencyKindType recurrencyKind;
  private ZonedDateTime validFrom;
  private ZonedDateTime validTo;
  private ChargingSchedule chargingSchedule;

  /**
   * @deprecated use {@link #ChargingProfile(Integer, Integer, ChargingProfilePurposeType,
   *     ChargingProfileKindType, ChargingSchedule)} to be sure to set required fields
   */
  @Deprecated
  public ChargingProfile() {}

  /**
   * Handle required values
   *
   * @param chargingProfileId Integer, see {@link #setChargingProfileId(Integer)}
   * @param stackLevel Integer, see {@link #setStackLevel(Integer)}
   * @param chargingProfilePurpose the {@link ChargingProfilePurposeType}, see {@link
   *     #setChargingProfilePurpose(ChargingProfilePurposeType)}
   * @param chargingProfileKind the {@link ChargingProfileKindType}, see {@link
   *     #setChargingProfileKind(ChargingProfileKindType)}
   * @param chargingSchedule the {@link ChargingSchedule}, see {@link
   *     #setChargingSchedule(ChargingSchedule)}
   */
  public ChargingProfile(
      Integer chargingProfileId,
      Integer stackLevel,
      ChargingProfilePurposeType chargingProfilePurpose,
      ChargingProfileKindType chargingProfileKind,
      ChargingSchedule chargingSchedule) {
    this.chargingProfileId = chargingProfileId;
    this.stackLevel = stackLevel;
    this.chargingProfilePurpose = chargingProfilePurpose;
    this.chargingProfileKind = chargingProfileKind;
    this.chargingSchedule = chargingSchedule;
  }

  /**
   * Handle required values
   *
   * @param chargingProfileId Integer, see {@link #setChargingProfileId(Integer)}
   * @param stackLevel Integer, see {@link #setStackLevel(Integer)}
   * @param chargingProfilePurpose the {@link ChargingProfilePurposeType}, see {@link
   *     #setChargingProfilePurpose(ChargingProfilePurposeType)}
   * @param chargingProfileKind the {@link ChargingProfileKindType}, see {@link
   *     #setChargingProfileKind(ChargingProfileKindType)}
   * @deprecated use {@link #ChargingProfile(Integer, Integer, ChargingProfilePurposeType,
   *     ChargingProfileKindType, ChargingSchedule)} to be sure to set required fields
   */
  @Deprecated
  public ChargingProfile(
      Integer chargingProfileId,
      Integer stackLevel,
      ChargingProfilePurposeType chargingProfilePurpose,
      ChargingProfileKindType chargingProfileKind) {
    this.chargingProfileId = chargingProfileId;
    this.stackLevel = stackLevel;
    this.chargingProfilePurpose = chargingProfilePurpose;
    this.chargingProfileKind = chargingProfileKind;
  }

  @Override
  public boolean validate() {
    boolean valid = chargingProfileId != null;
    valid &= (stackLevel != null && stackLevel >= 0);
    valid &= chargingProfilePurpose != null;
    valid &=
        (transactionId == null || chargingProfilePurpose == ChargingProfilePurposeType.TxProfile);
    valid &= chargingProfileKind != null;
    valid &= (chargingSchedule != null && chargingSchedule.validate());
    return valid;
  }

  /**
   * Unique identifier for this profile
   *
   * @return identifier for this profile
   */
  public Integer getChargingProfileId() {
    return chargingProfileId;
  }

  /**
   * Required. Unique identifier for this profile
   *
   * @param chargingProfileId Integer
   */
  @XmlElement
  public void setChargingProfileId(Integer chargingProfileId) {
    if (chargingProfileId == null) {
      throw new PropertyConstraintException(null, "chargingProfileId must be present");
    }

    this.chargingProfileId = chargingProfileId;
  }

  /**
   * Only valid if ChargingProfilePurpose is set to TxProfile, the transactionId MAY be used to
   * match the profile to a specific transaction.
   *
   * @return the transactionId
   */
  public Integer getTransactionId() {
    return transactionId;
  }

  /**
   * Optional. Only valid if ChargingProfilePurpose is set to TxProfile, the transactionId MAY be
   * used to match the profile to a specific transaction.
   *
   * @param transactionId Integer
   */
  @XmlElement
  public void setTransactionId(Integer transactionId) {
    this.transactionId = transactionId;
  }

  /**
   * Value determining level in hierarchy stack of profiles. Higher values have precedence over
   * lower values. Lowest level is 0.
   *
   * @return level in hierarchy stack of profiles
   */
  public Integer getStackLevel() {
    return stackLevel;
  }

  /**
   * Required. Value determining level in hierarchy stack of profiles. Higher values have precedence
   * over lower values. Lowest level is 0.
   *
   * @param stackLevel Integer
   */
  @XmlElement
  public void setStackLevel(Integer stackLevel) {
    if (stackLevel == null || stackLevel < 0) {
      throw new PropertyConstraintException(stackLevel, "stackLevel must be >= 0");
    }

    this.stackLevel = stackLevel;
  }

  /**
   * Unique identifier for this profile.
   *
   * @return identifier for this profile
   */
  public ChargingProfilePurposeType getChargingProfilePurpose() {
    return chargingProfilePurpose;
  }

  /**
   * Required. Unique identifier for this profile.
   *
   * @param chargingProfilePurpose the {@link ChargingProfilePurposeType}
   */
  @XmlElement
  public void setChargingProfilePurpose(ChargingProfilePurposeType chargingProfilePurpose) {
    this.chargingProfilePurpose = chargingProfilePurpose;
  }

  /**
   * Unique identifier for this profile.
   *
   * @return identifier for this profile
   */
  @Deprecated
  public ChargingProfilePurposeType objChargingProfilePurpose() {
    return chargingProfilePurpose;
  }

  /**
   * Indicates the kind of schedule.
   *
   * @return kind of schedule
   */
  public ChargingProfileKindType getChargingProfileKind() {
    return chargingProfileKind;
  }

  /**
   * Required. Indicates the kind of schedule
   *
   * @param chargingProfileKind the {@link ChargingProfileKindType}
   */
  @XmlElement
  public void setChargingProfileKind(ChargingProfileKindType chargingProfileKind) {
    this.chargingProfileKind = chargingProfileKind;
  }

  /**
   * Indicates the kind of schedule.
   *
   * @return kind of schedule
   */
  @Deprecated
  public ChargingProfileKindType objChargingProfileKind() {
    return chargingProfileKind;
  }

  /**
   * Indicates the start point of a recurrence.
   *
   * @return start point of a recurrency
   */
  public RecurrencyKindType getRecurrencyKind() {
    return recurrencyKind;
  }

  /**
   * Required. Indicates the kind of schedule.
   *
   * @param recurrencyKind the {@link RecurrencyKindType}
   */
  @XmlElement
  public void setRecurrencyKind(RecurrencyKindType recurrencyKind) {
    this.recurrencyKind = recurrencyKind;
  }

  /**
   * Indicates the start point of a recurrence.
   *
   * @return start point of a recurrency
   */
  @Deprecated
  public RecurrencyKindType objRecurrencyKind() {
    return recurrencyKind;
  }

  /**
   * Point in time at which the profile starts to be valid. If absent, the profile is valid as soon
   * as it is received by the Charge Point.
   *
   * @return Point in time at which the profile starts to be valid
   */
  public ZonedDateTime getValidFrom() {
    return validFrom;
  }

  /**
   * Optional. Point in time at which the profile starts to be valid. If absent, the profile is
   * valid as soon as it is received by the Charge Point.
   *
   * @param validFrom the {@link ZonedDateTime}
   */
  @XmlElement
  public void setValidFrom(ZonedDateTime validFrom) {
    this.validFrom = validFrom;
  }

  /**
   * Point in time at which the profile starts to be valid. If absent, the profile is valid as soon
   * as it is received by the Charge Point.
   *
   * @return Point in time at which the profile starts to be valid
   */
  @Deprecated
  public ZonedDateTime objValidFrom() {
    return this.validFrom;
  }

  /**
   * Point in time at which the profile stops to be valid. If absent, the profile is valid until it
   * is replaced by another profile
   *
   * @return Point in time at which the profile stops to be valid
   */
  public ZonedDateTime getValidTo() {
    return validTo;
  }

  /**
   * Optional. Point in time at which the profile stops to be valid. If absent, the profile is valid
   * until it is replaced by another profile
   *
   * @param validTo the {@link ZonedDateTime}
   */
  @XmlElement
  public void setValidTo(ZonedDateTime validTo) {
    this.validTo = validTo;
  }

  /**
   * Point in time at which the profile stops to be valid. If absent, the profile is valid until it
   * is replaced by another profile
   *
   * @return Point in time at which the profile stops to be valid
   */
  @Deprecated
  public ZonedDateTime objValidTo() {
    return validTo;
  }

  /**
   * Contains limits for the available power or current over time.
   *
   * @return charging schedule
   */
  public ChargingSchedule getChargingSchedule() {
    return chargingSchedule;
  }

  /**
   * Required. Contains limits for the available power or current over time.
   *
   * @param chargingSchedule the {@link ChargingSchedule}
   */
  @XmlElement
  public void setChargingSchedule(ChargingSchedule chargingSchedule) {
    this.chargingSchedule = chargingSchedule;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ChargingProfile that = (ChargingProfile) o;
    return Objects.equals(chargingProfileId, that.chargingProfileId)
        && Objects.equals(transactionId, that.transactionId)
        && Objects.equals(stackLevel, that.stackLevel)
        && chargingProfilePurpose == that.chargingProfilePurpose
        && chargingProfileKind == that.chargingProfileKind
        && recurrencyKind == that.recurrencyKind
        && Objects.equals(validFrom, that.validFrom)
        && Objects.equals(validTo, that.validTo)
        && Objects.equals(chargingSchedule, that.chargingSchedule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        chargingProfileId,
        transactionId,
        stackLevel,
        chargingProfilePurpose,
        chargingProfileKind,
        recurrencyKind,
        validFrom,
        validTo,
        chargingSchedule);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("chargingProfileId", chargingProfileId)
        .add("transactionId", transactionId)
        .add("stackLevel", stackLevel)
        .add("chargingProfilePurpose", chargingProfilePurpose)
        .add("chargingProfileKind", chargingProfileKind)
        .add("recurrencyKind", recurrencyKind)
        .add("validFrom", validFrom)
        .add("validTo", validTo)
        .add("chargingSchedule", chargingSchedule)
        .add("isValid", validate())
        .toString();
  }
}
