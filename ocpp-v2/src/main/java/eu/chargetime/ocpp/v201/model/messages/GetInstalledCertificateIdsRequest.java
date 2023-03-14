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
import eu.chargetime.ocpp.v201.model.types.GetCertificateIdUseEnum;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetInstalledCertificateIdsRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetInstalledCertificateIdsRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The type of certificates requested. When omitted, all certificate types are requested. */
  @Nullable private GetCertificateIdUseEnum[] certificateType;

  /** Constructor for the GetInstalledCertificateIdsRequest class */
  public GetInstalledCertificateIdsRequest() {}

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

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidCertificateType(certificateType);
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
    return Objects.equals(customData, that.customData)
        && Arrays.equals(certificateType, that.certificateType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, Arrays.hashCode(certificateType));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("certificateType", certificateType)
        .add("isValid", validate())
        .toString();
  }
}
