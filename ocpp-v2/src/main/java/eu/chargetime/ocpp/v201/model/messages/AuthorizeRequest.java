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

package eu.chargetime.ocpp.v201.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.IdToken;
import eu.chargetime.ocpp.v201.model.types.OCSPRequestData;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * AuthorizeRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class AuthorizeRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  private IdToken idToken;

  /** The X.509 certificated presented by EV and encoded in PEM format. */
  @Nullable private String certificate;

  /** iso15118CertificateHashData */
  @Nullable private OCSPRequestData[] iso15118CertificateHashData;

  /**
   * Constructor for the AuthorizeRequest class
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   */
  public AuthorizeRequest(IdToken idToken) {
    setIdToken(idToken);
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
  public AuthorizeRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @return A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
  public IdToken getIdToken() {
    return idToken;
  }

  /**
   * Sets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
  public void setIdToken(IdToken idToken) {
    if (!isValidIdToken(idToken)) {
      throw new PropertyConstraintException(idToken, "idToken is invalid");
    }
    this.idToken = idToken;
  }

  /**
   * Returns whether the given idToken is valid
   *
   * @param idToken the idToken to check the validity of
   * @return {@code true} if idToken is valid, {@code false} if not
   */
  private boolean isValidIdToken(IdToken idToken) {
    return idToken != null && idToken.validate();
  }

  /**
   * Gets the X.509 certificated presented by EV and encoded in PEM format.
   *
   * @return The X.509 certificated presented by EV and encoded in PEM format
   */
  @Nullable
  public String getCertificate() {
    return certificate;
  }

  /**
   * Sets the X.509 certificated presented by EV and encoded in PEM format.
   *
   * @param certificate The X.509 certificated presented by EV and encoded in PEM format
   */
  public void setCertificate(@Nullable String certificate) {
    if (!isValidCertificate(certificate)) {
      throw new PropertyConstraintException(certificate, "certificate is invalid");
    }
    this.certificate = certificate;
  }

  /**
   * Returns whether the given certificate is valid
   *
   * @param certificate the certificate to check the validity of
   * @return {@code true} if certificate is valid, {@code false} if not
   */
  private boolean isValidCertificate(@Nullable String certificate) {
    return certificate == null || certificate.length() <= 5500;
  }

  /**
   * Adds the X.509 certificated presented by EV and encoded in PEM format.
   *
   * @param certificate The X.509 certificated presented by EV and encoded in PEM format
   * @return this
   */
  public AuthorizeRequest withCertificate(@Nullable String certificate) {
    setCertificate(certificate);
    return this;
  }

  /**
   * Gets iso15118CertificateHashData
   *
   * @return iso15118CertificateHashData
   */
  @Nullable
  public OCSPRequestData[] getIso15118CertificateHashData() {
    return iso15118CertificateHashData;
  }

  /**
   * Sets iso15118CertificateHashData
   *
   * @param iso15118CertificateHashData iso15118CertificateHashData
   */
  public void setIso15118CertificateHashData(
      @Nullable OCSPRequestData[] iso15118CertificateHashData) {
    if (!isValidIso15118CertificateHashData(iso15118CertificateHashData)) {
      throw new PropertyConstraintException(
          iso15118CertificateHashData, "iso15118CertificateHashData is invalid");
    }
    this.iso15118CertificateHashData = iso15118CertificateHashData;
  }

  /**
   * Returns whether the given iso15118CertificateHashData is valid
   *
   * @param iso15118CertificateHashData the iso15118CertificateHashData to check the validity of
   * @return {@code true} if iso15118CertificateHashData is valid, {@code false} if not
   */
  private boolean isValidIso15118CertificateHashData(
      @Nullable OCSPRequestData[] iso15118CertificateHashData) {
    return iso15118CertificateHashData == null
        || (iso15118CertificateHashData.length >= 1
            && iso15118CertificateHashData.length <= 4
            && Arrays.stream(iso15118CertificateHashData).allMatch(item -> item.validate()));
  }

  /**
   * Adds iso15118CertificateHashData
   *
   * @param iso15118CertificateHashData iso15118CertificateHashData
   * @return this
   */
  public AuthorizeRequest withIso15118CertificateHashData(
      @Nullable OCSPRequestData[] iso15118CertificateHashData) {
    setIso15118CertificateHashData(iso15118CertificateHashData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidIdToken(idToken)
        && isValidCertificate(certificate)
        && isValidIso15118CertificateHashData(iso15118CertificateHashData);
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizeRequest that = (AuthorizeRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(idToken, that.idToken)
        && Objects.equals(certificate, that.certificate)
        && Arrays.equals(iso15118CertificateHashData, that.iso15118CertificateHashData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, idToken, certificate, Arrays.hashCode(iso15118CertificateHashData));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("idToken", idToken)
        .add("certificate", certificate)
        .add("iso15118CertificateHashData", iso15118CertificateHashData)
        .add("isValid", validate())
        .toString();
  }
}
