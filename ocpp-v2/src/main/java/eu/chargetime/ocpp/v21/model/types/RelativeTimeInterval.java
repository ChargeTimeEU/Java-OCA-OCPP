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
import java.util.Objects;
import javax.annotation.Nullable;

/** RelativeTimeIntervalType */
public final class RelativeTimeInterval {
  /** Start of the interval, in seconds from NOW. */
  private Integer start;

  /** Duration of the interval, in seconds. */
  @Nullable private Integer duration;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the RelativeTimeInterval class
   *
   * @param start Start of the interval, in seconds from NOW.
   */
  public RelativeTimeInterval(Integer start) {
    setStart(start);
  }

  /**
   * Gets start of the interval, in seconds from NOW.
   *
   * @return Start of the interval, in seconds from NOW
   */
  public Integer getStart() {
    return start;
  }

  /**
   * Sets start of the interval, in seconds from NOW.
   *
   * @param start Start of the interval, in seconds from NOW
   */
  public void setStart(Integer start) {
    if (!isValidStart(start)) {
      throw new PropertyConstraintException(start, "start is invalid");
    }
    this.start = start;
  }

  /**
   * Returns whether the given start is valid
   *
   * @param start the start to check the validity of
   * @return {@code true} if start is valid, {@code false} if not
   */
  private boolean isValidStart(Integer start) {
    return start != null;
  }

  /**
   * Gets duration of the interval, in seconds.
   *
   * @return Duration of the interval, in seconds
   */
  @Nullable
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets duration of the interval, in seconds.
   *
   * @param duration Duration of the interval, in seconds
   */
  public void setDuration(@Nullable Integer duration) {
    this.duration = duration;
  }

  /**
   * Adds duration of the interval, in seconds.
   *
   * @param duration Duration of the interval, in seconds
   * @return this
   */
  public RelativeTimeInterval withDuration(@Nullable Integer duration) {
    setDuration(duration);
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
  public RelativeTimeInterval withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidStart(start) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelativeTimeInterval that = (RelativeTimeInterval) o;
    return Objects.equals(start, that.start)
        && Objects.equals(duration, that.duration)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, duration, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("start", start)
        .add("duration", duration)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
