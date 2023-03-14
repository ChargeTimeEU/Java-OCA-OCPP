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
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/** CertificateHashDataChainType */
public final class CertificateHashDataChain {
  /** Custom data */
  @Nullable private CustomData customData;

  /** certificateHashData */
  private CertificateHashData certificateHashData;

  /** The type of the requested certificate(s). */
  private GetCertificateIdUseEnum certificateType;

  /** childCertificateHashData */
  @Nullable private CertificateHashData[] childCertificateHashData;

  /**
   * Constructor for the CertificateHashDataChain class
   *
   * @param certificateHashData certificateHashData
   * @param certificateType The type of the requested certificate(s).
   */
  public CertificateHashDataChain(
      CertificateHashData certificateHashData, GetCertificateIdUseEnum certificateType) {
    setCertificateHashData(certificateHashData);
    setCertificateType(certificateType);
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
  public CertificateHashDataChain withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
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
   * Gets the type of the requested certificate(s).
   *
   * @return The type of the requested certificate(s)
   */
  public GetCertificateIdUseEnum getCertificateType() {
    return certificateType;
  }

  /**
   * Sets the type of the requested certificate(s).
   *
   * @param certificateType The type of the requested certificate(s)
   */
  public void setCertificateType(GetCertificateIdUseEnum certificateType) {
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
  private boolean isValidCertificateType(GetCertificateIdUseEnum certificateType) {
    return certificateType != null;
  }

  /**
   * Gets childCertificateHashData
   *
   * @return childCertificateHashData
   */
  @Nullable
  public CertificateHashData[] getChildCertificateHashData() {
    return childCertificateHashData;
  }

  /**
   * Sets childCertificateHashData
   *
   * @param childCertificateHashData childCertificateHashData
   */
  public void setChildCertificateHashData(
      @Nullable CertificateHashData[] childCertificateHashData) {
    if (!isValidChildCertificateHashData(childCertificateHashData)) {
      throw new PropertyConstraintException(
          childCertificateHashData, "childCertificateHashData is invalid");
    }
    this.childCertificateHashData = childCertificateHashData;
  }

  /**
   * Returns whether the given childCertificateHashData is valid
   *
   * @param childCertificateHashData the childCertificateHashData to check the validity of
   * @return {@code true} if childCertificateHashData is valid, {@code false} if not
   */
  private boolean isValidChildCertificateHashData(
      @Nullable CertificateHashData[] childCertificateHashData) {
    return childCertificateHashData == null
        || (childCertificateHashData.length >= 1
            && childCertificateHashData.length <= 4
            && Arrays.stream(childCertificateHashData).allMatch(item -> item.validate()));
  }

  /**
   * Adds childCertificateHashData
   *
   * @param childCertificateHashData childCertificateHashData
   * @return this
   */
  public CertificateHashDataChain withChildCertificateHashData(
      @Nullable CertificateHashData[] childCertificateHashData) {
    setChildCertificateHashData(childCertificateHashData);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidCertificateHashData(certificateHashData)
        && isValidCertificateType(certificateType)
        && isValidChildCertificateHashData(childCertificateHashData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CertificateHashDataChain that = (CertificateHashDataChain) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(certificateHashData, that.certificateHashData)
        && Objects.equals(certificateType, that.certificateType)
        && Arrays.equals(childCertificateHashData, that.childCertificateHashData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        certificateHashData,
        certificateType,
        Arrays.hashCode(childCertificateHashData));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("certificateHashData", certificateHashData)
        .add("certificateType", certificateType)
        .add("childCertificateHashData", childCertificateHashData)
        .add("isValid", validate())
        .toString();
  }
}
