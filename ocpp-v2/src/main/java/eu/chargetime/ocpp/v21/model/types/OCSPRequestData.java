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

/** Information about a certificate for an OCSP check. */
public final class OCSPRequestData {
  /** Used algorithms for the hashes provided. */
  private HashAlgorithmEnum hashAlgorithm;

  /**
   * The hash of the issuer’s distinguished name (DN), that must be calculated over the DER encoding
   * of the issuer’s name field in the certificate being checked.
   */
  private String issuerNameHash;

  /**
   * The hash of the DER encoded public key: the value (excluding tag and length) of the subject
   * public key field in the issuer’s certificate.
   */
  private String issuerKeyHash;

  /**
   * The string representation of the hexadecimal value of the serial number without the prefix "0x"
   * and without leading zeroes.
   */
  private String serialNumber;

  /** The responder URL (Case insensitive). */
  private String responderURL;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the OCSPRequestData class
   *
   * @param hashAlgorithm Used algorithms for the hashes provided.
   * @param issuerNameHash The hash of the issuer’s distinguished name (DN), that must be calculated
   *     over the DER encoding of the issuer’s name field in the certificate being checked.
   * @param issuerKeyHash The hash of the DER encoded public key: the value (excluding tag and
   *     length) of the subject public key field in the issuer’s certificate.
   * @param serialNumber The string representation of the hexadecimal value of the serial number
   *     without the prefix "0x" and without leading zeroes.
   * @param responderURL The responder URL (Case insensitive).
   */
  public OCSPRequestData(
      HashAlgorithmEnum hashAlgorithm,
      String issuerNameHash,
      String issuerKeyHash,
      String serialNumber,
      String responderURL) {
    setHashAlgorithm(hashAlgorithm);
    setIssuerNameHash(issuerNameHash);
    setIssuerKeyHash(issuerKeyHash);
    setSerialNumber(serialNumber);
    setResponderURL(responderURL);
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
   * Gets the hash of the issuer’s distinguished name (DN), that must be calculated over the DER
   * encoding of the issuer’s name field in the certificate being checked.
   *
   * @return The hash of the issuer’s distinguished name (DN), that must be calculated over the DER
   *     encoding of the issuer’s name field in the certificate being checked
   */
  public String getIssuerNameHash() {
    return issuerNameHash;
  }

  /**
   * Sets the hash of the issuer’s distinguished name (DN), that must be calculated over the DER
   * encoding of the issuer’s name field in the certificate being checked.
   *
   * @param issuerNameHash The hash of the issuer’s distinguished name (DN), that must be calculated
   *     over the DER encoding of the issuer’s name field in the certificate being checked
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
   * Gets the hash of the DER encoded public key: the value (excluding tag and length) of the
   * subject public key field in the issuer’s certificate.
   *
   * @return The hash of the DER encoded public key: the value (excluding tag and length) of the
   *     subject public key field in the issuer’s certificate
   */
  public String getIssuerKeyHash() {
    return issuerKeyHash;
  }

  /**
   * Sets the hash of the DER encoded public key: the value (excluding tag and length) of the
   * subject public key field in the issuer’s certificate.
   *
   * @param issuerKeyHash The hash of the DER encoded public key: the value (excluding tag and
   *     length) of the subject public key field in the issuer’s certificate
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
   * Gets the string representation of the hexadecimal value of the serial number without the prefix
   * "0x" and without leading zeroes.
   *
   * @return The string representation of the hexadecimal value of the serial number without the
   *     prefix "0x" and without leading zeroes
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Sets the string representation of the hexadecimal value of the serial number without the prefix
   * "0x" and without leading zeroes.
   *
   * @param serialNumber The string representation of the hexadecimal value of the serial number
   *     without the prefix "0x" and without leading zeroes
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

  /**
   * Gets the responder URL (Case insensitive).
   *
   * @return The responder URL (Case insensitive)
   */
  public String getResponderURL() {
    return responderURL;
  }

  /**
   * Sets the responder URL (Case insensitive).
   *
   * @param responderURL The responder URL (Case insensitive)
   */
  public void setResponderURL(String responderURL) {
    if (!isValidResponderURL(responderURL)) {
      throw new PropertyConstraintException(responderURL, "responderURL is invalid");
    }
    this.responderURL = responderURL;
  }

  /**
   * Returns whether the given responderURL is valid
   *
   * @param responderURL the responderURL to check the validity of
   * @return {@code true} if responderURL is valid, {@code false} if not
   */
  private boolean isValidResponderURL(String responderURL) {
    return responderURL != null && responderURL.length() <= 2000;
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
  public OCSPRequestData withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidHashAlgorithm(hashAlgorithm)
        && isValidIssuerNameHash(issuerNameHash)
        && isValidIssuerKeyHash(issuerKeyHash)
        && isValidSerialNumber(serialNumber)
        && isValidResponderURL(responderURL)
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
    OCSPRequestData that = (OCSPRequestData) o;
    return Objects.equals(hashAlgorithm, that.hashAlgorithm)
        && Objects.equals(issuerNameHash, that.issuerNameHash)
        && Objects.equals(issuerKeyHash, that.issuerKeyHash)
        && Objects.equals(serialNumber, that.serialNumber)
        && Objects.equals(responderURL, that.responderURL)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        hashAlgorithm, issuerNameHash, issuerKeyHash, serialNumber, responderURL, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("hashAlgorithm", hashAlgorithm)
        .add("issuerNameHash", issuerNameHash)
        .add("issuerKeyHash", issuerKeyHash)
        .add("serialNumber", serialNumber)
        .add("responderURL", responderURL)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
