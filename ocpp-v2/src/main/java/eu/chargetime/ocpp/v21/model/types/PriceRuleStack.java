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

/** Part of ISO 15118-20 price schedule. */
public final class PriceRuleStack {
  /**
   * Duration of the stack of price rules. he amount of seconds that define the duration of the
   * given PriceRule(s).
   */
  private Integer duration;

  /** Part of ISO 15118-20 price schedule. */
  private PriceRule[] priceRule;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the PriceRuleStack class
   *
   * @param duration Duration of the stack of price rules. he amount of seconds that define the
   *     duration of the given PriceRule(s).
   * @param priceRule Part of ISO 15118-20 price schedule.
   */
  public PriceRuleStack(Integer duration, PriceRule[] priceRule) {
    setDuration(duration);
    setPriceRule(priceRule);
  }

  /**
   * Gets duration of the stack of price rules. he amount of seconds that define the duration of the
   * given PriceRule(s).
   *
   * @return Duration of the stack of price rules
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets duration of the stack of price rules. he amount of seconds that define the duration of the
   * given PriceRule(s).
   *
   * @param duration Duration of the stack of price rules
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
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  public PriceRule[] getPriceRule() {
    return priceRule;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param priceRule Part of ISO 15118-20 price schedule
   */
  public void setPriceRule(PriceRule[] priceRule) {
    if (!isValidPriceRule(priceRule)) {
      throw new PropertyConstraintException(priceRule, "priceRule is invalid");
    }
    this.priceRule = priceRule;
  }

  /**
   * Returns whether the given priceRule is valid
   *
   * @param priceRule the priceRule to check the validity of
   * @return {@code true} if priceRule is valid, {@code false} if not
   */
  private boolean isValidPriceRule(PriceRule[] priceRule) {
    return priceRule != null
        && priceRule.length >= 1
        && priceRule.length <= 8
        && Arrays.stream(priceRule).allMatch(item -> item.validate());
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
  public PriceRuleStack withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidDuration(duration)
        && isValidPriceRule(priceRule)
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
    PriceRuleStack that = (PriceRuleStack) o;
    return Objects.equals(duration, that.duration)
        && Arrays.equals(priceRule, that.priceRule)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(duration, Arrays.hashCode(priceRule), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("duration", duration)
        .add("priceRule", priceRule)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
