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
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.AuthorizeCertificateStatusEnum;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.EnergyTransferModeEnum;
import eu.chargetime.ocpp.v21.model.types.IdTokenInfo;
import eu.chargetime.ocpp.v21.model.types.Tariff;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * AuthorizeResponse
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class AuthorizeResponse extends Confirmation {
  /**
   * Status information about an identifier. It is advised to not stop charging for a token that
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
   * List of allowed energy transfer modes the EV can choose from. If omitted this defaults to
   * charging only.
   */
  @Nullable private EnergyTransferModeEnum[] allowedEnergyTransfer;

  /**
   * A tariff is described by fields with prices for: energy, charging time, idle time, fixed fee,
   * reservation time,
   *
   * <pre>
   * reservation fixed fee.
   * Each of these fields may have (optional) conditions that specify when a price is applicable.
   * The description contains a human-readable explanation of the tariff to be shown to the user.
   * The other fields are parameters that define the tariff. These are used by the charging station
   * to calculate the price.
   * </pre>
   */
  @Nullable private Tariff tariff;

  /** Custom data */
  @Nullable private CustomData customData;

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

  /**
   * Gets list of allowed energy transfer modes the EV can choose from. If omitted this defaults to
   * charging only.
   *
   * @return List of allowed energy transfer modes the EV can choose from
   */
  @Nullable
  public EnergyTransferModeEnum[] getAllowedEnergyTransfer() {
    return allowedEnergyTransfer;
  }

  /**
   * Sets list of allowed energy transfer modes the EV can choose from. If omitted this defaults to
   * charging only.
   *
   * @param allowedEnergyTransfer List of allowed energy transfer modes the EV can choose from
   */
  public void setAllowedEnergyTransfer(@Nullable EnergyTransferModeEnum[] allowedEnergyTransfer) {
    if (!isValidAllowedEnergyTransfer(allowedEnergyTransfer)) {
      throw new PropertyConstraintException(
          allowedEnergyTransfer, "allowedEnergyTransfer is invalid");
    }
    this.allowedEnergyTransfer = allowedEnergyTransfer;
  }

  /**
   * Returns whether the given allowedEnergyTransfer is valid
   *
   * @param allowedEnergyTransfer the allowedEnergyTransfer to check the validity of
   * @return {@code true} if allowedEnergyTransfer is valid, {@code false} if not
   */
  private boolean isValidAllowedEnergyTransfer(
      @Nullable EnergyTransferModeEnum[] allowedEnergyTransfer) {
    return allowedEnergyTransfer == null || (allowedEnergyTransfer.length >= 1);
  }

  /**
   * Adds list of allowed energy transfer modes the EV can choose from. If omitted this defaults to
   * charging only.
   *
   * @param allowedEnergyTransfer List of allowed energy transfer modes the EV can choose from
   * @return this
   */
  public AuthorizeResponse withAllowedEnergyTransfer(
      @Nullable EnergyTransferModeEnum[] allowedEnergyTransfer) {
    setAllowedEnergyTransfer(allowedEnergyTransfer);
    return this;
  }

  /**
   * Gets a tariff is described by fields with prices for: energy, charging time, idle time, fixed
   * fee, reservation time,
   *
   * @return A tariff is described by fields with prices for: energy, charging time, idle time,
   *     fixed fee, reservation time,
   */
  @Nullable
  public Tariff getTariff() {
    return tariff;
  }

  /**
   * Sets a tariff is described by fields with prices for: energy, charging time, idle time, fixed
   * fee, reservation time,
   *
   * @param tariff A tariff is described by fields with prices for: energy, charging time, idle
   *     time, fixed fee, reservation time,
   */
  public void setTariff(@Nullable Tariff tariff) {
    if (!isValidTariff(tariff)) {
      throw new PropertyConstraintException(tariff, "tariff is invalid");
    }
    this.tariff = tariff;
  }

  /**
   * Returns whether the given tariff is valid
   *
   * @param tariff the tariff to check the validity of
   * @return {@code true} if tariff is valid, {@code false} if not
   */
  private boolean isValidTariff(@Nullable Tariff tariff) {
    return tariff == null || tariff.validate();
  }

  /**
   * Adds a tariff is described by fields with prices for: energy, charging time, idle time, fixed
   * fee, reservation time,
   *
   * @param tariff A tariff is described by fields with prices for: energy, charging time, idle
   *     time, fixed fee, reservation time,
   * @return this
   */
  public AuthorizeResponse withTariff(@Nullable Tariff tariff) {
    setTariff(tariff);
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
  public AuthorizeResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidIdTokenInfo(idTokenInfo)
        && isValidAllowedEnergyTransfer(allowedEnergyTransfer)
        && isValidTariff(tariff)
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
    AuthorizeResponse that = (AuthorizeResponse) o;
    return Objects.equals(idTokenInfo, that.idTokenInfo)
        && Objects.equals(certificateStatus, that.certificateStatus)
        && Arrays.equals(allowedEnergyTransfer, that.allowedEnergyTransfer)
        && Objects.equals(tariff, that.tariff)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        idTokenInfo, certificateStatus, Arrays.hashCode(allowedEnergyTransfer), tariff, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("idTokenInfo", idTokenInfo)
        .add("certificateStatus", certificateStatus)
        .add("allowedEnergyTransfer", allowedEnergyTransfer)
        .add("tariff", tariff)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
