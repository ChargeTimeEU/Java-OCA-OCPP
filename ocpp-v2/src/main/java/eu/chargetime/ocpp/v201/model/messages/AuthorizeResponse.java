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
import eu.chargetime.ocpp.v201.model.types.AuthorizeCertificateStatusEnum;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.IdTokenInfo;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * AuthorizeResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class AuthorizeResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * ID Token
   *
   * <p>Status information about an identifier. It is advised to not stop charging for a token that
   * expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is not
   * given, the status has no end date.
   */
  private IdTokenInfo idTokenInfo;

  /**
   * Certificate status information.
   *
   * <pre>
   * - if all certificates are valid: return 'Accepted'.
   * - if one of the certificates was revoked, return 'CertificateRevoked'.
   * </pre>
   */
  @Nullable private AuthorizeCertificateStatusEnum certificateStatus;

  /**
   * Constructor for the AuthorizeResponse class
   *
   * @param idTokenInfo Status information about an identifier. It is advised to not stop charging
   *     for a token that expires during charging, as ExpiryDate is only used for caching purposes.
   *     If ExpiryDate is not given, the status has no end date.
   */
  public AuthorizeResponse(IdTokenInfo idTokenInfo) {
    setIdTokenInfo(idTokenInfo);
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
  public AuthorizeResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets status information about an identifier. It is advised to not stop charging for a token
   * that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is
   * not given, the status has no end date.
   *
   * @return Status information about an identifier
   */
  public IdTokenInfo getIdTokenInfo() {
    return idTokenInfo;
  }

  /**
   * Sets status information about an identifier. It is advised to not stop charging for a token
   * that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is
   * not given, the status has no end date.
   *
   * @param idTokenInfo Status information about an identifier
   */
  public void setIdTokenInfo(IdTokenInfo idTokenInfo) {
    if (!isValidIdTokenInfo(idTokenInfo)) {
      throw new PropertyConstraintException(idTokenInfo, "idTokenInfo is invalid");
    }
    this.idTokenInfo = idTokenInfo;
  }

  /**
   * Returns whether the given idTokenInfo is valid
   *
   * @param idTokenInfo the idTokenInfo to check the validity of
   * @return {@code true} if idTokenInfo is valid, {@code false} if not
   */
  private boolean isValidIdTokenInfo(IdTokenInfo idTokenInfo) {
    return idTokenInfo != null && idTokenInfo.validate();
  }

  /**
   * Gets certificate status information.
   *
   * @return Certificate status information
   */
  @Nullable
  public AuthorizeCertificateStatusEnum getCertificateStatus() {
    return certificateStatus;
  }

  /**
   * Sets certificate status information.
   *
   * @param certificateStatus Certificate status information
   */
  public void setCertificateStatus(@Nullable AuthorizeCertificateStatusEnum certificateStatus) {
    this.certificateStatus = certificateStatus;
  }

  /**
   * Adds certificate status information.
   *
   * @param certificateStatus Certificate status information
   * @return this
   */
  public AuthorizeResponse withCertificateStatus(
      @Nullable AuthorizeCertificateStatusEnum certificateStatus) {
    setCertificateStatus(certificateStatus);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidIdTokenInfo(idTokenInfo);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizeResponse that = (AuthorizeResponse) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(idTokenInfo, that.idTokenInfo)
        && Objects.equals(certificateStatus, that.certificateStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, idTokenInfo, certificateStatus);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("idTokenInfo", idTokenInfo)
        .add("certificateStatus", certificateStatus)
        .add("isValid", validate())
        .toString();
  }
}
