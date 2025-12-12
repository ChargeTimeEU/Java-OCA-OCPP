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
import java.util.Objects;
import javax.annotation.Nullable;

/** ClearTariffsResultType */
public final class ClearTariffsResult {
  /** More information about the status. */
  @Nullable private StatusInfo statusInfo;

  /**
   * Id of tariff for which status is reported. If no tariffs were found, then this field is absent,
   * and status will be `NoTariff`.
   */
  @Nullable private String tariffId;

  /** status */
  private TariffClearStatusEnum status;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ClearTariffsResult class
   *
   * @param status status
   */
  public ClearTariffsResult(TariffClearStatusEnum status) {
    setStatus(status);
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
  public ClearTariffsResult withStatusInfo(@Nullable StatusInfo statusInfo) {
    setStatusInfo(statusInfo);
    return this;
  }

  /**
   * Gets id of tariff for which status is reported. If no tariffs were found, then this field is
   * absent, and status will be `NoTariff`.
   *
   * @return Id of tariff for which status is reported
   */
  @Nullable
  public String getTariffId() {
    return tariffId;
  }

  /**
   * Sets id of tariff for which status is reported. If no tariffs were found, then this field is
   * absent, and status will be `NoTariff`.
   *
   * @param tariffId Id of tariff for which status is reported
   */
  public void setTariffId(@Nullable String tariffId) {
    if (!isValidTariffId(tariffId)) {
      throw new PropertyConstraintException(tariffId, "tariffId is invalid");
    }
    this.tariffId = tariffId;
  }

  /**
   * Returns whether the given tariffId is valid
   *
   * @param tariffId the tariffId to check the validity of
   * @return {@code true} if tariffId is valid, {@code false} if not
   */
  private boolean isValidTariffId(@Nullable String tariffId) {
    return tariffId == null || tariffId.length() <= 60;
  }

  /**
   * Adds id of tariff for which status is reported. If no tariffs were found, then this field is
   * absent, and status will be `NoTariff`.
   *
   * @param tariffId Id of tariff for which status is reported
   * @return this
   */
  public ClearTariffsResult withTariffId(@Nullable String tariffId) {
    setTariffId(tariffId);
    return this;
  }

  /**
   * Gets status
   *
   * @return status
   */
  public TariffClearStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets status
   *
   * @param status status
   */
  public void setStatus(TariffClearStatusEnum status) {
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
  private boolean isValidStatus(TariffClearStatusEnum status) {
    return status != null;
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
  public ClearTariffsResult withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidStatusInfo(statusInfo)
        && isValidTariffId(tariffId)
        && isValidStatus(status)
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
    ClearTariffsResult that = (ClearTariffsResult) o;
    return Objects.equals(statusInfo, that.statusInfo)
        && Objects.equals(tariffId, that.tariffId)
        && Objects.equals(status, that.status)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusInfo, tariffId, status, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("statusInfo", statusInfo)
        .add("tariffId", tariffId)
        .add("status", status)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
