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
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Firmware
 *
 * <p>A copy of the firmware that can be loaded/updated on the Charging Station.
 */
public final class Firmware {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Firmware. Location. URI
   *
   * <p>URI defining the origin of the firmware.
   */
  private String location;

  /**
   * Firmware. Retrieve. Date Time
   *
   * <p>Date and time at which the firmware shall be retrieved.
   */
  private ZonedDateTime retrieveDateTime;

  /**
   * Firmware. Install. Date Time
   *
   * <p>Date and time at which the firmware shall be installed.
   */
  @Nullable private ZonedDateTime installDateTime;

  /** Certificate with which the firmware was signed. PEM encoded X.509 certificate. */
  @Nullable private String signingCertificate;

  /**
   * Firmware. Signature. Signature
   *
   * <p>Base64 encoded firmware signature.
   */
  @Nullable private String signature;

  /**
   * Constructor for the Firmware class
   *
   * @param location URI defining the origin of the firmware.
   * @param retrieveDateTime Date and time at which the firmware shall be retrieved.
   */
  public Firmware(String location, ZonedDateTime retrieveDateTime) {
    setLocation(location);
    setRetrieveDateTime(retrieveDateTime);
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
  public Firmware withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets URI defining the origin of the firmware.
   *
   * @return URI defining the origin of the firmware
   */
  public String getLocation() {
    return location;
  }

  /**
   * Sets URI defining the origin of the firmware.
   *
   * @param location URI defining the origin of the firmware
   */
  public void setLocation(String location) {
    if (!isValidLocation(location)) {
      throw new PropertyConstraintException(location, "location is invalid");
    }
    this.location = location;
  }

  /**
   * Returns whether the given location is valid
   *
   * @param location the location to check the validity of
   * @return {@code true} if location is valid, {@code false} if not
   */
  private boolean isValidLocation(String location) {
    return location != null && location.length() <= 512;
  }

  /**
   * Gets date and time at which the firmware shall be retrieved.
   *
   * @return Date and time at which the firmware shall be retrieved
   */
  public ZonedDateTime getRetrieveDateTime() {
    return retrieveDateTime;
  }

  /**
   * Sets date and time at which the firmware shall be retrieved.
   *
   * @param retrieveDateTime Date and time at which the firmware shall be retrieved
   */
  public void setRetrieveDateTime(ZonedDateTime retrieveDateTime) {
    if (!isValidRetrieveDateTime(retrieveDateTime)) {
      throw new PropertyConstraintException(retrieveDateTime, "retrieveDateTime is invalid");
    }
    this.retrieveDateTime = retrieveDateTime;
  }

  /**
   * Returns whether the given retrieveDateTime is valid
   *
   * @param retrieveDateTime the retrieveDateTime to check the validity of
   * @return {@code true} if retrieveDateTime is valid, {@code false} if not
   */
  private boolean isValidRetrieveDateTime(ZonedDateTime retrieveDateTime) {
    return retrieveDateTime != null;
  }

  /**
   * Gets date and time at which the firmware shall be installed.
   *
   * @return Date and time at which the firmware shall be installed
   */
  @Nullable
  public ZonedDateTime getInstallDateTime() {
    return installDateTime;
  }

  /**
   * Sets date and time at which the firmware shall be installed.
   *
   * @param installDateTime Date and time at which the firmware shall be installed
   */
  public void setInstallDateTime(@Nullable ZonedDateTime installDateTime) {
    this.installDateTime = installDateTime;
  }

  /**
   * Adds date and time at which the firmware shall be installed.
   *
   * @param installDateTime Date and time at which the firmware shall be installed
   * @return this
   */
  public Firmware withInstallDateTime(@Nullable ZonedDateTime installDateTime) {
    setInstallDateTime(installDateTime);
    return this;
  }

  /**
   * Gets certificate with which the firmware was signed. PEM encoded X.509 certificate.
   *
   * @return Certificate with which the firmware was signed
   */
  @Nullable
  public String getSigningCertificate() {
    return signingCertificate;
  }

  /**
   * Sets certificate with which the firmware was signed. PEM encoded X.509 certificate.
   *
   * @param signingCertificate Certificate with which the firmware was signed
   */
  public void setSigningCertificate(@Nullable String signingCertificate) {
    if (!isValidSigningCertificate(signingCertificate)) {
      throw new PropertyConstraintException(signingCertificate, "signingCertificate is invalid");
    }
    this.signingCertificate = signingCertificate;
  }

  /**
   * Returns whether the given signingCertificate is valid
   *
   * @param signingCertificate the signingCertificate to check the validity of
   * @return {@code true} if signingCertificate is valid, {@code false} if not
   */
  private boolean isValidSigningCertificate(@Nullable String signingCertificate) {
    return signingCertificate == null || signingCertificate.length() <= 5500;
  }

  /**
   * Adds certificate with which the firmware was signed. PEM encoded X.509 certificate.
   *
   * @param signingCertificate Certificate with which the firmware was signed
   * @return this
   */
  public Firmware withSigningCertificate(@Nullable String signingCertificate) {
    setSigningCertificate(signingCertificate);
    return this;
  }

  /**
   * Gets base64 encoded firmware signature.
   *
   * @return Base64 encoded firmware signature
   */
  @Nullable
  public String getSignature() {
    return signature;
  }

  /**
   * Sets base64 encoded firmware signature.
   *
   * @param signature Base64 encoded firmware signature
   */
  public void setSignature(@Nullable String signature) {
    if (!isValidSignature(signature)) {
      throw new PropertyConstraintException(signature, "signature is invalid");
    }
    this.signature = signature;
  }

  /**
   * Returns whether the given signature is valid
   *
   * @param signature the signature to check the validity of
   * @return {@code true} if signature is valid, {@code false} if not
   */
  private boolean isValidSignature(@Nullable String signature) {
    return signature == null || signature.length() <= 800;
  }

  /**
   * Adds base64 encoded firmware signature.
   *
   * @param signature Base64 encoded firmware signature
   * @return this
   */
  public Firmware withSignature(@Nullable String signature) {
    setSignature(signature);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidLocation(location)
        && isValidRetrieveDateTime(retrieveDateTime)
        && isValidSigningCertificate(signingCertificate)
        && isValidSignature(signature);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Firmware that = (Firmware) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(location, that.location)
        && Objects.equals(retrieveDateTime, that.retrieveDateTime)
        && Objects.equals(installDateTime, that.installDateTime)
        && Objects.equals(signingCertificate, that.signingCertificate)
        && Objects.equals(signature, that.signature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, location, retrieveDateTime, installDateTime, signingCertificate, signature);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("location", location)
        .add("retrieveDateTime", retrieveDateTime)
        .add("installDateTime", installDateTime)
        .add("signingCertificate", signingCertificate)
        .add("signature", signature)
        .add("isValid", validate())
        .toString();
  }
}
