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

package eu.chargetime.ocpp.v21.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.GetCertificateIdUseEnum;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetInstalledCertificateIdsRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class GetInstalledCertificateIdsRequest extends RequestWithId {
  /** The type of certificates requested. When omitted, all certificate types are requested. */
  @Nullable private GetCertificateIdUseEnum[] certificateType;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the GetInstalledCertificateIdsRequest class */
  public GetInstalledCertificateIdsRequest() {}

  /**
   * Gets the type of certificates requested. When omitted, all certificate types are requested.
   *
   * @return The type of certificates requested
   */
  @Nullable
  public GetCertificateIdUseEnum[] getCertificateType() {
    return certificateType;
  }

  /**
   * Sets the type of certificates requested. When omitted, all certificate types are requested.
   *
   * @param certificateType The type of certificates requested
   */
  public void setCertificateType(@Nullable GetCertificateIdUseEnum[] certificateType) {
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
  private boolean isValidCertificateType(@Nullable GetCertificateIdUseEnum[] certificateType) {
    return certificateType == null || (certificateType.length >= 1);
  }

  /**
   * Adds the type of certificates requested. When omitted, all certificate types are requested.
   *
   * @param certificateType The type of certificates requested
   * @return this
   */
  public GetInstalledCertificateIdsRequest withCertificateType(
      @Nullable GetCertificateIdUseEnum[] certificateType) {
    setCertificateType(certificateType);
    return this;
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
  public GetInstalledCertificateIdsRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCertificateType(certificateType) && isValidCustomData(customData);
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
    GetInstalledCertificateIdsRequest that = (GetInstalledCertificateIdsRequest) o;
    return Arrays.equals(certificateType, that.certificateType)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(certificateType), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("certificateType", certificateType)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
