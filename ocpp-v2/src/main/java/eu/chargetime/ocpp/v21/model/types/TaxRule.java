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
public final class TaxRule {
  /** Id for the tax rule. */
  private Integer taxRuleID;

  /** Human readable string to identify the tax rule. */
  @Nullable private String taxRuleName;

  /** Whether the tax is included in any price or not. */
  @Nullable private Boolean taxIncludedInPrice;

  /** Whether this tax applies to Energy Fees. */
  private Boolean appliesToEnergyFee;

  /** Whether this tax applies to Parking Fees. */
  private Boolean appliesToParkingFee;

  /** Whether this tax applies to Overstay Fees. */
  private Boolean appliesToOverstayFee;

  /** Whether this tax applies to Minimum/Maximum Cost. */
  private Boolean appliesToMinimumMaximumCost;

  /** Part of ISO 15118-20 price schedule. */
  private RationalNumber taxRate;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TaxRule class
   *
   * @param taxRuleID Id for the tax rule.
   * @param appliesToEnergyFee Whether this tax applies to Energy Fees.
   * @param appliesToParkingFee Whether this tax applies to Parking Fees.
   * @param appliesToOverstayFee Whether this tax applies to Overstay Fees.
   * @param appliesToMinimumMaximumCost Whether this tax applies to Minimum/Maximum Cost.
   * @param taxRate Part of ISO 15118-20 price schedule.
   */
  public TaxRule(
      Integer taxRuleID,
      Boolean appliesToEnergyFee,
      Boolean appliesToParkingFee,
      Boolean appliesToOverstayFee,
      Boolean appliesToMinimumMaximumCost,
      RationalNumber taxRate) {
    setTaxRuleID(taxRuleID);
    setAppliesToEnergyFee(appliesToEnergyFee);
    setAppliesToParkingFee(appliesToParkingFee);
    setAppliesToOverstayFee(appliesToOverstayFee);
    setAppliesToMinimumMaximumCost(appliesToMinimumMaximumCost);
    setTaxRate(taxRate);
  }

  /**
   * Gets id for the tax rule.
   *
   * @return Id for the tax rule
   */
  public Integer getTaxRuleID() {
    return taxRuleID;
  }

  /**
   * Sets id for the tax rule.
   *
   * @param taxRuleID Id for the tax rule
   */
  public void setTaxRuleID(Integer taxRuleID) {
    if (!isValidTaxRuleID(taxRuleID)) {
      throw new PropertyConstraintException(taxRuleID, "taxRuleID is invalid");
    }
    this.taxRuleID = taxRuleID;
  }

  /**
   * Returns whether the given taxRuleID is valid
   *
   * @param taxRuleID the taxRuleID to check the validity of
   * @return {@code true} if taxRuleID is valid, {@code false} if not
   */
  private boolean isValidTaxRuleID(Integer taxRuleID) {
    return taxRuleID != null && taxRuleID >= 0;
  }

  /**
   * Gets human readable string to identify the tax rule.
   *
   * @return Human readable string to identify the tax rule
   */
  @Nullable
  public String getTaxRuleName() {
    return taxRuleName;
  }

  /**
   * Sets human readable string to identify the tax rule.
   *
   * @param taxRuleName Human readable string to identify the tax rule
   */
  public void setTaxRuleName(@Nullable String taxRuleName) {
    if (!isValidTaxRuleName(taxRuleName)) {
      throw new PropertyConstraintException(taxRuleName, "taxRuleName is invalid");
    }
    this.taxRuleName = taxRuleName;
  }

  /**
   * Returns whether the given taxRuleName is valid
   *
   * @param taxRuleName the taxRuleName to check the validity of
   * @return {@code true} if taxRuleName is valid, {@code false} if not
   */
  private boolean isValidTaxRuleName(@Nullable String taxRuleName) {
    return taxRuleName == null || taxRuleName.length() <= 100;
  }

  /**
   * Adds human readable string to identify the tax rule.
   *
   * @param taxRuleName Human readable string to identify the tax rule
   * @return this
   */
  public TaxRule withTaxRuleName(@Nullable String taxRuleName) {
    setTaxRuleName(taxRuleName);
    return this;
  }

