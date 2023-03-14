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
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.GetCertificateStatusEnum;
import eu.chargetime.ocpp.v201.model.types.StatusInfo;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetCertificateStatusResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetCertificateStatusResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Whether the charging station was able to retrieve the OCSP certificate status. */
  private GetCertificateStatusEnum status;

  /** More information about the status. */
  @Nullable private StatusInfo statusInfo;

  /**
   * OCSPResponse class as defined in IETF RFC 6960. DER encoded (as defined in IETF RFC 6960), and
   * then base64 encoded. MAY only be omitted when status is not Accepted.
   */
  @Nullable private String ocspResult;

  /**
   * Constructor for the GetCertificateStatusResponse class
   *
   * @param status Whether the charging station was able to retrieve the OCSP certificate status.
   */
  public GetCertificateStatusResponse(GetCertificateStatusEnum status) {
    setStatus(status);
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
  public GetCertificateStatusResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets whether the charging station was able to retrieve the OCSP certificate status.
   *
   * @return Whether the charging station was able to retrieve the OCSP certificate status
   */
  public GetCertificateStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets whether the charging station was able to retrieve the OCSP certificate status.
   *
   * @param status Whether the charging station was able to retrieve the OCSP certificate status
   */
  public void setStatus(GetCertificateStatusEnum status) {
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
  private boolean isValidStatus(GetCertificateStatusEnum status) {
    return status != null;
  }

  /**
   * Gets more information about the status.
   *
   * @return More information about the status
   */
  @Nullable
  public StatusInfo getStatusInfo() {
    return statusInfo;
  }

  /**
   * Sets more information about the status.
   *
   * @param statusInfo More information about the status
   */
  public void setStatusInfo(@Nullable StatusInfo statusInfo) {
    if (!isValidStatusInfo(statusInfo)) {
      throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
    }
    this.statusInfo = statusInfo;
  }

  /**
   * Returns whether the given statusInfo is valid
   *
   * @param statusInfo the statusInfo to check the validity of
   * @return {@code true} if statusInfo is valid, {@code false} if not
   */
  private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
    return statusInfo == null || statusInfo.validate();
  }

  /**
   * Adds more information about the status.
   *
   * @param statusInfo More information about the status
   * @return this
   */
  public GetCertificateStatusResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
    setStatusInfo(statusInfo);
    return this;
  }

  /**
   * Gets OCSPResponse class as defined in IETF RFC 6960. DER encoded (as defined in IETF RFC 6960),
   * and then base64 encoded. MAY only be omitted when status is not Accepted.
   *
   * @return OCSPResponse class as defined in IETF RFC 6960
   */
  @Nullable
  public String getOcspResult() {
    return ocspResult;
  }

  /**
   * Sets OCSPResponse class as defined in IETF RFC 6960. DER encoded (as defined in IETF RFC 6960),
   * and then base64 encoded. MAY only be omitted when status is not Accepted.
   *
   * @param ocspResult OCSPResponse class as defined in IETF RFC 6960
   */
  public void setOcspResult(@Nullable String ocspResult) {
    if (!isValidOcspResult(ocspResult)) {
      throw new PropertyConstraintException(ocspResult, "ocspResult is invalid");
    }
    this.ocspResult = ocspResult;
  }

  /**
   * Returns whether the given ocspResult is valid
   *
   * @param ocspResult the ocspResult to check the validity of
   * @return {@code true} if ocspResult is valid, {@code false} if not
   */
  private boolean isValidOcspResult(@Nullable String ocspResult) {
    return ocspResult == null || ocspResult.length() <= 5500;
  }

  /**
   * Adds OCSPResponse class as defined in IETF RFC 6960. DER encoded (as defined in IETF RFC 6960),
   * and then base64 encoded. MAY only be omitted when status is not Accepted.
   *
   * @param ocspResult OCSPResponse class as defined in IETF RFC 6960
   * @return this
   */
  public GetCertificateStatusResponse withOcspResult(@Nullable String ocspResult) {
    setOcspResult(ocspResult);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidStatus(status)
        && isValidStatusInfo(statusInfo)
        && isValidOcspResult(ocspResult);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCertificateStatusResponse that = (GetCertificateStatusResponse) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(status, that.status)
        && Objects.equals(statusInfo, that.statusInfo)
        && Objects.equals(ocspResult, that.ocspResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, status, statusInfo, ocspResult);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("status", status)
        .add("statusInfo", statusInfo)
        .add("ocspResult", ocspResult)
        .add("isValid", validate())
        .toString();
  }
}
