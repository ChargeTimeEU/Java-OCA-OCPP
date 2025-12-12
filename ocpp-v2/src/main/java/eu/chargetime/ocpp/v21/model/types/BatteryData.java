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
import java.util.Objects;
import javax.annotation.Nullable;

/** BatteryDataType */
public final class BatteryData {
  /** Slot number where battery is inserted or removed. */
  private Integer evseId;

  /** Serial number of battery. */
  private String serialNumber;

  /** State of charge */
  private Double soC;

  /** State of health */
  private Double soH;

  /** Production date of battery. */
  @Nullable private ZonedDateTime productionDate;

  /** Vendor-specific info from battery in undefined format. */
  @Nullable private String vendorInfo;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the BatteryData class
   *
   * @param evseId Slot number where battery is inserted or removed.
   * @param serialNumber Serial number of battery.
   * @param soC State of charge
   * @param soH State of health
   */
  public BatteryData(Integer evseId, String serialNumber, Double soC, Double soH) {
    setEvseId(evseId);
    setSerialNumber(serialNumber);
    setSoC(soC);
    setSoH(soH);
  }

  /**
   * Gets slot number where battery is inserted or removed.
   *
   * @return Slot number where battery is inserted or removed
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets slot number where battery is inserted or removed.
   *
   * @param evseId Slot number where battery is inserted or removed
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
    return evseId != null && evseId >= 0;
  }

  /**
   * Gets serial number of battery.
   *
   * @return Serial number of battery
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Sets serial number of battery.
   *
   * @param serialNumber Serial number of battery
   */
  public void setSerialNumber(String serialNumber) {
    if (!isValidSerialNumber(serialNumber)) {
      throw new PropertyConstraintException(serialNumber, "serialNumber is invalid");
    }
    this.serialNumber = serialNumber;
  }

  /**
   * Returns whether the given serialNumber is valid
   *
   * @param serialNumber the serialNumber to check the validity of
   * @return {@code true} if serialNumber is valid, {@code false} if not
   */
  private boolean isValidSerialNumber(String serialNumber) {
    return serialNumber != null && serialNumber.length() <= 50;
  }

  /**
   * Gets state of charge
   *
   * @return State of charge
   */
  public Double getSoC() {
    return soC;
  }

  /**
   * Sets state of charge
   *
   * @param soC State of charge
   */
  public void setSoC(Double soC) {
    if (!isValidSoC(soC)) {
      throw new PropertyConstraintException(soC, "soC is invalid");
    }
    this.soC = soC;
  }

  /**
   * Returns whether the given soC is valid
   *
   * @param soC the soC to check the validity of
   * @return {@code true} if soC is valid, {@code false} if not
   */
  private boolean isValidSoC(Double soC) {
    return soC != null && soC >= 0.0d && soC <= 100.0d;
  }

  /**
   * Gets state of health
   *
   * @return State of health
   */
  public Double getSoH() {
    return soH;
  }

  /**
   * Sets state of health
   *
   * @param soH State of health
   */
  public void setSoH(Double soH) {
    if (!isValidSoH(soH)) {
      throw new PropertyConstraintException(soH, "soH is invalid");
    }
    this.soH = soH;
  }

  /**
   * Returns whether the given soH is valid
   *
   * @param soH the soH to check the validity of
   * @return {@code true} if soH is valid, {@code false} if not
   */
  private boolean isValidSoH(Double soH) {
    return soH != null && soH >= 0.0d && soH <= 100.0d;
  }

  /**
   * Gets production date of battery.
   *
   * @return Production date of battery
   */
  @Nullable
  public ZonedDateTime getProductionDate() {
    return productionDate;
  }

  /**
   * Sets production date of battery.
   *
   * @param productionDate Production date of battery
   */
  public void setProductionDate(@Nullable ZonedDateTime productionDate) {
    this.productionDate = productionDate;
  }

  /**
   * Adds production date of battery.
   *
   * @param productionDate Production date of battery
   * @return this
   */
  public BatteryData withProductionDate(@Nullable ZonedDateTime productionDate) {
    setProductionDate(productionDate);
    return this;
  }

  /**
   * Gets vendor-specific info from battery in undefined format.
   *
   * @return Vendor-specific info from battery in undefined format
   */
  @Nullable
  public String getVendorInfo() {
    return vendorInfo;
  }

  /**
   * Sets vendor-specific info from battery in undefined format.
   *
   * @param vendorInfo Vendor-specific info from battery in undefined format
   */
  public void setVendorInfo(@Nullable String vendorInfo) {
    if (!isValidVendorInfo(vendorInfo)) {
      throw new PropertyConstraintException(vendorInfo, "vendorInfo is invalid");
    }
    this.vendorInfo = vendorInfo;
  }

  /**
   * Returns whether the given vendorInfo is valid
   *
   * @param vendorInfo the vendorInfo to check the validity of
   * @return {@code true} if vendorInfo is valid, {@code false} if not
   */
  private boolean isValidVendorInfo(@Nullable String vendorInfo) {
    return vendorInfo == null || vendorInfo.length() <= 500;
  }

  /**
   * Adds vendor-specific info from battery in undefined format.
   *
   * @param vendorInfo Vendor-specific info from battery in undefined format
   * @return this
   */
  public BatteryData withVendorInfo(@Nullable String vendorInfo) {
    setVendorInfo(vendorInfo);
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
  public BatteryData withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidEvseId(evseId)
        && isValidSerialNumber(serialNumber)
        && isValidSoC(soC)
        && isValidSoH(soH)
        && isValidVendorInfo(vendorInfo)
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
    BatteryData that = (BatteryData) o;
    return Objects.equals(evseId, that.evseId)
        && Objects.equals(serialNumber, that.serialNumber)
        && Objects.equals(soC, that.soC)
        && Objects.equals(soH, that.soH)
        && Objects.equals(productionDate, that.productionDate)
        && Objects.equals(vendorInfo, that.vendorInfo)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evseId, serialNumber, soC, soH, productionDate, vendorInfo, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evseId", evseId)
        .add("serialNumber", serialNumber)
        .add("soC", soC)
        .add("soH", soH)
        .add("productionDate", productionDate)
        .add("vendorInfo", vendorInfo)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
