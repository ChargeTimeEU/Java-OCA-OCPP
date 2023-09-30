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

/** CertificateHashDataType */
public final class CertificateHashData {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Used algorithms for the hashes provided. */
  private HashAlgorithmEnum hashAlgorithm;

  /** Hashed value of the Issuer DN (Distinguished Name). */
  private String issuerNameHash;

  /** Hashed value of the issuers public key */
  private String issuerKeyHash;

  /** The serial number of the certificate. */
  private String serialNumber;

  /**
   * Constructor for the CertificateHashData class
   *
   * @param hashAlgorithm Used algorithms for the hashes provided.
   * @param issuerNameHash Hashed value of the Issuer DN (Distinguished Name).
   * @param issuerKeyHash Hashed value of the issuers public key
   * @param serialNumber The serial number of the certificate.
   */
  public CertificateHashData(
      HashAlgorithmEnum hashAlgorithm,
      String issuerNameHash,
      String issuerKeyHash,
      String serialNumber) {
    setHashAlgorithm(hashAlgorithm);
    setIssuerNameHash(issuerNameHash);
    setIssuerKeyHash(issuerKeyHash);
    setSerialNumber(serialNumber);
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
  public CertificateHashData withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets used algorithms for the hashes provided.
   *
   * @return Used algorithms for the hashes provided
   */
  public HashAlgorithmEnum getHashAlgorithm() {
    return hashAlgorithm;
  }

  /**
   * Sets used algorithms for the hashes provided.
   *
   * @param hashAlgorithm Used algorithms for the hashes provided
   */
  public void setHashAlgorithm(HashAlgorithmEnum hashAlgorithm) {
    if (!isValidHashAlgorithm(hashAlgorithm)) {
      throw new PropertyConstraintException(hashAlgorithm, "hashAlgorithm is invalid");
    }
    this.hashAlgorithm = hashAlgorithm;
  }

  /**
   * Returns whether the given hashAlgorithm is valid
   *
   * @param hashAlgorithm the hashAlgorithm to check the validity of
   * @return {@code true} if hashAlgorithm is valid, {@code false} if not
   */
  private boolean isValidHashAlgorithm(HashAlgorithmEnum hashAlgorithm) {
    return hashAlgorithm != null;
  }

  /**
   * Gets hashed value of the Issuer DN (Distinguished Name).
   *
   * @return Hashed value of the Issuer DN (Distinguished Name)
   */
  public String getIssuerNameHash() {
    return issuerNameHash;
  }

  /**
   * Sets hashed value of the Issuer DN (Distinguished Name).
   *
   * @param issuerNameHash Hashed value of the Issuer DN (Distinguished Name)
   */
  public void setIssuerNameHash(String issuerNameHash) {
    if (!isValidIssuerNameHash(issuerNameHash)) {
      throw new PropertyConstraintException(issuerNameHash, "issuerNameHash is invalid");
    }
    this.issuerNameHash = issuerNameHash;
  }

  /**
   * Returns whether the given issuerNameHash is valid
   *
   * @param issuerNameHash the issuerNameHash to check the validity of
   * @return {@code true} if issuerNameHash is valid, {@code false} if not
   */
  private boolean isValidIssuerNameHash(String issuerNameHash) {
    return issuerNameHash != null && issuerNameHash.length() <= 128;
  }

  /**
   * Gets hashed value of the issuers public key
   *
   * @return Hashed value of the issuers public key
   */
  public String getIssuerKeyHash() {
    return issuerKeyHash;
  }

  /**
   * Sets hashed value of the issuers public key
   *
   * @param issuerKeyHash Hashed value of the issuers public key
   */
  public void setIssuerKeyHash(String issuerKeyHash) {
    if (!isValidIssuerKeyHash(issuerKeyHash)) {
      throw new PropertyConstraintException(issuerKeyHash, "issuerKeyHash is invalid");
    }
    this.issuerKeyHash = issuerKeyHash;
  }

  /**
   * Returns whether the given issuerKeyHash is valid
   *
   * @param issuerKeyHash the issuerKeyHash to check the validity of
   * @return {@code true} if issuerKeyHash is valid, {@code false} if not
   */
  private boolean isValidIssuerKeyHash(String issuerKeyHash) {
    return issuerKeyHash != null && issuerKeyHash.length() <= 128;
  }

  /**
   * Gets the serial number of the certificate.
   *
   * @return The serial number of the certificate
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Sets the serial number of the certificate.
   *
   * @param serialNumber The serial number of the certificate
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
    return serialNumber != null && serialNumber.length() <= 40;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidHashAlgorithm(hashAlgorithm)
        && isValidIssuerNameHash(issuerNameHash)
        && isValidIssuerKeyHash(issuerKeyHash)
        && isValidSerialNumber(serialNumber);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CertificateHashData that = (CertificateHashData) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(hashAlgorithm, that.hashAlgorithm)
        && Objects.equals(issuerNameHash, that.issuerNameHash)
        && Objects.equals(issuerKeyHash, that.issuerKeyHash)
        && Objects.equals(serialNumber, that.serialNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, hashAlgorithm, issuerNameHash, issuerKeyHash, serialNumber);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("hashAlgorithm", hashAlgorithm)
        .add("issuerNameHash", issuerNameHash)
        .add("issuerKeyHash", issuerKeyHash)
        .add("serialNumber", serialNumber)
        .add("isValid", validate())
        .toString();
  }
}
