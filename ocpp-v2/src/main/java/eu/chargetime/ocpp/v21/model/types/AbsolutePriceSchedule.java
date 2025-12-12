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

/**
 * The AbsolutePriceScheduleType is modeled after the same type that is defined in ISO 15118-20,
 * such that if it is supplied by an EMSP as a signed EXI message, the conversion from EXI to JSON
 * (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest and therefore does not
 * invalidate the signature.
 *
 * <p>image::images/AbsolutePriceSchedule-Simple.png[]
 */
public final class AbsolutePriceSchedule {
  /** Starting point of price schedule. */
  private ZonedDateTime timeAnchor;

  /** Unique ID of price schedule */
  private Integer priceScheduleID;

  /** Description of the price schedule. */
  @Nullable private String priceScheduleDescription;

  /** Currency according to ISO 4217. */
  private String currency;

  /**
   * String that indicates what language is used for the human readable strings in the price
   * schedule. Based on ISO 639.
   */
  private String language;

  /**
   * A string in URN notation which shall uniquely identify an algorithm that defines how to compute
   * an energy fee sum for a specific power profile based on the EnergyFee information from the
   * PriceRule elements.
   */
  private String priceAlgorithm;

  /** Part of ISO 15118-20 price schedule. */
  @Nullable private RationalNumber minimumCost;

  /** Part of ISO 15118-20 price schedule. */
  @Nullable private RationalNumber maximumCost;

  /** Part of ISO 15118-20 price schedule. */
  private PriceRuleStack[] priceRuleStacks;

  /** Part of ISO 15118-20 price schedule. */
  @Nullable private TaxRule[] taxRules;

  /** Part of ISO 15118-20 price schedule. */
  @Nullable private OverstayRuleList overstayRuleList;

  /** Part of ISO 15118-20 price schedule. */
  @Nullable private AdditionalSelectedServices[] additionalSelectedServices;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the AbsolutePriceSchedule class
   *
   * @param timeAnchor Starting point of price schedule.
   * @param priceScheduleID Unique ID of price schedule
   * @param currency Currency according to ISO 4217.
   * @param language String that indicates what language is used for the human readable strings in
   *     the price schedule. Based on ISO 639.
   * @param priceAlgorithm A string in URN notation which shall uniquely identify an algorithm that
   *     defines how to compute an energy fee sum for a specific power profile based on the
   *     EnergyFee information from the PriceRule elements.
   * @param priceRuleStacks Part of ISO 15118-20 price schedule.
   */
  public AbsolutePriceSchedule(
      ZonedDateTime timeAnchor,
      Integer priceScheduleID,
      String currency,
      String language,
      String priceAlgorithm,
      PriceRuleStack[] priceRuleStacks) {
    setTimeAnchor(timeAnchor);
    setPriceScheduleID(priceScheduleID);
    setCurrency(currency);
    setLanguage(language);
    setPriceAlgorithm(priceAlgorithm);
    setPriceRuleStacks(priceRuleStacks);
  }

  /**
   * Gets starting point of price schedule.
   *
   * @return Starting point of price schedule
   */
  public ZonedDateTime getTimeAnchor() {
    return timeAnchor;
  }

  /**
   * Sets starting point of price schedule.
   *
   * @param timeAnchor Starting point of price schedule
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
   * Gets unique ID of price schedule
   *
   * @return Unique ID of price schedule
   */
  public Integer getPriceScheduleID() {
    return priceScheduleID;
  }

  /**
   * Sets unique ID of price schedule
   *
   * @param priceScheduleID Unique ID of price schedule
   */
  public void setPriceScheduleID(Integer priceScheduleID) {
    if (!isValidPriceScheduleID(priceScheduleID)) {
      throw new PropertyConstraintException(priceScheduleID, "priceScheduleID is invalid");
    }
    this.priceScheduleID = priceScheduleID;
  }

  /**
   * Returns whether the given priceScheduleID is valid
   *
   * @param priceScheduleID the priceScheduleID to check the validity of
   * @return {@code true} if priceScheduleID is valid, {@code false} if not
   */
  private boolean isValidPriceScheduleID(Integer priceScheduleID) {
    return priceScheduleID != null && priceScheduleID >= 0;
  }

  /**
   * Gets description of the price schedule.
   *
   * @return Description of the price schedule
   */
  @Nullable
  public String getPriceScheduleDescription() {
    return priceScheduleDescription;
  }

