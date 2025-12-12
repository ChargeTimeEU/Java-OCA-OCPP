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

/** Part of ISO 15118-20 price schedule. */
public final class OverstayRule {
  /** Part of ISO 15118-20 price schedule. */
  private RationalNumber overstayFee;

  /** Human readable string to identify the overstay rule. */
  @Nullable private String overstayRuleDescription;

  /**
   * Time in seconds after trigger of the parent Overstay Rules for this particular fee to apply.
   */
  private Integer startTime;

  /** Time till overstay will be reapplied */
  private Integer overstayFeePeriod;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the OverstayRule class
   *
   * @param overstayFee Part of ISO 15118-20 price schedule.
   * @param startTime Time in seconds after trigger of the parent Overstay Rules for this particular
   *     fee to apply.
   * @param overstayFeePeriod Time till overstay will be reapplied
   */
  public OverstayRule(RationalNumber overstayFee, Integer startTime, Integer overstayFeePeriod) {
    setOverstayFee(overstayFee);
    setStartTime(startTime);
    setOverstayFeePeriod(overstayFeePeriod);
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  public RationalNumber getOverstayFee() {
    return overstayFee;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param overstayFee Part of ISO 15118-20 price schedule
   */
  public void setOverstayFee(RationalNumber overstayFee) {
    if (!isValidOverstayFee(overstayFee)) {
      throw new PropertyConstraintException(overstayFee, "overstayFee is invalid");
    }
    this.overstayFee = overstayFee;
  }

  /**
   * Returns whether the given overstayFee is valid
   *
   * @param overstayFee the overstayFee to check the validity of
   * @return {@code true} if overstayFee is valid, {@code false} if not
   */
  private boolean isValidOverstayFee(RationalNumber overstayFee) {
    return overstayFee != null && overstayFee.validate();
  }

  /**
   * Gets human readable string to identify the overstay rule.
   *
   * @return Human readable string to identify the overstay rule
   */
  @Nullable
  public String getOverstayRuleDescription() {
    return overstayRuleDescription;
  }

  /**
   * Sets human readable string to identify the overstay rule.
   *
   * @param overstayRuleDescription Human readable string to identify the overstay rule
   */
  public void setOverstayRuleDescription(@Nullable String overstayRuleDescription) {
    if (!isValidOverstayRuleDescription(overstayRuleDescription)) {
      throw new PropertyConstraintException(
          overstayRuleDescription, "overstayRuleDescription is invalid");
    }
    this.overstayRuleDescription = overstayRuleDescription;
  }

  /**
   * Returns whether the given overstayRuleDescription is valid
   *
   * @param overstayRuleDescription the overstayRuleDescription to check the validity of
   * @return {@code true} if overstayRuleDescription is valid, {@code false} if not
   */
  private boolean isValidOverstayRuleDescription(@Nullable String overstayRuleDescription) {
    return overstayRuleDescription == null || overstayRuleDescription.length() <= 32;
  }

  /**
   * Adds human readable string to identify the overstay rule.
   *
   * @param overstayRuleDescription Human readable string to identify the overstay rule
   * @return this
   */
  public OverstayRule withOverstayRuleDescription(@Nullable String overstayRuleDescription) {
    setOverstayRuleDescription(overstayRuleDescription);
    return this;
  }

  /**
   * Gets time in seconds after trigger of the parent Overstay Rules for this particular fee to
   * apply.
   *
   * @return Time in seconds after trigger of the parent Overstay Rules for this particular fee to
   *     apply
   */
  public Integer getStartTime() {
    return startTime;
  }

  /**
   * Sets time in seconds after trigger of the parent Overstay Rules for this particular fee to
   * apply.
   *
   * @param startTime Time in seconds after trigger of the parent Overstay Rules for this particular
   *     fee to apply
   */
  public void setStartTime(Integer startTime) {
    if (!isValidStartTime(startTime)) {
      throw new PropertyConstraintException(startTime, "startTime is invalid");
    }
    this.startTime = startTime;
  }

  /**
   * Returns whether the given startTime is valid
   *
   * @param startTime the startTime to check the validity of
   * @return {@code true} if startTime is valid, {@code false} if not
   */
  private boolean isValidStartTime(Integer startTime) {
    return startTime != null;
  }

  /**
   * Gets time till overstay will be reapplied
   *
   * @return Time till overstay will be reapplied
   */
  public Integer getOverstayFeePeriod() {
    return overstayFeePeriod;
  }

  /**
   * Sets time till overstay will be reapplied
   *
   * @param overstayFeePeriod Time till overstay will be reapplied
   */
  public void setOverstayFeePeriod(Integer overstayFeePeriod) {
    if (!isValidOverstayFeePeriod(overstayFeePeriod)) {
      throw new PropertyConstraintException(overstayFeePeriod, "overstayFeePeriod is invalid");
    }
    this.overstayFeePeriod = overstayFeePeriod;
  }

  /**
   * Returns whether the given overstayFeePeriod is valid
   *
   * @param overstayFeePeriod the overstayFeePeriod to check the validity of
   * @return {@code true} if overstayFeePeriod is valid, {@code false} if not
   */
  private boolean isValidOverstayFeePeriod(Integer overstayFeePeriod) {
    return overstayFeePeriod != null;
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
  public OverstayRule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidOverstayFee(overstayFee)
        && isValidOverstayRuleDescription(overstayRuleDescription)
        && isValidStartTime(startTime)
        && isValidOverstayFeePeriod(overstayFeePeriod)
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
    OverstayRule that = (OverstayRule) o;
    return Objects.equals(overstayFee, that.overstayFee)
        && Objects.equals(overstayRuleDescription, that.overstayRuleDescription)
        && Objects.equals(startTime, that.startTime)
        && Objects.equals(overstayFeePeriod, that.overstayFeePeriod)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        overstayFee, overstayRuleDescription, startTime, overstayFeePeriod, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("overstayFee", overstayFee)
        .add("overstayRuleDescription", overstayRuleDescription)
        .add("startTime", startTime)
        .add("overstayFeePeriod", overstayFeePeriod)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
