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
import eu.chargetime.ocpp.v201.model.types.Iso15118EVCertificateStatusEnum;
import eu.chargetime.ocpp.v201.model.types.StatusInfo;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Get15118EVCertificateResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class Get15118EVCertificateResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Whether the message was processed properly. */
  private Iso15118EVCertificateStatusEnum status;

  /** More information about the status. */
  @Nullable private StatusInfo statusInfo;

  /** Raw CertificateInstallationRes response for the EV, Base64 encoded. */
  private String exiResponse;

  /**
   * Constructor for the Get15118EVCertificateResponse class
   *
   * @param status Whether the message was processed properly.
   * @param exiResponse Raw CertificateInstallationRes response for the EV, Base64 encoded.
   */
  public Get15118EVCertificateResponse(Iso15118EVCertificateStatusEnum status, String exiResponse) {
    setStatus(status);
    setExiResponse(exiResponse);
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
  public Get15118EVCertificateResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets whether the message was processed properly.
   *
   * @return Whether the message was processed properly
   */
  public Iso15118EVCertificateStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets whether the message was processed properly.
   *
   * @param status Whether the message was processed properly
   */
  public void setStatus(Iso15118EVCertificateStatusEnum status) {
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
  private boolean isValidStatus(Iso15118EVCertificateStatusEnum status) {
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
  public Get15118EVCertificateResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
    setStatusInfo(statusInfo);
    return this;
  }

  /**
   * Gets raw CertificateInstallationRes response for the EV, Base64 encoded.
   *
   * @return Raw CertificateInstallationRes response for the EV, Base64 encoded
   */
  public String getExiResponse() {
    return exiResponse;
  }

  /**
   * Sets raw CertificateInstallationRes response for the EV, Base64 encoded.
   *
   * @param exiResponse Raw CertificateInstallationRes response for the EV, Base64 encoded
   */
  public void setExiResponse(String exiResponse) {
    if (!isValidExiResponse(exiResponse)) {
      throw new PropertyConstraintException(exiResponse, "exiResponse is invalid");
    }
    this.exiResponse = exiResponse;
  }

  /**
   * Returns whether the given exiResponse is valid
   *
   * @param exiResponse the exiResponse to check the validity of
   * @return {@code true} if exiResponse is valid, {@code false} if not
   */
  private boolean isValidExiResponse(String exiResponse) {
    return exiResponse != null && exiResponse.length() <= 5600;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidStatus(status)
        && isValidStatusInfo(statusInfo)
        && isValidExiResponse(exiResponse);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Get15118EVCertificateResponse that = (Get15118EVCertificateResponse) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(status, that.status)
        && Objects.equals(statusInfo, that.statusInfo)
        && Objects.equals(exiResponse, that.exiResponse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, status, statusInfo, exiResponse);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("status", status)
        .add("statusInfo", statusInfo)
        .add("exiResponse", exiResponse)
        .add("isValid", validate())
        .toString();
  }
}
