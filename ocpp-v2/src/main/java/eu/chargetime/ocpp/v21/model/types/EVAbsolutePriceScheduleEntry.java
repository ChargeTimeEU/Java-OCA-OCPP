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

/** An entry in price schedule over time for which EV is willing to discharge. */
public final class EVAbsolutePriceScheduleEntry {
  /** The amount of seconds of this entry. */
  private Integer duration;

  /** An entry in price schedule over time for which EV is willing to discharge. */
  private EVPriceRule[] evPriceRule;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the EVAbsolutePriceScheduleEntry class
   *
   * @param duration The amount of seconds of this entry.
   * @param evPriceRule An entry in price schedule over time for which EV is willing to discharge.
   */
  public EVAbsolutePriceScheduleEntry(Integer duration, EVPriceRule[] evPriceRule) {
    setDuration(duration);
    setEvPriceRule(evPriceRule);
  }

  /**
   * Gets the amount of seconds of this entry.
   *
   * @return The amount of seconds of this entry
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets the amount of seconds of this entry.
   *
   * @param duration The amount of seconds of this entry
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
   * Gets an entry in price schedule over time for which EV is willing to discharge.
   *
   * @return An entry in price schedule over time for which EV is willing to discharge
   */
  public EVPriceRule[] getEvPriceRule() {
    return evPriceRule;
  }

  /**
   * Sets an entry in price schedule over time for which EV is willing to discharge.
   *
   * @param evPriceRule An entry in price schedule over time for which EV is willing to discharge
   */
  public void setEvPriceRule(EVPriceRule[] evPriceRule) {
    if (!isValidEvPriceRule(evPriceRule)) {
      throw new PropertyConstraintException(evPriceRule, "evPriceRule is invalid");
    }
    this.evPriceRule = evPriceRule;
  }

  /**
   * Returns whether the given evPriceRule is valid
   *
   * @param evPriceRule the evPriceRule to check the validity of
   * @return {@code true} if evPriceRule is valid, {@code false} if not
   */
  private boolean isValidEvPriceRule(EVPriceRule[] evPriceRule) {
    return evPriceRule != null
        && evPriceRule.length >= 1
        && evPriceRule.length <= 8
        && Arrays.stream(evPriceRule).allMatch(item -> item.validate());
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
  public EVAbsolutePriceScheduleEntry withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidDuration(duration)
        && isValidEvPriceRule(evPriceRule)
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
    EVAbsolutePriceScheduleEntry that = (EVAbsolutePriceScheduleEntry) o;
    return Objects.equals(duration, that.duration)
        && Arrays.equals(evPriceRule, that.evPriceRule)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(duration, Arrays.hashCode(evPriceRule), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("duration", duration)
        .add("evPriceRule", evPriceRule)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
