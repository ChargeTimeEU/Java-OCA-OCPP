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
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/** Data necessary to request the revocation status of a certificate. */
public final class CertificateStatusRequestInfo {
  /** certificateHashData */
  private CertificateHashData certificateHashData;

  /** Source of status: OCSP, CRL */
  private CertificateStatusSourceEnum source;

  /** URL(s) of source. */
  private String[] urls;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the CertificateStatusRequestInfo class
   *
   * @param certificateHashData certificateHashData
   * @param source Source of status: OCSP, CRL
   * @param urls URL(s) of source.
   */
  public CertificateStatusRequestInfo(
      CertificateHashData certificateHashData, CertificateStatusSourceEnum source, String[] urls) {
    setCertificateHashData(certificateHashData);
    setSource(source);
    setUrls(urls);
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
   * Gets URL(s) of source.
   *
   * @return URL(s) of source
   */
  public String[] getUrls() {
    return urls;
  }

  /**
   * Sets URL(s) of source.
   *
   * @param urls URL(s) of source
   */
  public void setUrls(String[] urls) {
    if (!isValidUrls(urls)) {
      throw new PropertyConstraintException(urls, "urls is invalid");
    }
    this.urls = urls;
  }

  /**
   * Returns whether the given urls is valid
   *
   * @param urls the urls to check the validity of
   * @return {@code true} if urls is valid, {@code false} if not
   */
  private boolean isValidUrls(String[] urls) {
    return urls != null
        && urls.length >= 1
        && urls.length <= 5
        && Arrays.stream(urls).allMatch(item -> item.length() <= 2000);
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
  public CertificateStatusRequestInfo withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidCertificateHashData(certificateHashData)
        && isValidSource(source)
        && isValidUrls(urls)
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
    CertificateStatusRequestInfo that = (CertificateStatusRequestInfo) o;
    return Objects.equals(certificateHashData, that.certificateHashData)
        && Objects.equals(source, that.source)
        && Arrays.equals(urls, that.urls)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(certificateHashData, source, Arrays.hashCode(urls), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("certificateHashData", certificateHashData)
        .add("source", source)
        .add("urls", urls)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