  /**
   * Gets whether the tax is included in any price or not.
   *
   * @return Whether the tax is included in any price or not
   */
  @Nullable
  public Boolean getTaxIncludedInPrice() {
    return taxIncludedInPrice;
  }

  /**
   * Sets whether the tax is included in any price or not.
   *
   * @param taxIncludedInPrice Whether the tax is included in any price or not
   */
  public void setTaxIncludedInPrice(@Nullable Boolean taxIncludedInPrice) {
    this.taxIncludedInPrice = taxIncludedInPrice;
  }

  /**
   * Adds whether the tax is included in any price or not.
   *
   * @param taxIncludedInPrice Whether the tax is included in any price or not
   * @return this
   */
  public TaxRule withTaxIncludedInPrice(@Nullable Boolean taxIncludedInPrice) {
    setTaxIncludedInPrice(taxIncludedInPrice);
    return this;
  }

  /**
   * Gets whether this tax applies to Energy Fees.
   *
   * @return Whether this tax applies to Energy Fees
   */
  public Boolean getAppliesToEnergyFee() {
    return appliesToEnergyFee;
  }

  /**
   * Sets whether this tax applies to Energy Fees.
   *
   * @param appliesToEnergyFee Whether this tax applies to Energy Fees
   */
  public void setAppliesToEnergyFee(Boolean appliesToEnergyFee) {
    if (!isValidAppliesToEnergyFee(appliesToEnergyFee)) {
      throw new PropertyConstraintException(appliesToEnergyFee, "appliesToEnergyFee is invalid");
    }
    this.appliesToEnergyFee = appliesToEnergyFee;
  }

  /**
   * Returns whether the given appliesToEnergyFee is valid
   *
   * @param appliesToEnergyFee the appliesToEnergyFee to check the validity of
   * @return {@code true} if appliesToEnergyFee is valid, {@code false} if not
   */
  private boolean isValidAppliesToEnergyFee(Boolean appliesToEnergyFee) {
    return appliesToEnergyFee != null;
  }

  /**
   * Gets whether this tax applies to Parking Fees.
   *
   * @return Whether this tax applies to Parking Fees
   */
  public Boolean getAppliesToParkingFee() {
    return appliesToParkingFee;
  }

  /**
   * Sets whether this tax applies to Parking Fees.
   *
   * @param appliesToParkingFee Whether this tax applies to Parking Fees
   */
  public void setAppliesToParkingFee(Boolean appliesToParkingFee) {
    if (!isValidAppliesToParkingFee(appliesToParkingFee)) {
      throw new PropertyConstraintException(appliesToParkingFee, "appliesToParkingFee is invalid");
    }
    this.appliesToParkingFee = appliesToParkingFee;
  }

  /**
   * Returns whether the given appliesToParkingFee is valid
   *
   * @param appliesToParkingFee the appliesToParkingFee to check the validity of
   * @return {@code true} if appliesToParkingFee is valid, {@code false} if not
   */
  private boolean isValidAppliesToParkingFee(Boolean appliesToParkingFee) {
    return appliesToParkingFee != null;
  }

  /**
   * Gets whether this tax applies to Overstay Fees.
   *
   * @return Whether this tax applies to Overstay Fees
   */
  public Boolean getAppliesToOverstayFee() {
    return appliesToOverstayFee;
  }

  /**
   * Sets whether this tax applies to Overstay Fees.
   *
   * @param appliesToOverstayFee Whether this tax applies to Overstay Fees
   */
  public void setAppliesToOverstayFee(Boolean appliesToOverstayFee) {
    if (!isValidAppliesToOverstayFee(appliesToOverstayFee)) {
      throw new PropertyConstraintException(
          appliesToOverstayFee, "appliesToOverstayFee is invalid");
    }
    this.appliesToOverstayFee = appliesToOverstayFee;
  }

