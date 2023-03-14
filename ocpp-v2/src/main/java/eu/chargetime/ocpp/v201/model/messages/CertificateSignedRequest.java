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
 * CertificateSignedRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class CertificateSignedRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * The signed PEM encoded X.509 certificate. This can also contain the necessary sub CA
   * certificates. In that case, the order of the bundle should follow the certificate chain,
   * starting from the leaf certificate.
   *
   * <p>The Configuration Variable MaxCertificateChainSize can be used to limit the maximum size of
   * this field.
   */
  private String certificateChain;

  /**
   * The type of the signed certificate that is returned. When omitted the certificate is used for
   * both the 15118 connection (if implemented) and the Charging Station to CSMS connection. This
   * field is required when a typeOfCertificate was included in the SignCertificateRequest that
   * requested this certificate to be signed AND both the 15118 connection and the Charging Station
   * connection are implemented.
   */
  @Nullable private CertificateSigningUseEnum certificateType;

  /**
   * Constructor for the CertificateSignedRequest class
   *
   * @param certificateChain The signed PEM encoded X.509 certificate. This can also contain the
   *     necessary sub CA certificates. In that case, the order of the bundle should follow the
   *     certificate chain, starting from the leaf certificate.
   */
  public CertificateSignedRequest(String certificateChain) {
    setCertificateChain(certificateChain);
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
  public CertificateSignedRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the signed PEM encoded X.509 certificate. This can also contain the necessary sub CA
   * certificates. In that case, the order of the bundle should follow the certificate chain,
   * starting from the leaf certificate.
   *
   * @return The signed PEM encoded X.509 certificate
   */
  public String getCertificateChain() {
    return certificateChain;
  }

  /**
   * Sets the signed PEM encoded X.509 certificate. This can also contain the necessary sub CA
   * certificates. In that case, the order of the bundle should follow the certificate chain,
   * starting from the leaf certificate.
   *
   * @param certificateChain The signed PEM encoded X.509 certificate
   */
  public void setCertificateChain(String certificateChain) {
    if (!isValidCertificateChain(certificateChain)) {
      throw new PropertyConstraintException(certificateChain, "certificateChain is invalid");
    }
    this.certificateChain = certificateChain;
  }

  /**
   * Returns whether the given certificateChain is valid
   *
   * @param certificateChain the certificateChain to check the validity of
   * @return {@code true} if certificateChain is valid, {@code false} if not
   */
  private boolean isValidCertificateChain(String certificateChain) {
    return certificateChain != null && certificateChain.length() <= 10000;
  }

  /**
   * Gets the type of the signed certificate that is returned. When omitted the certificate is used
   * for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.
   * This field is required when a typeOfCertificate was included in the SignCertificateRequest that
   * requested this certificate to be signed AND both the 15118 connection and the Charging Station
   * connection are implemented.
   *
   * @return The type of the signed certificate that is returned
   */
  @Nullable
  public CertificateSigningUseEnum getCertificateType() {
    return certificateType;
  }

  /**
   * Sets the type of the signed certificate that is returned. When omitted the certificate is used
   * for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.
   * This field is required when a typeOfCertificate was included in the SignCertificateRequest that
   * requested this certificate to be signed AND both the 15118 connection and the Charging Station
   * connection are implemented.
   *
   * @param certificateType The type of the signed certificate that is returned
   */
  public void setCertificateType(@Nullable CertificateSigningUseEnum certificateType) {
    this.certificateType = certificateType;
  }

  /**
   * Adds the type of the signed certificate that is returned. When omitted the certificate is used
   * for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.
   * This field is required when a typeOfCertificate was included in the SignCertificateRequest that
   * requested this certificate to be signed AND both the 15118 connection and the Charging Station
   * connection are implemented.
   *
   * @param certificateType The type of the signed certificate that is returned
   * @return this
   */
  public CertificateSignedRequest withCertificateType(
      @Nullable CertificateSigningUseEnum certificateType) {
    setCertificateType(certificateType);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidCertificateChain(certificateChain);
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
    CertificateSignedRequest that = (CertificateSignedRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(certificateChain, that.certificateChain)
        && Objects.equals(certificateType, that.certificateType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, certificateChain, certificateType);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("certificateChain", certificateChain)
        .add("certificateType", certificateType)
        .add("isValid", validate())
        .toString();
  }
}
