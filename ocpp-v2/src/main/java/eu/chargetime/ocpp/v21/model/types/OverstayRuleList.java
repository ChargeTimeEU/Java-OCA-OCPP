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
public final class OverstayRuleList {
  /** Part of ISO 15118-20 price schedule. */
  @Nullable private RationalNumber overstayPowerThreshold;

  /** Part of ISO 15118-20 price schedule. */
  private OverstayRule[] overstayRule;

  /** Time till overstay is applied in seconds. */
  @Nullable private Integer overstayTimeThreshold;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the OverstayRuleList class
   *
   * @param overstayRule Part of ISO 15118-20 price schedule.
   */
  public OverstayRuleList(OverstayRule[] overstayRule) {
    setOverstayRule(overstayRule);
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  @Nullable
  public RationalNumber getOverstayPowerThreshold() {
    return overstayPowerThreshold;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param overstayPowerThreshold Part of ISO 15118-20 price schedule
   */
  public void setOverstayPowerThreshold(@Nullable RationalNumber overstayPowerThreshold) {
    if (!isValidOverstayPowerThreshold(overstayPowerThreshold)) {
      throw new PropertyConstraintException(
          overstayPowerThreshold, "overstayPowerThreshold is invalid");
    }
    this.overstayPowerThreshold = overstayPowerThreshold;
  }

  /**
   * Returns whether the given overstayPowerThreshold is valid
   *
   * @param overstayPowerThreshold the overstayPowerThreshold to check the validity of
   * @return {@code true} if overstayPowerThreshold is valid, {@code false} if not
   */
  private boolean isValidOverstayPowerThreshold(@Nullable RationalNumber overstayPowerThreshold) {
    return overstayPowerThreshold == null || overstayPowerThreshold.validate();
  }

  /**
   * Adds part of ISO 15118-20 price schedule.
   *
   * @param overstayPowerThreshold Part of ISO 15118-20 price schedule
   * @return this
   */
  public OverstayRuleList withOverstayPowerThreshold(
      @Nullable RationalNumber overstayPowerThreshold) {
    setOverstayPowerThreshold(overstayPowerThreshold);
    return this;
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  public OverstayRule[] getOverstayRule() {
    return overstayRule;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param overstayRule Part of ISO 15118-20 price schedule
   */
  public void setOverstayRule(OverstayRule[] overstayRule) {
    if (!isValidOverstayRule(overstayRule)) {
      throw new PropertyConstraintException(overstayRule, "overstayRule is invalid");
    }
    this.overstayRule = overstayRule;
  }

  /**
   * Returns whether the given overstayRule is valid
   *
   * @param overstayRule the overstayRule to check the validity of
   * @return {@code true} if overstayRule is valid, {@code false} if not
   */
  private boolean isValidOverstayRule(OverstayRule[] overstayRule) {
    return overstayRule != null
        && overstayRule.length >= 1
        && overstayRule.length <= 5
        && Arrays.stream(overstayRule).allMatch(item -> item.validate());
  }

  /**
   * Gets time till overstay is applied in seconds.
   *
   * @return Time till overstay is applied in seconds
   */
  @Nullable
  public Integer getOverstayTimeThreshold() {
    return overstayTimeThreshold;
  }

  /**
   * Sets time till overstay is applied in seconds.
   *
   * @param overstayTimeThreshold Time till overstay is applied in seconds
   */
  public void setOverstayTimeThreshold(@Nullable Integer overstayTimeThreshold) {
    this.overstayTimeThreshold = overstayTimeThreshold;
  }

  /**
   * Adds time till overstay is applied in seconds.
   *
   * @param overstayTimeThreshold Time till overstay is applied in seconds
   * @return this
   */
  public OverstayRuleList withOverstayTimeThreshold(@Nullable Integer overstayTimeThreshold) {
    setOverstayTimeThreshold(overstayTimeThreshold);
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
  public OverstayRuleList withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidOverstayPowerThreshold(overstayPowerThreshold)
        && isValidOverstayRule(overstayRule)
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
    OverstayRuleList that = (OverstayRuleList) o;
    return Objects.equals(overstayPowerThreshold, that.overstayPowerThreshold)
        && Arrays.equals(overstayRule, that.overstayRule)
        && Objects.equals(overstayTimeThreshold, that.overstayTimeThreshold)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        overstayPowerThreshold, Arrays.hashCode(overstayRule), overstayTimeThreshold, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("overstayPowerThreshold", overstayPowerThreshold)
        .add("overstayRule", overstayRule)
        .add("overstayTimeThreshold", overstayTimeThreshold)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
