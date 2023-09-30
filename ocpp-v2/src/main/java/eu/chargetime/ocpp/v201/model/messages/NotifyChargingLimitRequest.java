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
import eu.chargetime.ocpp.v201.model.types.ChargingLimit;
import eu.chargetime.ocpp.v201.model.types.ChargingSchedule;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyChargingLimitRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class NotifyChargingLimitRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Charging Schedule
   *
   * <p>Charging schedule structure defines a list of charging periods, as used in:
   * GetCompositeSchedule.conf and ChargingProfile.
   */
  @Nullable private ChargingSchedule[] chargingSchedule;

  /**
   * The charging schedule contained in this notification applies to an EVSE. evseId must be greater
   * than 0.
   */
  @Nullable private Integer evseId;

  /** Charging Limit */
  private ChargingLimit chargingLimit;

  /**
   * Constructor for the NotifyChargingLimitRequest class
   *
   * @param chargingLimit Charging Limit
   */
  public NotifyChargingLimitRequest(ChargingLimit chargingLimit) {
    setChargingLimit(chargingLimit);
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

  /**
   * Gets charging schedule structure defines a list of charging periods, as used in:
   * GetCompositeSchedule.conf and ChargingProfile.
   *
   * @return Charging schedule structure defines a list of charging periods, as used in:
   *     GetCompositeSchedule.conf and ChargingProfile
   */
  @Nullable
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
   * GetCompositeSchedule.conf and ChargingProfile.
   *
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: GetCompositeSchedule.conf and ChargingProfile
   * @return this
   */
  public NotifyChargingLimitRequest withChargingSchedule(
      @Nullable ChargingSchedule[] chargingSchedule) {
    setChargingSchedule(chargingSchedule);
    return this;
  }

  /**
   * Gets the charging schedule contained in this notification applies to an EVSE. evseId must be
   * greater than 0.
   *
   * @return The charging schedule contained in this notification applies to an EVSE
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the charging schedule contained in this notification applies to an EVSE. evseId must be
   * greater than 0.
   *
   * @param evseId The charging schedule contained in this notification applies to an EVSE
   */
  public void setEvseId(@Nullable Integer evseId) {
    this.evseId = evseId;
  }

  /**
   * Adds the charging schedule contained in this notification applies to an EVSE. evseId must be
   * greater than 0.
   *
   * @param evseId The charging schedule contained in this notification applies to an EVSE
   * @return this
   */
  public NotifyChargingLimitRequest withEvseId(@Nullable Integer evseId) {
    setEvseId(evseId);
    return this;
  }

  /**
   * Gets charging Limit
   *
   * @return Charging Limit
   */
  public ChargingLimit getChargingLimit() {
    return chargingLimit;
  }

  /**
   * Sets charging Limit
   *
   * @param chargingLimit Charging Limit
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

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidChargingSchedule(chargingSchedule)
        && isValidChargingLimit(chargingLimit);
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
    return Objects.equals(customData, that.customData)
        && Arrays.equals(chargingSchedule, that.chargingSchedule)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(chargingLimit, that.chargingLimit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, Arrays.hashCode(chargingSchedule), evseId, chargingLimit);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("chargingSchedule", chargingSchedule)
        .add("evseId", evseId)
        .add("chargingLimit", chargingLimit)
        .add("isValid", validate())
        .toString();
  }
}
