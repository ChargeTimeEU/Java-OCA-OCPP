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
import eu.chargetime.ocpp.v21.model.types.ChargingSchedule;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyEVChargingScheduleRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class NotifyEVChargingScheduleRequest extends RequestWithId {
  /** Periods contained in the charging profile are relative to this point in time. */
  private ZonedDateTime timeBase;

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
  private ChargingSchedule chargingSchedule;

  /**
   * The charging schedule contained in this notification applies to an EVSE. EvseId must be greater
   * than 0.
   */
  private Integer evseId;

  /** Id of the chargingSchedule that EV selected from the provided ChargingProfile. */
  @Nullable private Integer selectedChargingScheduleId;

  /**
   * True when power tolerance is accepted by EV. This value is taken from
   * EVPowerProfile.PowerToleranceAcceptance in the ISO 15118-20 PowerDeliverReq message..
   */
  @Nullable private Boolean powerToleranceAcceptance;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the NotifyEVChargingScheduleRequest class
   *
   * @param timeBase Periods contained in the charging profile are relative to this point in time.
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: NotifyEVChargingScheduleRequest and ChargingProfileType. When used in a
   *     NotifyEVChargingScheduleRequest only duration and chargingSchedulePeriod are relevant and
   *     chargingRateUnit must be 'W'.
   * @param evseId The charging schedule contained in this notification applies to an EVSE. EvseId
   *     must be greater than 0.
   */
  public NotifyEVChargingScheduleRequest(
      ZonedDateTime timeBase, ChargingSchedule chargingSchedule, Integer evseId) {
    setTimeBase(timeBase);
    setChargingSchedule(chargingSchedule);
    setEvseId(evseId);
  }

  /**
   * Gets periods contained in the charging profile are relative to this point in time.
   *
   * @return Periods contained in the charging profile are relative to this point in time
   */
  public ZonedDateTime getTimeBase() {
    return timeBase;
  }

  /**
   * Sets periods contained in the charging profile are relative to this point in time.
   *
   * @param timeBase Periods contained in the charging profile are relative to this point in time
   */
  public void setTimeBase(ZonedDateTime timeBase) {
    if (!isValidTimeBase(timeBase)) {
      throw new PropertyConstraintException(timeBase, "timeBase is invalid");
    }
    this.timeBase = timeBase;
  }

  /**
   * Returns whether the given timeBase is valid
   *
   * @param timeBase the timeBase to check the validity of
   * @return {@code true} if timeBase is valid, {@code false} if not
   */
  private boolean isValidTimeBase(ZonedDateTime timeBase) {
    return timeBase != null;
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
  public ChargingSchedule getChargingSchedule() {
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
  public void setChargingSchedule(ChargingSchedule chargingSchedule) {
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
  private boolean isValidChargingSchedule(ChargingSchedule chargingSchedule) {
    return chargingSchedule != null && chargingSchedule.validate();
  }

  /**
   * Gets the charging schedule contained in this notification applies to an EVSE. EvseId must be
   * greater than 0.
   *
   * @return The charging schedule contained in this notification applies to an EVSE
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the charging schedule contained in this notification applies to an EVSE. EvseId must be
   * greater than 0.
   *
   * @param evseId The charging schedule contained in this notification applies to an EVSE
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
    return evseId != null && evseId >= 1;
  }

  /**
   * Gets id of the chargingSchedule that EV selected from the provided ChargingProfile.
   *
   * @return Id of the chargingSchedule that EV selected from the provided ChargingProfile
   */
  @Nullable
  public Integer getSelectedChargingScheduleId() {
    return selectedChargingScheduleId;
  }

  /**
   * Sets id of the chargingSchedule that EV selected from the provided ChargingProfile.
   *
   * @param selectedChargingScheduleId Id of the chargingSchedule that EV selected from the provided
   *     ChargingProfile
   */
  public void setSelectedChargingScheduleId(@Nullable Integer selectedChargingScheduleId) {
    if (!isValidSelectedChargingScheduleId(selectedChargingScheduleId)) {
      throw new PropertyConstraintException(
          selectedChargingScheduleId, "selectedChargingScheduleId is invalid");
    }
    this.selectedChargingScheduleId = selectedChargingScheduleId;
  }

  /**
   * Returns whether the given selectedChargingScheduleId is valid
   *
   * @param selectedChargingScheduleId the selectedChargingScheduleId to check the validity of
   * @return {@code true} if selectedChargingScheduleId is valid, {@code false} if not
   */
  private boolean isValidSelectedChargingScheduleId(@Nullable Integer selectedChargingScheduleId) {
    return selectedChargingScheduleId == null || (selectedChargingScheduleId >= 0);
  }

  /**
   * Adds id of the chargingSchedule that EV selected from the provided ChargingProfile.
   *
   * @param selectedChargingScheduleId Id of the chargingSchedule that EV selected from the provided
   *     ChargingProfile
   * @return this
   */
  public NotifyEVChargingScheduleRequest withSelectedChargingScheduleId(
      @Nullable Integer selectedChargingScheduleId) {
    setSelectedChargingScheduleId(selectedChargingScheduleId);
    return this;
  }

  /**
   * Gets true when power tolerance is accepted by EV. This value is taken from
   * EVPowerProfile.PowerToleranceAcceptance in the ISO 15118-20 PowerDeliverReq message..
   *
   * @return True when power tolerance is accepted by EV
   */
  @Nullable
  public Boolean getPowerToleranceAcceptance() {
    return powerToleranceAcceptance;
  }

  /**
   * Sets true when power tolerance is accepted by EV. This value is taken from
   * EVPowerProfile.PowerToleranceAcceptance in the ISO 15118-20 PowerDeliverReq message..
   *
   * @param powerToleranceAcceptance True when power tolerance is accepted by EV
   */
  public void setPowerToleranceAcceptance(@Nullable Boolean powerToleranceAcceptance) {
    this.powerToleranceAcceptance = powerToleranceAcceptance;
  }

  /**
   * Adds true when power tolerance is accepted by EV. This value is taken from
   * EVPowerProfile.PowerToleranceAcceptance in the ISO 15118-20 PowerDeliverReq message..
   *
   * @param powerToleranceAcceptance True when power tolerance is accepted by EV
   * @return this
   */
  public NotifyEVChargingScheduleRequest withPowerToleranceAcceptance(
      @Nullable Boolean powerToleranceAcceptance) {
    setPowerToleranceAcceptance(powerToleranceAcceptance);
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
  public NotifyEVChargingScheduleRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidTimeBase(timeBase)
        && isValidChargingSchedule(chargingSchedule)
        && isValidEvseId(evseId)
        && isValidSelectedChargingScheduleId(selectedChargingScheduleId)
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
    NotifyEVChargingScheduleRequest that = (NotifyEVChargingScheduleRequest) o;
    return Objects.equals(timeBase, that.timeBase)
        && Objects.equals(chargingSchedule, that.chargingSchedule)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(selectedChargingScheduleId, that.selectedChargingScheduleId)
        && Objects.equals(powerToleranceAcceptance, that.powerToleranceAcceptance)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        timeBase,
        chargingSchedule,
        evseId,
        selectedChargingScheduleId,
        powerToleranceAcceptance,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("timeBase", timeBase)
        .add("chargingSchedule", chargingSchedule)
        .add("evseId", evseId)
        .add("selectedChargingScheduleId", selectedChargingScheduleId)
        .add("powerToleranceAcceptance", powerToleranceAcceptance)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
