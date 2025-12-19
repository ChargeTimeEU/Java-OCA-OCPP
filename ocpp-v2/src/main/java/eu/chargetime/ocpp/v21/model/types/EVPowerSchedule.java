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

/** Schedule of EV energy offer. */
public final class EVPowerSchedule {
  /**
   * An entry in schedule of the energy amount over time that EV is willing to discharge. A negative
   * value indicates the willingness to discharge under specific conditions, a positive value
   * indicates that the EV currently is not able to offer energy to discharge.
   */
  private EVPowerScheduleEntry[] evPowerScheduleEntries;

  /** The time that defines the starting point for the EVEnergyOffer. */
  private ZonedDateTime timeAnchor;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the EVPowerSchedule class
   *
   * @param evPowerScheduleEntries An entry in schedule of the energy amount over time that EV is
   *     willing to discharge. A negative value indicates the willingness to discharge under
   *     specific conditions, a positive value indicates that the EV currently is not able to offer
   *     energy to discharge.
   * @param timeAnchor The time that defines the starting point for the EVEnergyOffer.
   */
  public EVPowerSchedule(EVPowerScheduleEntry[] evPowerScheduleEntries, ZonedDateTime timeAnchor) {
    setEvPowerScheduleEntries(evPowerScheduleEntries);
    setTimeAnchor(timeAnchor);
  }

  /**
   * Gets an entry in schedule of the energy amount over time that EV is willing to discharge. A
   * negative value indicates the willingness to discharge under specific conditions, a positive
   * value indicates that the EV currently is not able to offer energy to discharge.
   *
   * @return An entry in schedule of the energy amount over time that EV is willing to discharge
   */
  public EVPowerScheduleEntry[] getEvPowerScheduleEntries() {
    return evPowerScheduleEntries;
  }

  /**
   * Sets an entry in schedule of the energy amount over time that EV is willing to discharge. A
   * negative value indicates the willingness to discharge under specific conditions, a positive
   * value indicates that the EV currently is not able to offer energy to discharge.
   *
   * @param evPowerScheduleEntries An entry in schedule of the energy amount over time that EV is
   *     willing to discharge
   */
  public void setEvPowerScheduleEntries(EVPowerScheduleEntry[] evPowerScheduleEntries) {
    if (!isValidEvPowerScheduleEntries(evPowerScheduleEntries)) {
      throw new PropertyConstraintException(
          evPowerScheduleEntries, "evPowerScheduleEntries is invalid");
    }
    this.evPowerScheduleEntries = evPowerScheduleEntries;
  }

  /**
   * Returns whether the given evPowerScheduleEntries is valid
   *
   * @param evPowerScheduleEntries the evPowerScheduleEntries to check the validity of
   * @return {@code true} if evPowerScheduleEntries is valid, {@code false} if not
   */
  private boolean isValidEvPowerScheduleEntries(EVPowerScheduleEntry[] evPowerScheduleEntries) {
    return evPowerScheduleEntries != null
        && evPowerScheduleEntries.length >= 1
        && evPowerScheduleEntries.length <= 1024
        && Arrays.stream(evPowerScheduleEntries).allMatch(item -> item.validate());
  }

  /**
   * Gets the time that defines the starting point for the EVEnergyOffer.
   *
   * @return The time that defines the starting point for the EVEnergyOffer
   */
  public ZonedDateTime getTimeAnchor() {
    return timeAnchor;
  }

  /**
   * Sets the time that defines the starting point for the EVEnergyOffer.
   *
   * @param timeAnchor The time that defines the starting point for the EVEnergyOffer
   */
  public void setTimeAnchor(ZonedDateTime timeAnchor) {
    if (!isValidTimeAnchor(timeAnchor)) {
      throw new PropertyConstraintException(timeAnchor, "timeAnchor is invalid");
    }
    this.timeAnchor = timeAnchor;
  }

  /**
   * Returns whether the given timeAnchor is valid
   *
   * @param timeAnchor the timeAnchor to check the validity of
   * @return {@code true} if timeAnchor is valid, {@code false} if not
   */
  private boolean isValidTimeAnchor(ZonedDateTime timeAnchor) {
    return timeAnchor != null;
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
  public EVPowerSchedule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidEvPowerScheduleEntries(evPowerScheduleEntries)
        && isValidTimeAnchor(timeAnchor)
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
    EVPowerSchedule that = (EVPowerSchedule) o;
    return Arrays.equals(evPowerScheduleEntries, that.evPowerScheduleEntries)
        && Objects.equals(timeAnchor, that.timeAnchor)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(evPowerScheduleEntries), timeAnchor, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evPowerScheduleEntries", evPowerScheduleEntries)
        .add("timeAnchor", timeAnchor)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
