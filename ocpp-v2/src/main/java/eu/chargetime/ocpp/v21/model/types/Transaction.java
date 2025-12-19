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

/** TransactionType */
public final class Transaction {
  /** The Id of the transaction. */
  private String transactionId;

  /**
   * Current charging state, is required when state has changed. Omitted when there is no
   * communication between EVSE and EV, because no cable is plugged in.
   */
  @Nullable private ChargingStateEnum chargingState;

  /**
   * The total time that energy flowed from EVSE to EV during the transaction (in seconds). Note
   * that timeSpentCharging is smaller or equal to the duration of the transaction.
   */
  @Nullable private Integer timeSpentCharging;

  /**
   * The stoppedReason is the reason/event that initiated the process of stopping the transaction.
   * It will normally be the user stopping authorization via card (Local or MasterPass) or app
   * (Remote), but it can also be CSMS revoking authorization (DeAuthorized), or disconnecting the
   * EV when TxStopPoint = EVConnected (EVDisconnected). Most other reasons are related to technical
   * faults or energy limitations.
   *
   * <p>MAY only be omitted when stoppedReason is "Local"
   */
  @Nullable private ReasonEnum stoppedReason;

  /**
   * The ID given to remote start request (RequestStartTransactionRequest. This enables to CSMS to
   * match the started transaction to the given start request.
   */
  @Nullable private Integer remoteStartId;

  /** The operationMode that is currently in effect for the transaction. */
  @Nullable private OperationModeEnum operationMode;

  /** Id of tariff in use for transaction */
  @Nullable private String tariffId;

