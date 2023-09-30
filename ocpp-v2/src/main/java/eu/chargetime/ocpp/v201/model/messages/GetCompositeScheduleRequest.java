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
import eu.chargetime.ocpp.v201.model.types.ChargingRateUnitEnum;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetCompositeScheduleRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetCompositeScheduleRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Length of the requested schedule in seconds. */
  private Integer duration;

  /** Can be used to force a power or current profile. */
  @Nullable private ChargingRateUnitEnum chargingRateUnit;

  /**
   * The ID of the EVSE for which the schedule is requested. When evseid=0, the Charging Station
   * will calculate the expected consumption for the grid connection.
   */
  private Integer evseId;

  /**
   * Constructor for the GetCompositeScheduleRequest class
   *
   * @param duration Length of the requested schedule in seconds.
   * @param evseId The ID of the EVSE for which the schedule is requested. When evseid=0, the
   *     Charging Station will calculate the expected consumption for the grid connection.
   */
  public GetCompositeScheduleRequest(Integer duration, Integer evseId) {
    setDuration(duration);
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
  public GetCompositeScheduleRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets length of the requested schedule in seconds.
   *
   * @return Length of the requested schedule in seconds
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets length of the requested schedule in seconds.
   *
   * @param duration Length of the requested schedule in seconds
   */
  public void setDuration(Integer duration) {
    if (!isValidDuration(duration)) {
      throw new PropertyConstraintException(duration, "duration is invalid");
    }
    this.duration = duration;
  }

  /**
   * Returns whether the given duration is valid
   *
   * @param duration the duration to check the validity of
   * @return {@code true} if duration is valid, {@code false} if not
   */
  private boolean isValidDuration(Integer duration) {
    return duration != null;
  }

  /**
   * Gets can be used to force a power or current profile.
   *
   * @return Can be used to force a power or current profile
   */
  @Nullable
  public ChargingRateUnitEnum getChargingRateUnit() {
    return chargingRateUnit;
  }

  /**
   * Sets can be used to force a power or current profile.
   *
   * @param chargingRateUnit Can be used to force a power or current profile
   */
  public void setChargingRateUnit(@Nullable ChargingRateUnitEnum chargingRateUnit) {
    this.chargingRateUnit = chargingRateUnit;
  }

  /**
   * Adds can be used to force a power or current profile.
   *
   * @param chargingRateUnit Can be used to force a power or current profile
   * @return this
   */
  public GetCompositeScheduleRequest withChargingRateUnit(
      @Nullable ChargingRateUnitEnum chargingRateUnit) {
    setChargingRateUnit(chargingRateUnit);
    return this;
  }

  /**
   * Gets the ID of the EVSE for which the schedule is requested. When evseid=0, the Charging
   * Station will calculate the expected consumption for the grid connection.
   *
   * @return The ID of the EVSE for which the schedule is requested
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the ID of the EVSE for which the schedule is requested. When evseid=0, the Charging
   * Station will calculate the expected consumption for the grid connection.
   *
   * @param evseId The ID of the EVSE for which the schedule is requested
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
    return isValidCustomData(customData) && isValidDuration(duration) && isValidEvseId(evseId);
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
    GetCompositeScheduleRequest that = (GetCompositeScheduleRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(duration, that.duration)
        && Objects.equals(chargingRateUnit, that.chargingRateUnit)
        && Objects.equals(evseId, that.evseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, duration, chargingRateUnit, evseId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("duration", duration)
        .add("chargingRateUnit", chargingRateUnit)
        .add("evseId", evseId)
        .add("isValid", validate())
        .toString();
  }
}