  /**
   * Sets description of the price schedule.
   *
   * @param priceScheduleDescription Description of the price schedule
   */
  public void setPriceScheduleDescription(@Nullable String priceScheduleDescription) {
    if (!isValidPriceScheduleDescription(priceScheduleDescription)) {
      throw new PropertyConstraintException(
          priceScheduleDescription, "priceScheduleDescription is invalid");
    }
    this.priceScheduleDescription = priceScheduleDescription;
  }

  /**
   * Returns whether the given priceScheduleDescription is valid
   *
   * @param priceScheduleDescription the priceScheduleDescription to check the validity of
   * @return {@code true} if priceScheduleDescription is valid, {@code false} if not
   */
  private boolean isValidPriceScheduleDescription(@Nullable String priceScheduleDescription) {
    return priceScheduleDescription == null || priceScheduleDescription.length() <= 160;
  }

  /**
   * Adds description of the price schedule.
   *
   * @param priceScheduleDescription Description of the price schedule
   * @return this
   */
  public AbsolutePriceSchedule withPriceScheduleDescription(
      @Nullable String priceScheduleDescription) {
    setPriceScheduleDescription(priceScheduleDescription);
    return this;
  }

  /**
   * Gets currency according to ISO 4217.
   *
   * @return Currency according to ISO 4217
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets currency according to ISO 4217.
   *
   * @param currency Currency according to ISO 4217
   */
  public void setCurrency(String currency) {
    if (!isValidCurrency(currency)) {
      throw new PropertyConstraintException(currency, "currency is invalid");
    }
    this.currency = currency;
  }

  /**
   * Returns whether the given currency is valid
   *
   * @param currency the currency to check the validity of
   * @return {@code true} if currency is valid, {@code false} if not
   */
  private boolean isValidCurrency(String currency) {
    return currency != null && currency.length() <= 3;
  }

  /**
   * Gets string that indicates what language is used for the human readable strings in the price
   * schedule. Based on ISO 639.
   *
   * @return String that indicates what language is used for the human readable strings in the price
   *     schedule
   */
  public String getLanguage() {
    return language;
  }

  /**
   * Sets string that indicates what language is used for the human readable strings in the price
   * schedule. Based on ISO 639.
   *
   * @param language String that indicates what language is used for the human readable strings in
   *     the price schedule
   */
  public void setLanguage(String language) {
    if (!isValidLanguage(language)) {
      throw new PropertyConstraintException(language, "language is invalid");
    }
    this.language = language;
  }

  /**
   * Returns whether the given language is valid
   *
   * @param language the language to check the validity of
   * @return {@code true} if language is valid, {@code false} if not
   */
  private boolean isValidLanguage(String language) {
    return language != null && language.length() <= 8;
  }

  /**
   * Gets a string in URN notation which shall uniquely identify an algorithm that defines how to
   * compute an energy fee sum for a specific power profile based on the EnergyFee information from
   * the PriceRule elements.
   *
   * @return A string in URN notation which shall uniquely identify an algorithm that defines how to
   *     compute an energy fee sum for a specific power profile based on the EnergyFee information
   *     from the PriceRule elements
   */
  public String getPriceAlgorithm() {
    return priceAlgorithm;
  }

  /**
   * Sets a string in URN notation which shall uniquely identify an algorithm that defines how to
   * compute an energy fee sum for a specific power profile based on the EnergyFee information from
   * the PriceRule elements.
   *
   * @param priceAlgorithm A string in URN notation which shall uniquely identify an algorithm that
   *     defines how to compute an energy fee sum for a specific power profile based on the
   *     EnergyFee information from the PriceRule elements
   */
  public void setPriceAlgorithm(String priceAlgorithm) {
    if (!isValidPriceAlgorithm(priceAlgorithm)) {
      throw new PropertyConstraintException(priceAlgorithm, "priceAlgorithm is invalid");
    }
    this.priceAlgorithm = priceAlgorithm;
  }

