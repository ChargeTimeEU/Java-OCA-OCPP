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
import eu.chargetime.ocpp.v201.model.types.CertificateSigningUseEnum;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * SignCertificateRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class SignCertificateRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * The Charging Station SHALL send the public key in form of a Certificate Signing Request (CSR)
   * as described in RFC 2986 [22] and then PEM encoded, using the SignCertificateRequest message.
   */
  private String csr;

  /**
   * The type of certificate that is to be signed. When omitted the certificate is to be used for
   * both the 15118 connection (if implemented) and the Charging Station to CSMS connection.
   */
  @Nullable private CertificateSigningUseEnum certificateType;

  /**
   * Constructor for the SignCertificateRequest class
   *
   * @param csr The Charging Station SHALL send the public key in form of a Certificate Signing
   *     Request (CSR) as described in RFC 2986 [22] and then PEM encoded, using the
   *     SignCertificateRequest message.
   */
  public SignCertificateRequest(String csr) {
    setCsr(csr);
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
  public SignCertificateRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the Charging Station SHALL send the public key in form of a Certificate Signing Request
   * (CSR) as described in RFC 2986 [22] and then PEM encoded, using the SignCertificateRequest
   * message.
   *
   * @return The Charging Station SHALL send the public key in form of a Certificate Signing Request
   *     (CSR) as described in RFC 2986 [22] and then PEM encoded, using the SignCertificateRequest
   *     message
   */
  public String getCsr() {
    return csr;
  }

  /**
   * Sets the Charging Station SHALL send the public key in form of a Certificate Signing Request
   * (CSR) as described in RFC 2986 [22] and then PEM encoded, using the SignCertificateRequest
   * message.
   *
   * @param csr The Charging Station SHALL send the public key in form of a Certificate Signing
   *     Request (CSR) as described in RFC 2986 [22] and then PEM encoded, using the
   *     SignCertificateRequest message
   */
  public void setCsr(String csr) {
    if (!isValidCsr(csr)) {
      throw new PropertyConstraintException(csr, "csr is invalid");
    }
    this.csr = csr;
  }

  /**
   * Returns whether the given csr is valid
   *
   * @param csr the csr to check the validity of
   * @return {@code true} if csr is valid, {@code false} if not
   */
  private boolean isValidCsr(String csr) {
    return csr != null && csr.length() <= 5500;
  }

  /**
   * Gets the type of certificate that is to be signed. When omitted the certificate is to be used
   * for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.
   *
   * @return The type of certificate that is to be signed
   */
  @Nullable
  public CertificateSigningUseEnum getCertificateType() {
    return certificateType;
  }

  /**
   * Sets the type of certificate that is to be signed. When omitted the certificate is to be used
   * for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.
   *
   * @param certificateType The type of certificate that is to be signed
   */
  public void setCertificateType(@Nullable CertificateSigningUseEnum certificateType) {
    this.certificateType = certificateType;
  }

  /**
   * Adds the type of certificate that is to be signed. When omitted the certificate is to be used
   * for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.
   *
   * @param certificateType The type of certificate that is to be signed
   * @return this
   */
  public SignCertificateRequest withCertificateType(
      @Nullable CertificateSigningUseEnum certificateType) {
    setCertificateType(certificateType);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidCsr(csr);
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
    SignCertificateRequest that = (SignCertificateRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(csr, that.csr)
        && Objects.equals(certificateType, that.certificateType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, csr, certificateType);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("csr", csr)
        .add("certificateType", certificateType)
        .add("isValid", validate())
        .toString();
  }
}
