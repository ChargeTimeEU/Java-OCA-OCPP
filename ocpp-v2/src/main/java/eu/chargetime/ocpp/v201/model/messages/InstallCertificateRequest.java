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
import eu.chargetime.ocpp.v201.model.types.InstallCertificateUseEnum;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * InstallCertificateRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class InstallCertificateRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The certificate type that is sent. */
  private InstallCertificateUseEnum certificateType;

  /** A PEM encoded X.509 certificate. */
  private String certificate;

  /**
   * Constructor for the InstallCertificateRequest class
   *
   * @param certificateType The certificate type that is sent.
   * @param certificate A PEM encoded X.509 certificate.
   */
  public InstallCertificateRequest(InstallCertificateUseEnum certificateType, String certificate) {
    setCertificateType(certificateType);
    setCertificate(certificate);
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
  public InstallCertificateRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the certificate type that is sent.
   *
   * @return The certificate type that is sent
   */
  public InstallCertificateUseEnum getCertificateType() {
    return certificateType;
  }

  /**
   * Sets the certificate type that is sent.
   *
   * @param certificateType The certificate type that is sent
   */
  public void setCertificateType(InstallCertificateUseEnum certificateType) {
    if (!isValidCertificateType(certificateType)) {
      throw new PropertyConstraintException(certificateType, "certificateType is invalid");
    }
    this.certificateType = certificateType;
  }

  /**
   * Returns whether the given certificateType is valid
   *
   * @param certificateType the certificateType to check the validity of
   * @return {@code true} if certificateType is valid, {@code false} if not
   */
  private boolean isValidCertificateType(InstallCertificateUseEnum certificateType) {
    return certificateType != null;
  }

  /**
   * Gets a PEM encoded X.509 certificate.
   *
   * @return A PEM encoded X.509 certificate
   */
  public String getCertificate() {
    return certificate;
  }

  /**
   * Sets a PEM encoded X.509 certificate.
   *
   * @param certificate A PEM encoded X.509 certificate
   */
  public void setCertificate(String certificate) {
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
  private boolean isValidCertificate(String certificate) {
    return certificate != null && certificate.length() <= 5500;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidCertificateType(certificateType)
        && isValidCertificate(certificate);
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
    InstallCertificateRequest that = (InstallCertificateRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(certificateType, that.certificateType)
        && Objects.equals(certificate, that.certificate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, certificateType, certificate);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("certificateType", certificateType)
        .add("certificate", certificate)
        .add("isValid", validate())
        .toString();
  }
}