  /** Cost, energy, time or SoC limit for a transaction. */
  @Nullable private TransactionLimit transactionLimit;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the Transaction class
   *
   * @param transactionId The Id of the transaction.
   */
  public Transaction(String transactionId) {
    setTransactionId(transactionId);
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
   * Gets current charging state, is required when state has changed. Omitted when there is no
   * communication between EVSE and EV, because no cable is plugged in.
   *
   * @return Current charging state, is required when state has changed
   */
  @Nullable
  public ChargingStateEnum getChargingState() {
    return chargingState;
  }

  /**
   * Sets current charging state, is required when state has changed. Omitted when there is no
   * communication between EVSE and EV, because no cable is plugged in.
   *
   * @param chargingState Current charging state, is required when state has changed
   */
  public void setChargingState(@Nullable ChargingStateEnum chargingState) {
    this.chargingState = chargingState;
  }

  /**
   * Adds current charging state, is required when state has changed. Omitted when there is no
   * communication between EVSE and EV, because no cable is plugged in.
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
   * Gets the stoppedReason is the reason/event that initiated the process of stopping the
   * transaction. It will normally be the user stopping authorization via card (Local or MasterPass)
   * or app (Remote), but it can also be CSMS revoking authorization (DeAuthorized), or
   * disconnecting the EV when TxStopPoint = EVConnected (EVDisconnected). Most other reasons are
   * related to technical faults or energy limitations.
   *
   * @return The stoppedReason is the reason/event that initiated the process of stopping the
   *     transaction
   */
  @Nullable
  public ReasonEnum getStoppedReason() {
    return stoppedReason;
  }

  /**
   * Sets the stoppedReason is the reason/event that initiated the process of stopping the
   * transaction. It will normally be the user stopping authorization via card (Local or MasterPass)
   * or app (Remote), but it can also be CSMS revoking authorization (DeAuthorized), or
   * disconnecting the EV when TxStopPoint = EVConnected (EVDisconnected). Most other reasons are
   * related to technical faults or energy limitations.
   *
   * @param stoppedReason The stoppedReason is the reason/event that initiated the process of
   *     stopping the transaction
   */
  public void setStoppedReason(@Nullable ReasonEnum stoppedReason) {
    this.stoppedReason = stoppedReason;
  }

  /**
   * Adds the stoppedReason is the reason/event that initiated the process of stopping the
   * transaction. It will normally be the user stopping authorization via card (Local or MasterPass)
   * or app (Remote), but it can also be CSMS revoking authorization (DeAuthorized), or
   * disconnecting the EV when TxStopPoint = EVConnected (EVDisconnected). Most other reasons are
   * related to technical faults or energy limitations.
   *
   * @param stoppedReason The stoppedReason is the reason/event that initiated the process of
   *     stopping the transaction
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

  /**
   * Gets the operationMode that is currently in effect for the transaction.
   *
   * @return The operationMode that is currently in effect for the transaction
   */
  @Nullable
  public OperationModeEnum getOperationMode() {
    return operationMode;
  }

  /**
   * Sets the operationMode that is currently in effect for the transaction.
   *
   * @param operationMode The operationMode that is currently in effect for the transaction
   */
  public void setOperationMode(@Nullable OperationModeEnum operationMode) {
    this.operationMode = operationMode;
  }

  /**
   * Adds the operationMode that is currently in effect for the transaction.
   *
   * @param operationMode The operationMode that is currently in effect for the transaction
   * @return this
   */
  public Transaction withOperationMode(@Nullable OperationModeEnum operationMode) {
    setOperationMode(operationMode);
    return this;
  }

  /**
   * Gets id of tariff in use for transaction
   *
   * @return Id of tariff in use for transaction
   */
  @Nullable
  public String getTariffId() {
    return tariffId;
  }

  /**
   * Sets id of tariff in use for transaction
   *
   * @param tariffId Id of tariff in use for transaction
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
   * Adds id of tariff in use for transaction
   *
   * @param tariffId Id of tariff in use for transaction
   * @return this
   */
  public Transaction withTariffId(@Nullable String tariffId) {
    setTariffId(tariffId);
    return this;
  }

  /**
   * Gets cost, energy, time or SoC limit for a transaction.
   *
   * @return Cost, energy, time or SoC limit for a transaction
   */
  @Nullable
  public TransactionLimit getTransactionLimit() {
    return transactionLimit;
  }

  /**
   * Sets cost, energy, time or SoC limit for a transaction.
   *
   * @param transactionLimit Cost, energy, time or SoC limit for a transaction
   */
  public void setTransactionLimit(@Nullable TransactionLimit transactionLimit) {
    if (!isValidTransactionLimit(transactionLimit)) {
      throw new PropertyConstraintException(transactionLimit, "transactionLimit is invalid");
    }
    this.transactionLimit = transactionLimit;
  }

  /**
   * Returns whether the given transactionLimit is valid
   *
   * @param transactionLimit the transactionLimit to check the validity of
   * @return {@code true} if transactionLimit is valid, {@code false} if not
   */
  private boolean isValidTransactionLimit(@Nullable TransactionLimit transactionLimit) {
    return transactionLimit == null || transactionLimit.validate();
  }

  /**
   * Adds cost, energy, time or SoC limit for a transaction.
   *
   * @param transactionLimit Cost, energy, time or SoC limit for a transaction
   * @return this
   */
  public Transaction withTransactionLimit(@Nullable TransactionLimit transactionLimit) {
    setTransactionLimit(transactionLimit);
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
  public Transaction withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidTransactionId(transactionId)
        && isValidTariffId(tariffId)
        && isValidTransactionLimit(transactionLimit)
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
    Transaction that = (Transaction) o;
    return Objects.equals(transactionId, that.transactionId)
        && Objects.equals(chargingState, that.chargingState)
        && Objects.equals(timeSpentCharging, that.timeSpentCharging)
        && Objects.equals(stoppedReason, that.stoppedReason)
        && Objects.equals(remoteStartId, that.remoteStartId)
        && Objects.equals(operationMode, that.operationMode)
        && Objects.equals(tariffId, that.tariffId)
        && Objects.equals(transactionLimit, that.transactionLimit)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        transactionId,
        chargingState,
        timeSpentCharging,
        stoppedReason,
        remoteStartId,
        operationMode,
        tariffId,
        transactionLimit,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("transactionId", transactionId)
        .add("chargingState", chargingState)
        .add("timeSpentCharging", timeSpentCharging)
        .add("stoppedReason", stoppedReason)
        .add("remoteStartId", remoteStartId)
        .add("operationMode", operationMode)
        .add("tariffId", tariffId)
        .add("transactionLimit", transactionLimit)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
