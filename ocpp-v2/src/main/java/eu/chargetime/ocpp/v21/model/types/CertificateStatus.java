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

/** Revocation status of certificate */
public final class CertificateStatus {
  /** certificateHashData */
  private CertificateHashData certificateHashData;

  /** Source of status: OCSP, CRL */
  private CertificateStatusSourceEnum source;

  /** Status of certificate: good, revoked or unknown. */
  private CertificateStatusEnum status;

  /** nextUpdate */
  private ZonedDateTime nextUpdate;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the CertificateStatus class
   *
   * @param certificateHashData certificateHashData
   * @param source Source of status: OCSP, CRL
   * @param status Status of certificate: good, revoked or unknown.
   * @param nextUpdate nextUpdate
   */
  public CertificateStatus(
      CertificateHashData certificateHashData,
      CertificateStatusSourceEnum source,
      CertificateStatusEnum status,
      ZonedDateTime nextUpdate) {
    setCertificateHashData(certificateHashData);
    setSource(source);
    setStatus(status);
    setNextUpdate(nextUpdate);
  }

  /**
   * Gets certificateHashData
   *
   * @return certificateHashData
   */
  public CertificateHashData getCertificateHashData() {
    return certificateHashData;
  }

  /**
   * Sets certificateHashData
   *
   * @param certificateHashData certificateHashData
   */
  public void setCertificateHashData(CertificateHashData certificateHashData) {
    if (!isValidCertificateHashData(certificateHashData)) {
      throw new PropertyConstraintException(certificateHashData, "certificateHashData is invalid");
    }
    this.certificateHashData = certificateHashData;
  }

  /**
   * Returns whether the given certificateHashData is valid
   *
   * @param certificateHashData the certificateHashData to check the validity of
   * @return {@code true} if certificateHashData is valid, {@code false} if not
   */
  private boolean isValidCertificateHashData(CertificateHashData certificateHashData) {
    return certificateHashData != null && certificateHashData.validate();
  }

  /**
   * Gets source of status: OCSP, CRL
   *
   * @return Source of status: OCSP, CRL
   */
  public CertificateStatusSourceEnum getSource() {
    return source;
  }

  /**
   * Sets source of status: OCSP, CRL
   *
   * @param source Source of status: OCSP, CRL
   */
  public void setSource(CertificateStatusSourceEnum source) {
    if (!isValidSource(source)) {
      throw new PropertyConstraintException(source, "source is invalid");
    }
    this.source = source;
  }

  /**
   * Returns whether the given source is valid
   *
   * @param source the source to check the validity of
   * @return {@code true} if source is valid, {@code false} if not
   */
  private boolean isValidSource(CertificateStatusSourceEnum source) {
    return source != null;
  }

  /**
   * Gets status of certificate: good, revoked or unknown.
   *
   * @return Status of certificate: good, revoked or unknown
   */
  public CertificateStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets status of certificate: good, revoked or unknown.
   *
   * @param status Status of certificate: good, revoked or unknown
   */
  public void setStatus(CertificateStatusEnum status) {
    if (!isValidStatus(status)) {
      throw new PropertyConstraintException(status, "status is invalid");
    }
    this.status = status;
  }

  /**
   * Returns whether the given status is valid
   *
   * @param status the status to check the validity of
   * @return {@code true} if status is valid, {@code false} if not
   */
  private boolean isValidStatus(CertificateStatusEnum status) {
    return status != null;
  }

  /**
   * Gets nextUpdate
   *
   * @return nextUpdate
   */
  public ZonedDateTime getNextUpdate() {
    return nextUpdate;
  }

  /**
   * Sets nextUpdate
   *
   * @param nextUpdate nextUpdate
   */
  public void setNextUpdate(ZonedDateTime nextUpdate) {
    if (!isValidNextUpdate(nextUpdate)) {
      throw new PropertyConstraintException(nextUpdate, "nextUpdate is invalid");
    }
    this.nextUpdate = nextUpdate;
  }

  /**
   * Returns whether the given nextUpdate is valid
   *
   * @param nextUpdate the nextUpdate to check the validity of
   * @return {@code true} if nextUpdate is valid, {@code false} if not
   */
  private boolean isValidNextUpdate(ZonedDateTime nextUpdate) {
    return nextUpdate != null;
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
  public CertificateStatus withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidCertificateHashData(certificateHashData)
        && isValidSource(source)
        && isValidStatus(status)
        && isValidNextUpdate(nextUpdate)
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
    CertificateStatus that = (CertificateStatus) o;
    return Objects.equals(certificateHashData, that.certificateHashData)
        && Objects.equals(source, that.source)
        && Objects.equals(status, that.status)
        && Objects.equals(nextUpdate, that.nextUpdate)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(certificateHashData, source, status, nextUpdate, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("certificateHashData", certificateHashData)
        .add("source", source)
        .add("status", status)
        .add("nextUpdate", nextUpdate)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
