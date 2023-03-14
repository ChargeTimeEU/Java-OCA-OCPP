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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Charge Point
 *
 * <p>The physical system where an Electrical Vehicle (EV) can be charged.
 */
public final class ChargingStation {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Device. Serial Number. Serial Number
   *
   * <p>Vendor-specific device identifier.
   */
  @Nullable private String serialNumber;

  /**
   * Device. Model. CI20 Text
   *
   * <p>The model of the device.
   */
  private String model;

  /**
   * Wireless Communication Module
   *
   * <p>Parameters required for initiating and maintaining wireless communication with other
   * devices.
   */
  @Nullable private Modem modem;

  /** The identifier of the vendor (not necessarily in a unique manner). */
  private String vendorName;

  /** The firmware version of the Charging Station. */
  @Nullable private String firmwareVersion;

  /**
   * Constructor for the ChargingStation class
   *
   * @param model The model of the device.
   * @param vendorName The identifier of the vendor (not necessarily in a unique manner).
   */
  public ChargingStation(String model, String vendorName) {
    setModel(model);
    setVendorName(vendorName);
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
  public ChargingStation withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets vendor-specific device identifier.
   *
   * @return Vendor-specific device identifier
   */
  @Nullable
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Sets vendor-specific device identifier.
   *
   * @param serialNumber Vendor-specific device identifier
   */
  public void setSerialNumber(@Nullable String serialNumber) {
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
  private boolean isValidSerialNumber(@Nullable String serialNumber) {
    return serialNumber == null || serialNumber.length() <= 25;
  }

  /**
   * Adds vendor-specific device identifier.
   *
   * @param serialNumber Vendor-specific device identifier
   * @return this
   */
  public ChargingStation withSerialNumber(@Nullable String serialNumber) {
    setSerialNumber(serialNumber);
    return this;
  }

  /**
   * Gets the model of the device.
   *
   * @return The model of the device
   */
  public String getModel() {
    return model;
  }

  /**
   * Sets the model of the device.
   *
   * @param model The model of the device
   */
  public void setModel(String model) {
    if (!isValidModel(model)) {
      throw new PropertyConstraintException(model, "model is invalid");
    }
    this.model = model;
  }

  /**
   * Returns whether the given model is valid
   *
   * @param model the model to check the validity of
   * @return {@code true} if model is valid, {@code false} if not
   */
  private boolean isValidModel(String model) {
    return model != null && model.length() <= 20;
  }

  /**
   * Gets parameters required for initiating and maintaining wireless communication with other
   * devices.
   *
   * @return Parameters required for initiating and maintaining wireless communication with other
   *     devices
   */
  @Nullable
  public Modem getModem() {
    return modem;
  }

  /**
   * Sets parameters required for initiating and maintaining wireless communication with other
   * devices.
   *
   * @param modem Parameters required for initiating and maintaining wireless communication with
   *     other devices
   */
  public void setModem(@Nullable Modem modem) {
    if (!isValidModem(modem)) {
      throw new PropertyConstraintException(modem, "modem is invalid");
    }
    this.modem = modem;
  }

  /**
   * Returns whether the given modem is valid
   *
   * @param modem the modem to check the validity of
   * @return {@code true} if modem is valid, {@code false} if not
   */
  private boolean isValidModem(@Nullable Modem modem) {
    return modem == null || modem.validate();
  }

  /**
   * Adds parameters required for initiating and maintaining wireless communication with other
   * devices.
   *
   * @param modem Parameters required for initiating and maintaining wireless communication with
   *     other devices
   * @return this
   */
  public ChargingStation withModem(@Nullable Modem modem) {
    setModem(modem);
    return this;
  }

  /**
   * Gets the identifier of the vendor (not necessarily in a unique manner).
   *
   * @return The identifier of the vendor (not necessarily in a unique manner)
   */
  public String getVendorName() {
    return vendorName;
  }

  /**
   * Sets the identifier of the vendor (not necessarily in a unique manner).
   *
   * @param vendorName The identifier of the vendor (not necessarily in a unique manner)
   */
  public void setVendorName(String vendorName) {
    if (!isValidVendorName(vendorName)) {
      throw new PropertyConstraintException(vendorName, "vendorName is invalid");
    }
    this.vendorName = vendorName;
  }

  /**
   * Returns whether the given vendorName is valid
   *
   * @param vendorName the vendorName to check the validity of
   * @return {@code true} if vendorName is valid, {@code false} if not
   */
  private boolean isValidVendorName(String vendorName) {
    return vendorName != null && vendorName.length() <= 50;
  }

  /**
   * Gets the firmware version of the Charging Station.
   *
   * @return The firmware version of the Charging Station
   */
  @Nullable
  public String getFirmwareVersion() {
    return firmwareVersion;
  }

  /**
   * Sets the firmware version of the Charging Station.
   *
   * @param firmwareVersion The firmware version of the Charging Station
   */
  public void setFirmwareVersion(@Nullable String firmwareVersion) {
    if (!isValidFirmwareVersion(firmwareVersion)) {
      throw new PropertyConstraintException(firmwareVersion, "firmwareVersion is invalid");
    }
    this.firmwareVersion = firmwareVersion;
  }

  /**
   * Returns whether the given firmwareVersion is valid
   *
   * @param firmwareVersion the firmwareVersion to check the validity of
   * @return {@code true} if firmwareVersion is valid, {@code false} if not
   */
  private boolean isValidFirmwareVersion(@Nullable String firmwareVersion) {
    return firmwareVersion == null || firmwareVersion.length() <= 50;
  }

  /**
   * Adds the firmware version of the Charging Station.
   *
   * @param firmwareVersion The firmware version of the Charging Station
   * @return this
   */
  public ChargingStation withFirmwareVersion(@Nullable String firmwareVersion) {
    setFirmwareVersion(firmwareVersion);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidSerialNumber(serialNumber)
        && isValidModel(model)
        && isValidModem(modem)
        && isValidVendorName(vendorName)
        && isValidFirmwareVersion(firmwareVersion);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChargingStation that = (ChargingStation) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(serialNumber, that.serialNumber)
        && Objects.equals(model, that.model)
        && Objects.equals(modem, that.modem)
        && Objects.equals(vendorName, that.vendorName)
        && Objects.equals(firmwareVersion, that.firmwareVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, serialNumber, model, modem, vendorName, firmwareVersion);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("serialNumber", serialNumber)
        .add("model", model)
        .add("modem", modem)
        .add("vendorName", vendorName)
        .add("firmwareVersion", firmwareVersion)
        .add("isValid", validate())
        .toString();
  }
}