  /**
   * Returns whether the given priceAlgorithm is valid
   *
   * @param priceAlgorithm the priceAlgorithm to check the validity of
   * @return {@code true} if priceAlgorithm is valid, {@code false} if not
   */
  private boolean isValidPriceAlgorithm(String priceAlgorithm) {
    return priceAlgorithm != null && priceAlgorithm.length() <= 2000;
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  @Nullable
  public RationalNumber getMinimumCost() {
    return minimumCost;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param minimumCost Part of ISO 15118-20 price schedule
   */
  public void setMinimumCost(@Nullable RationalNumber minimumCost) {
    if (!isValidMinimumCost(minimumCost)) {
      throw new PropertyConstraintException(minimumCost, "minimumCost is invalid");
    }
    this.minimumCost = minimumCost;
  }

  /**
   * Returns whether the given minimumCost is valid
   *
   * @param minimumCost the minimumCost to check the validity of
   * @return {@code true} if minimumCost is valid, {@code false} if not
   */
  private boolean isValidMinimumCost(@Nullable RationalNumber minimumCost) {
    return minimumCost == null || minimumCost.validate();
  }

  /**
   * Adds part of ISO 15118-20 price schedule.
   *
   * @param minimumCost Part of ISO 15118-20 price schedule
   * @return this
   */
  public AbsolutePriceSchedule withMinimumCost(@Nullable RationalNumber minimumCost) {
    setMinimumCost(minimumCost);
    return this;
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  @Nullable
  public RationalNumber getMaximumCost() {
    return maximumCost;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param maximumCost Part of ISO 15118-20 price schedule
   */
  public void setMaximumCost(@Nullable RationalNumber maximumCost) {
    if (!isValidMaximumCost(maximumCost)) {
      throw new PropertyConstraintException(maximumCost, "maximumCost is invalid");
    }
    this.maximumCost = maximumCost;
  }

  /**
   * Returns whether the given maximumCost is valid
   *
   * @param maximumCost the maximumCost to check the validity of
   * @return {@code true} if maximumCost is valid, {@code false} if not
   */
  private boolean isValidMaximumCost(@Nullable RationalNumber maximumCost) {
    return maximumCost == null || maximumCost.validate();
  }

  /**
   * Adds part of ISO 15118-20 price schedule.
   *
   * @param maximumCost Part of ISO 15118-20 price schedule
   * @return this
   */
  public AbsolutePriceSchedule withMaximumCost(@Nullable RationalNumber maximumCost) {
    setMaximumCost(maximumCost);
    return this;
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  public PriceRuleStack[] getPriceRuleStacks() {
    return priceRuleStacks;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param priceRuleStacks Part of ISO 15118-20 price schedule
   */
  public void setPriceRuleStacks(PriceRuleStack[] priceRuleStacks) {
    if (!isValidPriceRuleStacks(priceRuleStacks)) {
      throw new PropertyConstraintException(priceRuleStacks, "priceRuleStacks is invalid");
    }
    this.priceRuleStacks = priceRuleStacks;
  }

  /**
   * Returns whether the given priceRuleStacks is valid
   *
   * @param priceRuleStacks the priceRuleStacks to check the validity of
   * @return {@code true} if priceRuleStacks is valid, {@code false} if not
   */
  private boolean isValidPriceRuleStacks(PriceRuleStack[] priceRuleStacks) {
    return priceRuleStacks != null
        && priceRuleStacks.length >= 1
        && priceRuleStacks.length <= 1024
        && Arrays.stream(priceRuleStacks).allMatch(item -> item.validate());
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  @Nullable
  public TaxRule[] getTaxRules() {
    return taxRules;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param taxRules Part of ISO 15118-20 price schedule
   */
  public void setTaxRules(@Nullable TaxRule[] taxRules) {
    if (!isValidTaxRules(taxRules)) {
      throw new PropertyConstraintException(taxRules, "taxRules is invalid");
    }
    this.taxRules = taxRules;
  }

  /**
   * Returns whether the given taxRules is valid
   *
   * @param taxRules the taxRules to check the validity of
   * @return {@code true} if taxRules is valid, {@code false} if not
   */
  private boolean isValidTaxRules(@Nullable TaxRule[] taxRules) {
    return taxRules == null
        || (taxRules.length >= 1
            && taxRules.length <= 10
            && Arrays.stream(taxRules).allMatch(item -> item.validate()));
  }

  /**
   * Adds part of ISO 15118-20 price schedule.
   *
   * @param taxRules Part of ISO 15118-20 price schedule
   * @return this
   */
  public AbsolutePriceSchedule withTaxRules(@Nullable TaxRule[] taxRules) {
    setTaxRules(taxRules);
    return this;
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  @Nullable
  public OverstayRuleList getOverstayRuleList() {
    return overstayRuleList;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param overstayRuleList Part of ISO 15118-20 price schedule
   */
  public void setOverstayRuleList(@Nullable OverstayRuleList overstayRuleList) {
    if (!isValidOverstayRuleList(overstayRuleList)) {
      throw new PropertyConstraintException(overstayRuleList, "overstayRuleList is invalid");
    }
    this.overstayRuleList = overstayRuleList;
  }

  /**
   * Returns whether the given overstayRuleList is valid
   *
   * @param overstayRuleList the overstayRuleList to check the validity of
   * @return {@code true} if overstayRuleList is valid, {@code false} if not
   */
  private boolean isValidOverstayRuleList(@Nullable OverstayRuleList overstayRuleList) {
    return overstayRuleList == null || overstayRuleList.validate();
  }

  /**
   * Adds part of ISO 15118-20 price schedule.
   *
   * @param overstayRuleList Part of ISO 15118-20 price schedule
   * @return this
   */
  public AbsolutePriceSchedule withOverstayRuleList(@Nullable OverstayRuleList overstayRuleList) {
    setOverstayRuleList(overstayRuleList);
    return this;
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  @Nullable
  public AdditionalSelectedServices[] getAdditionalSelectedServices() {
    return additionalSelectedServices;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param additionalSelectedServices Part of ISO 15118-20 price schedule
   */
  public void setAdditionalSelectedServices(
      @Nullable AdditionalSelectedServices[] additionalSelectedServices) {
    if (!isValidAdditionalSelectedServices(additionalSelectedServices)) {
      throw new PropertyConstraintException(
          additionalSelectedServices, "additionalSelectedServices is invalid");
    }
    this.additionalSelectedServices = additionalSelectedServices;
  }

  /**
   * Returns whether the given additionalSelectedServices is valid
   *
   * @param additionalSelectedServices the additionalSelectedServices to check the validity of
   * @return {@code true} if additionalSelectedServices is valid, {@code false} if not
   */
  private boolean isValidAdditionalSelectedServices(
      @Nullable AdditionalSelectedServices[] additionalSelectedServices) {
    return additionalSelectedServices == null
        || (additionalSelectedServices.length >= 1
            && additionalSelectedServices.length <= 5
            && Arrays.stream(additionalSelectedServices).allMatch(item -> item.validate()));
  }

  /**
   * Adds part of ISO 15118-20 price schedule.
   *
   * @param additionalSelectedServices Part of ISO 15118-20 price schedule
   * @return this
   */
  public AbsolutePriceSchedule withAdditionalSelectedServices(
      @Nullable AdditionalSelectedServices[] additionalSelectedServices) {
    setAdditionalSelectedServices(additionalSelectedServices);
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
  public AbsolutePriceSchedule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidTimeAnchor(timeAnchor)
        && isValidPriceScheduleID(priceScheduleID)
        && isValidPriceScheduleDescription(priceScheduleDescription)
        && isValidCurrency(currency)
        && isValidLanguage(language)
        && isValidPriceAlgorithm(priceAlgorithm)
        && isValidMinimumCost(minimumCost)
        && isValidMaximumCost(maximumCost)
        && isValidPriceRuleStacks(priceRuleStacks)
        && isValidTaxRules(taxRules)
        && isValidOverstayRuleList(overstayRuleList)
        && isValidAdditionalSelectedServices(additionalSelectedServices)
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
    AbsolutePriceSchedule that = (AbsolutePriceSchedule) o;
    return Objects.equals(timeAnchor, that.timeAnchor)
        && Objects.equals(priceScheduleID, that.priceScheduleID)
        && Objects.equals(priceScheduleDescription, that.priceScheduleDescription)
        && Objects.equals(currency, that.currency)
        && Objects.equals(language, that.language)
        && Objects.equals(priceAlgorithm, that.priceAlgorithm)
        && Objects.equals(minimumCost, that.minimumCost)
        && Objects.equals(maximumCost, that.maximumCost)
        && Arrays.equals(priceRuleStacks, that.priceRuleStacks)
        && Arrays.equals(taxRules, that.taxRules)
        && Objects.equals(overstayRuleList, that.overstayRuleList)
        && Arrays.equals(additionalSelectedServices, that.additionalSelectedServices)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        timeAnchor,
        priceScheduleID,
        priceScheduleDescription,
        currency,
        language,
        priceAlgorithm,
        minimumCost,
        maximumCost,
        Arrays.hashCode(priceRuleStacks),
        Arrays.hashCode(taxRules),
        overstayRuleList,
        Arrays.hashCode(additionalSelectedServices),
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("timeAnchor", timeAnchor)
        .add("priceScheduleID", priceScheduleID)
        .add("priceScheduleDescription", priceScheduleDescription)
        .add("currency", currency)
        .add("language", language)
        .add("priceAlgorithm", priceAlgorithm)
        .add("minimumCost", minimumCost)
        .add("maximumCost", maximumCost)
        .add("priceRuleStacks", priceRuleStacks)
        .add("taxRules", taxRules)
        .add("overstayRuleList", overstayRuleList)
        .add("additionalSelectedServices", additionalSelectedServices)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
