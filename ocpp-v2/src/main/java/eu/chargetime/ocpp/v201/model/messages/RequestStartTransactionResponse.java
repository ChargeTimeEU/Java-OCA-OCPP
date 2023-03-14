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
import eu.chargetime.ocpp.v201.model.types.RequestStartStopStatusEnum;
import eu.chargetime.ocpp.v201.model.types.StatusInfo;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * RequestStartTransactionResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class RequestStartTransactionResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Status indicating whether the Charging Station accepts the request to start a transaction. */
  private RequestStartStopStatusEnum status;

  /** More information about the status. */
  @Nullable private StatusInfo statusInfo;

  /**
   * When the transaction was already started by the Charging Station before the
   * RequestStartTransactionRequest was received, for example: cable plugged in first. This contains
   * the transactionId of the already started transaction.
   */
  @Nullable private String transactionId;

  /**
   * Constructor for the RequestStartTransactionResponse class
   *
   * @param status Status indicating whether the Charging Station accepts the request to start a
   *     transaction.
   */
  public RequestStartTransactionResponse(RequestStartStopStatusEnum status) {
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
  public RequestStartTransactionResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets status indicating whether the Charging Station accepts the request to start a transaction.
   *
   * @return Status indicating whether the Charging Station accepts the request to start a
   *     transaction
   */
  public RequestStartStopStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets status indicating whether the Charging Station accepts the request to start a transaction.
   *
   * @param status Status indicating whether the Charging Station accepts the request to start a
   *     transaction
   */
  public void setStatus(RequestStartStopStatusEnum status) {
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
  private boolean isValidStatus(RequestStartStopStatusEnum status) {
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
  public RequestStartTransactionResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
    setStatusInfo(statusInfo);
    return this;
  }

  /**
   * Gets when the transaction was already started by the Charging Station before the
   * RequestStartTransactionRequest was received, for example: cable plugged in first. This contains
   * the transactionId of the already started transaction.
   *
   * @return When the transaction was already started by the Charging Station before the
   *     RequestStartTransactionRequest was received, for example: cable plugged in first
   */
  @Nullable
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets when the transaction was already started by the Charging Station before the
   * RequestStartTransactionRequest was received, for example: cable plugged in first. This contains
   * the transactionId of the already started transaction.
   *
   * @param transactionId When the transaction was already started by the Charging Station before
   *     the RequestStartTransactionRequest was received, for example: cable plugged in first
   */
  public void setTransactionId(@Nullable String transactionId) {
    if (!isValidTransactionId(transactionId)) {
      throw new PropertyConstraintException(transactionId, "transactionId is invalid");
    }
    this.transactionId = transactionId;
  }

  /**
   * Returns whether the given transactionId is valid
   *
   * @param transactionId the transactionId to check the validity of
   * @return {@code true} if transactionId is valid, {@code false} if not
   */
  private boolean isValidTransactionId(@Nullable String transactionId) {
    return transactionId == null || transactionId.length() <= 36;
  }

  /**
   * Adds when the transaction was already started by the Charging Station before the
   * RequestStartTransactionRequest was received, for example: cable plugged in first. This contains
   * the transactionId of the already started transaction.
   *
   * @param transactionId When the transaction was already started by the Charging Station before
   *     the RequestStartTransactionRequest was received, for example: cable plugged in first
   * @return this
   */
  public RequestStartTransactionResponse withTransactionId(@Nullable String transactionId) {
    setTransactionId(transactionId);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidStatus(status)
        && isValidStatusInfo(statusInfo)
        && isValidTransactionId(transactionId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestStartTransactionResponse that = (RequestStartTransactionResponse) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(status, that.status)
        && Objects.equals(statusInfo, that.statusInfo)
        && Objects.equals(transactionId, that.transactionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, status, statusInfo, transactionId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("status", status)
        .add("statusInfo", statusInfo)
        .add("transactionId", transactionId)
        .add("isValid", validate())
        .toString();
  }
}
