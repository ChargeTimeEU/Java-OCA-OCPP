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
import java.util.Objects;
import javax.annotation.Nullable;

/** Transaction */
public final class Transaction {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The Id of the transaction. */
  private String transactionId;

  /**
   * Transaction. State. Transaction State Code
   *
   * <p>Current charging state, is required when state has changed.
   */
  @Nullable private ChargingStateEnum chargingState;

  /**
   * Transaction. Time Spent Charging. Elapsed Time
   *
   * <p>The total time that energy flowed from EVSE to EV during the transaction (in seconds). Note
   * that timeSpentCharging is smaller or equal to the duration of the transaction.
   */
  @Nullable private Integer timeSpentCharging;

  /**
   * Transaction. Stopped Reason. EOT Reason Code
   *
   * <p>The reason why the transaction was stopped. MAY only be omitted when Reason is "Local".
   */
  @Nullable private ReasonEnum stoppedReason;

  /**
   * The ID given to remote start request (RequestStartTransactionRequest. This enables to CSMS to
   * match the started transaction to the given start request.
   */
  @Nullable private Integer remoteStartId;

  /**
   * Constructor for the Transaction class
   *
   * @param transactionId The Id of the transaction.
   */
  public Transaction(String transactionId) {
    setTransactionId(transactionId);
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
  public Transaction withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the Id of the transaction.
   *
   * @return The Id of the transaction
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets the Id of the transaction.
   *
   * @param transactionId The Id of the transaction
   */
  public void setTransactionId(String transactionId) {
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
  private boolean isValidTransactionId(String transactionId) {
    return transactionId != null && transactionId.length() <= 36;
  }

  /**
   * Gets current charging state, is required when state has changed.
   *
   * @return Current charging state, is required when state has changed
   */
  @Nullable
  public ChargingStateEnum getChargingState() {
    return chargingState;
  }

  /**
   * Sets current charging state, is required when state has changed.
   *
   * @param chargingState Current charging state, is required when state has changed
   */
  public void setChargingState(@Nullable ChargingStateEnum chargingState) {
    this.chargingState = chargingState;
  }

  /**
   * Adds current charging state, is required when state has changed.
   *
   * @param chargingState Current charging state, is required when state has changed
   * @return this
   */
  public Transaction withChargingState(@Nullable ChargingStateEnum chargingState) {
    setChargingState(chargingState);
    return this;
  }

  /**
   * Gets the total time that energy flowed from EVSE to EV during the transaction (in seconds).
   * Note that timeSpentCharging is smaller or equal to the duration of the transaction.
   *
   * @return The total time that energy flowed from EVSE to EV during the transaction (in seconds)
   */
  @Nullable
  public Integer getTimeSpentCharging() {
    return timeSpentCharging;
  }

  /**
   * Sets the total time that energy flowed from EVSE to EV during the transaction (in seconds).
   * Note that timeSpentCharging is smaller or equal to the duration of the transaction.
   *
   * @param timeSpentCharging The total time that energy flowed from EVSE to EV during the
   *     transaction (in seconds)
   */
  public void setTimeSpentCharging(@Nullable Integer timeSpentCharging) {
    this.timeSpentCharging = timeSpentCharging;
  }

  /**
   * Adds the total time that energy flowed from EVSE to EV during the transaction (in seconds).
   * Note that timeSpentCharging is smaller or equal to the duration of the transaction.
   *
   * @param timeSpentCharging The total time that energy flowed from EVSE to EV during the
   *     transaction (in seconds)
   * @return this
   */
  public Transaction withTimeSpentCharging(@Nullable Integer timeSpentCharging) {
    setTimeSpentCharging(timeSpentCharging);
    return this;
  }

  /**
   * Gets the reason why the transaction was stopped. MAY only be omitted when Reason is "Local".
   *
   * @return The reason why the transaction was stopped
   */
  @Nullable
  public ReasonEnum getStoppedReason() {
    return stoppedReason;
  }

  /**
   * Sets the reason why the transaction was stopped. MAY only be omitted when Reason is "Local".
   *
   * @param stoppedReason The reason why the transaction was stopped
   */
  public void setStoppedReason(@Nullable ReasonEnum stoppedReason) {
    this.stoppedReason = stoppedReason;
  }

  /**
   * Adds the reason why the transaction was stopped. MAY only be omitted when Reason is "Local".
   *
   * @param stoppedReason The reason why the transaction was stopped
   * @return this
   */
  public Transaction withStoppedReason(@Nullable ReasonEnum stoppedReason) {
    setStoppedReason(stoppedReason);
    return this;
  }

  /**
   * Gets the ID given to remote start request (RequestStartTransactionRequest. This enables to CSMS
   * to match the started transaction to the given start request.
   *
   * @return The ID given to remote start request (RequestStartTransactionRequest
   */
  @Nullable
  public Integer getRemoteStartId() {
    return remoteStartId;
  }

  /**
   * Sets the ID given to remote start request (RequestStartTransactionRequest. This enables to CSMS
   * to match the started transaction to the given start request.
   *
   * @param remoteStartId The ID given to remote start request (RequestStartTransactionRequest
   */
  public void setRemoteStartId(@Nullable Integer remoteStartId) {
    this.remoteStartId = remoteStartId;
  }

  /**
   * Adds the ID given to remote start request (RequestStartTransactionRequest. This enables to CSMS
   * to match the started transaction to the given start request.
   *
   * @param remoteStartId The ID given to remote start request (RequestStartTransactionRequest
   * @return this
   */
  public Transaction withRemoteStartId(@Nullable Integer remoteStartId) {
    setRemoteStartId(remoteStartId);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData) && isValidTransactionId(transactionId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction that = (Transaction) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(transactionId, that.transactionId)
        && Objects.equals(chargingState, that.chargingState)
        && Objects.equals(timeSpentCharging, that.timeSpentCharging)
        && Objects.equals(stoppedReason, that.stoppedReason)
        && Objects.equals(remoteStartId, that.remoteStartId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, transactionId, chargingState, timeSpentCharging, stoppedReason, remoteStartId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("transactionId", transactionId)
        .add("chargingState", chargingState)
        .add("timeSpentCharging", timeSpentCharging)
        .add("stoppedReason", stoppedReason)
        .add("remoteStartId", remoteStartId)
        .add("isValid", validate())
        .toString();
  }
}