  /**
   * Returns whether the given appliesToOverstayFee is valid
   *
   * @param appliesToOverstayFee the appliesToOverstayFee to check the validity of
   * @return {@code true} if appliesToOverstayFee is valid, {@code false} if not
   */
  private boolean isValidAppliesToOverstayFee(Boolean appliesToOverstayFee) {
    return appliesToOverstayFee != null;
  }

  /**
   * Gets whether this tax applies to Minimum/Maximum Cost.
   *
   * @return Whether this tax applies to Minimum/Maximum Cost
   */
  public Boolean getAppliesToMinimumMaximumCost() {
    return appliesToMinimumMaximumCost;
  }

  /**
   * Sets whether this tax applies to Minimum/Maximum Cost.
   *
   * @param appliesToMinimumMaximumCost Whether this tax applies to Minimum/Maximum Cost
   */
  public void setAppliesToMinimumMaximumCost(Boolean appliesToMinimumMaximumCost) {
    if (!isValidAppliesToMinimumMaximumCost(appliesToMinimumMaximumCost)) {
      throw new PropertyConstraintException(
          appliesToMinimumMaximumCost, "appliesToMinimumMaximumCost is invalid");
    }
    this.appliesToMinimumMaximumCost = appliesToMinimumMaximumCost;
  }

  /**
   * Returns whether the given appliesToMinimumMaximumCost is valid
   *
   * @param appliesToMinimumMaximumCost the appliesToMinimumMaximumCost to check the validity of
   * @return {@code true} if appliesToMinimumMaximumCost is valid, {@code false} if not
   */
  private boolean isValidAppliesToMinimumMaximumCost(Boolean appliesToMinimumMaximumCost) {
    return appliesToMinimumMaximumCost != null;
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  public RationalNumber getTaxRate() {
    return taxRate;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param taxRate Part of ISO 15118-20 price schedule
   */
  public void setTaxRate(RationalNumber taxRate) {
    if (!isValidTaxRate(taxRate)) {
      throw new PropertyConstraintException(taxRate, "taxRate is invalid");
    }
    this.taxRate = taxRate;
  }

  /**
   * Returns whether the given taxRate is valid
   *
   * @param taxRate the taxRate to check the validity of
   * @return {@code true} if taxRate is valid, {@code false} if not
   */
  private boolean isValidTaxRate(RationalNumber taxRate) {
    return taxRate != null && taxRate.validate();
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
  public TaxRule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidTaxRuleID(taxRuleID)
        && isValidTaxRuleName(taxRuleName)
        && isValidAppliesToEnergyFee(appliesToEnergyFee)
        && isValidAppliesToParkingFee(appliesToParkingFee)
        && isValidAppliesToOverstayFee(appliesToOverstayFee)
        && isValidAppliesToMinimumMaximumCost(appliesToMinimumMaximumCost)
        && isValidTaxRate(taxRate)
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
    TaxRule that = (TaxRule) o;
    return Objects.equals(taxRuleID, that.taxRuleID)
        && Objects.equals(taxRuleName, that.taxRuleName)
        && Objects.equals(taxIncludedInPrice, that.taxIncludedInPrice)
        && Objects.equals(appliesToEnergyFee, that.appliesToEnergyFee)
        && Objects.equals(appliesToParkingFee, that.appliesToParkingFee)
        && Objects.equals(appliesToOverstayFee, that.appliesToOverstayFee)
        && Objects.equals(appliesToMinimumMaximumCost, that.appliesToMinimumMaximumCost)
        && Objects.equals(taxRate, that.taxRate)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        taxRuleID,
        taxRuleName,
        taxIncludedInPrice,
        appliesToEnergyFee,
        appliesToParkingFee,
        appliesToOverstayFee,
        appliesToMinimumMaximumCost,
        taxRate,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("taxRuleID", taxRuleID)
        .add("taxRuleName", taxRuleName)
        .add("taxIncludedInPrice", taxIncludedInPrice)
        .add("appliesToEnergyFee", appliesToEnergyFee)
        .add("appliesToParkingFee", appliesToParkingFee)
        .add("appliesToOverstayFee", appliesToOverstayFee)
        .add("appliesToMinimumMaximumCost", appliesToMinimumMaximumCost)
        .add("taxRate", taxRate)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
