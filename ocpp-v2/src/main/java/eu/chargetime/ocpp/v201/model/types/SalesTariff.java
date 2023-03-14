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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Sales Tariff
 *
 * <p>NOTE: This dataType is based on dataTypes from ISO 15118-2.
 */
public final class SalesTariff {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Identified Object. MRID. Numeric Identifier
   *
   * <p>SalesTariff identifier used to identify one sales tariff. An SAID remains a unique
   * identifier for one schedule throughout a charging session.
   */
  private Integer id;

  /**
   * Sales Tariff. Sales. Tariff Description
   *
   * <p>A human readable title/short description of the sales tariff e.g. for HMI display purposes.
   */
  @Nullable private String salesTariffDescription;

  /**
   * Sales Tariff. Num E Price Levels. Counter
   *
   * <p>The overall number of distinct price levels used across all provided SalesTariff elements.
   */
  @Nullable private Integer numEPriceLevels;

  /** Sales Tariff Entry */
  private SalesTariffEntry[] salesTariffEntry;

  /**
   * Constructor for the SalesTariff class
   *
   * @param id SalesTariff identifier used to identify one sales tariff. An SAID remains a unique
   *     identifier for one schedule throughout a charging session.
   * @param salesTariffEntry Sales Tariff Entry
   */
  public SalesTariff(Integer id, SalesTariffEntry[] salesTariffEntry) {
    setId(id);
    setSalesTariffEntry(salesTariffEntry);
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
  public SalesTariff withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets salesTariff identifier used to identify one sales tariff. An SAID remains a unique
   * identifier for one schedule throughout a charging session.
   *
   * @return SalesTariff identifier used to identify one sales tariff
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets salesTariff identifier used to identify one sales tariff. An SAID remains a unique
   * identifier for one schedule throughout a charging session.
   *
   * @param id SalesTariff identifier used to identify one sales tariff
   */
  public void setId(Integer id) {
    if (!isValidId(id)) {
      throw new PropertyConstraintException(id, "id is invalid");
    }
    this.id = id;
  }

  /**
   * Returns whether the given id is valid
   *
   * @param id the id to check the validity of
   * @return {@code true} if id is valid, {@code false} if not
   */
  private boolean isValidId(Integer id) {
    return id != null;
  }

  /**
   * Gets a human readable title/short description of the sales tariff e.g. for HMI display
   * purposes.
   *
   * @return A human readable title/short description of the sales tariff e.g. for HMI display
   *     purposes
   */
  @Nullable
  public String getSalesTariffDescription() {
    return salesTariffDescription;
  }

  /**
   * Sets a human readable title/short description of the sales tariff e.g. for HMI display
   * purposes.
   *
   * @param salesTariffDescription A human readable title/short description of the sales tariff e.g.
   *     for HMI display purposes
   */
  public void setSalesTariffDescription(@Nullable String salesTariffDescription) {
    if (!isValidSalesTariffDescription(salesTariffDescription)) {
      throw new PropertyConstraintException(
          salesTariffDescription, "salesTariffDescription is invalid");
    }
    this.salesTariffDescription = salesTariffDescription;
  }

  /**
   * Returns whether the given salesTariffDescription is valid
   *
   * @param salesTariffDescription the salesTariffDescription to check the validity of
   * @return {@code true} if salesTariffDescription is valid, {@code false} if not
   */
  private boolean isValidSalesTariffDescription(@Nullable String salesTariffDescription) {
    return salesTariffDescription == null || salesTariffDescription.length() <= 32;
  }

  /**
   * Adds a human readable title/short description of the sales tariff e.g. for HMI display
   * purposes.
   *
   * @param salesTariffDescription A human readable title/short description of the sales tariff e.g.
   *     for HMI display purposes
   * @return this
   */
  public SalesTariff withSalesTariffDescription(@Nullable String salesTariffDescription) {
    setSalesTariffDescription(salesTariffDescription);
    return this;
  }

  /**
   * Gets the overall number of distinct price levels used across all provided SalesTariff elements.
   *
   * @return The overall number of distinct price levels used across all provided SalesTariff
   *     elements
   */
  @Nullable
  public Integer getNumEPriceLevels() {
    return numEPriceLevels;
  }

  /**
   * Sets the overall number of distinct price levels used across all provided SalesTariff elements.
   *
   * @param numEPriceLevels The overall number of distinct price levels used across all provided
   *     SalesTariff elements
   */
  public void setNumEPriceLevels(@Nullable Integer numEPriceLevels) {
    this.numEPriceLevels = numEPriceLevels;
  }

  /**
   * Adds the overall number of distinct price levels used across all provided SalesTariff elements.
   *
   * @param numEPriceLevels The overall number of distinct price levels used across all provided
   *     SalesTariff elements
   * @return this
   */
  public SalesTariff withNumEPriceLevels(@Nullable Integer numEPriceLevels) {
    setNumEPriceLevels(numEPriceLevels);
    return this;
  }

  /**
   * Gets sales Tariff Entry
   *
   * @return Sales Tariff Entry
   */
  public SalesTariffEntry[] getSalesTariffEntry() {
    return salesTariffEntry;
  }

  /**
   * Sets sales Tariff Entry
   *
   * @param salesTariffEntry Sales Tariff Entry
   */
  public void setSalesTariffEntry(SalesTariffEntry[] salesTariffEntry) {
    if (!isValidSalesTariffEntry(salesTariffEntry)) {
      throw new PropertyConstraintException(salesTariffEntry, "salesTariffEntry is invalid");
    }
    this.salesTariffEntry = salesTariffEntry;
  }

  /**
   * Returns whether the given salesTariffEntry is valid
   *
   * @param salesTariffEntry the salesTariffEntry to check the validity of
   * @return {@code true} if salesTariffEntry is valid, {@code false} if not
   */
  private boolean isValidSalesTariffEntry(SalesTariffEntry[] salesTariffEntry) {
    return salesTariffEntry != null
        && salesTariffEntry.length >= 1
        && salesTariffEntry.length <= 1024
        && Arrays.stream(salesTariffEntry).allMatch(item -> item.validate());
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidId(id)
        && isValidSalesTariffDescription(salesTariffDescription)
        && isValidSalesTariffEntry(salesTariffEntry);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalesTariff that = (SalesTariff) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(id, that.id)
        && Objects.equals(salesTariffDescription, that.salesTariffDescription)
        && Objects.equals(numEPriceLevels, that.numEPriceLevels)
        && Arrays.equals(salesTariffEntry, that.salesTariffEntry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, id, salesTariffDescription, numEPriceLevels, Arrays.hashCode(salesTariffEntry));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("id", id)
        .add("salesTariffDescription", salesTariffDescription)
        .add("numEPriceLevels", numEPriceLevels)
        .add("salesTariffEntry", salesTariffEntry)
        .add("isValid", validate())
        .toString();
  }
}
