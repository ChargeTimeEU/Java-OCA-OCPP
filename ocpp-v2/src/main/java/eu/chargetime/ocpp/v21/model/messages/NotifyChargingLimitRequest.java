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
import eu.chargetime.ocpp.v21.model.types.ChargingLimit;
import eu.chargetime.ocpp.v21.model.types.ChargingSchedule;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyChargingLimitRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class NotifyChargingLimitRequest extends RequestWithId {
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
  @Nullable private ChargingSchedule[] chargingSchedule;

  /**
   * The EVSE to which the charging limit is set. If absent or when zero, it applies to the entire
   * Charging Station.
   */
  @Nullable private Integer evseId;

  /** chargingLimit */
  private ChargingLimit chargingLimit;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the NotifyChargingLimitRequest class
   *
   * @param chargingLimit chargingLimit
   */
  public NotifyChargingLimitRequest(ChargingLimit chargingLimit) {
    setChargingLimit(chargingLimit);
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
  @Nullable
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
  public void setChargingSchedule(@Nullable ChargingSchedule[] chargingSchedule) {
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
  private boolean isValidChargingSchedule(@Nullable ChargingSchedule[] chargingSchedule) {
    return chargingSchedule == null
        || (chargingSchedule.length >= 1
            && Arrays.stream(chargingSchedule).allMatch(item -> item.validate()));
  }

  /**
   * Adds charging schedule structure defines a list of charging periods, as used in:
   * NotifyEVChargingScheduleRequest and ChargingProfileType. When used in a
   * NotifyEVChargingScheduleRequest only duration and chargingSchedulePeriod are relevant and
   * chargingRateUnit must be 'W'.
   *
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: NotifyEVChargingScheduleRequest and ChargingProfileType
   * @return this
   */
  public NotifyChargingLimitRequest withChargingSchedule(
      @Nullable ChargingSchedule[] chargingSchedule) {
    setChargingSchedule(chargingSchedule);
    return this;
  }

  /**
   * Gets the EVSE to which the charging limit is set. If absent or when zero, it applies to the
   * entire Charging Station.
   *
   * @return The EVSE to which the charging limit is set
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the EVSE to which the charging limit is set. If absent or when zero, it applies to the
   * entire Charging Station.
   *
   * @param evseId The EVSE to which the charging limit is set
   */
  public void setEvseId(@Nullable Integer evseId) {
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
  private boolean isValidEvseId(@Nullable Integer evseId) {
    return evseId == null || (evseId >= 0);
  }

  /**
   * Adds the EVSE to which the charging limit is set. If absent or when zero, it applies to the
   * entire Charging Station.
   *
   * @param evseId The EVSE to which the charging limit is set
   * @return this
   */
  public NotifyChargingLimitRequest withEvseId(@Nullable Integer evseId) {
    setEvseId(evseId);
    return this;
  }

  /**
   * Gets chargingLimit
   *
   * @return chargingLimit
   */
  public ChargingLimit getChargingLimit() {
    return chargingLimit;
  }

  /**
   * Sets chargingLimit
   *
   * @param chargingLimit chargingLimit
   */
  public void setChargingLimit(ChargingLimit chargingLimit) {
    if (!isValidChargingLimit(chargingLimit)) {
      throw new PropertyConstraintException(chargingLimit, "chargingLimit is invalid");
    }
    this.chargingLimit = chargingLimit;
  }

  /**
   * Returns whether the given chargingLimit is valid
   *
   * @param chargingLimit the chargingLimit to check the validity of
   * @return {@code true} if chargingLimit is valid, {@code false} if not
   */
  private boolean isValidChargingLimit(ChargingLimit chargingLimit) {
    return chargingLimit != null && chargingLimit.validate();
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
  public NotifyChargingLimitRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidChargingSchedule(chargingSchedule)
        && isValidEvseId(evseId)
        && isValidChargingLimit(chargingLimit)
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
    NotifyChargingLimitRequest that = (NotifyChargingLimitRequest) o;
    return Arrays.equals(chargingSchedule, that.chargingSchedule)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(chargingLimit, that.chargingLimit)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(chargingSchedule), evseId, chargingLimit, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("chargingSchedule", chargingSchedule)
        .add("evseId", evseId)
        .add("chargingLimit", chargingLimit)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
