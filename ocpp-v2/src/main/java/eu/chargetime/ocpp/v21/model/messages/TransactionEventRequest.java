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
import eu.chargetime.ocpp.v21.model.types.CostDetails;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.EVSE;
import eu.chargetime.ocpp.v21.model.types.IdToken;
import eu.chargetime.ocpp.v21.model.types.MeterValue;
import eu.chargetime.ocpp.v21.model.types.PreconditioningStatusEnum;
import eu.chargetime.ocpp.v21.model.types.Transaction;
import eu.chargetime.ocpp.v21.model.types.TransactionEventEnum;
import eu.chargetime.ocpp.v21.model.types.TriggerReasonEnum;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * TransactionEventRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class TransactionEventRequest extends RequestWithId {
  /**
   * CostDetailsType contains the cost as calculated by Charging Station based on provided
   * TariffType.
   *
   * <p>NOTE: Reservation is not shown as a chargingPeriod, because it took place outside of the
   * transaction.
   */
  @Nullable private CostDetails costDetails;

  /**
   * The type of this event. The first TransactionEvent of a transaction SHALL contain: "Started"
   * The last TransactionEvent of a transaction SHALL contain: "Ended" All others SHALL contain:
   * "Updated"
   */
  private TransactionEventEnum eventType;

  /**
   * Collection of one or more sampled values in MeterValuesRequest and TransactionEvent. All
   * sampled values in a MeterValue are sampled at the same point in time.
   */
  @Nullable private MeterValue[] meterValue;

  /** The date and time at which this transaction event occurred. */
  private ZonedDateTime timestamp;

  /** Reason the Charging Station sends this message to the CSMS */
  private TriggerReasonEnum triggerReason;

  /**
   * Incremental sequence number, helps with determining if all messages of a transaction have been
   * received.
   */
  private Integer seqNo;

  /**
   * Indication that this transaction event happened when the Charging Station was offline. Default
   * = false, meaning: the event occurred when the Charging Station was online.
   */
  @Nullable private Boolean offline;

  /**
   * If the Charging Station is able to report the number of phases used, then it SHALL provide it.
   *
   * <pre>
   * When omitted the CSMS may be able to determine the number of phases used as follows:
   * 1: The numberPhases in the currently used ChargingSchedule.
   * 2: The number of phases provided via device management.
   * </pre>
   */
  @Nullable private Integer numberOfPhasesUsed;

  /** The maximum current of the connected cable in Ampere (A). */
  @Nullable private Integer cableMaxCurrent;

  /** The Id of the reservation that terminates as a result of this transaction. */
  @Nullable private Integer reservationId;

  /** The current preconditioning status of the BMS in the EV. Default value is Unknown. */
  @Nullable private PreconditioningStatusEnum preconditioningStatus;

  /**
   * True when EVSE electronics are in sleep mode for this transaction. Default value (when absent)
   * is false.
   */
  @Nullable private Boolean evseSleep;

  /** transactionInfo */
  private Transaction transactionInfo;

  /** Electric Vehicle Supply Equipment */
  @Nullable private EVSE evse;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  @Nullable private IdToken idToken;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TransactionEventRequest class
   *
   * @param eventType The type of this event. The first TransactionEvent of a transaction SHALL
   *     contain: "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All
   *     others SHALL contain: "Updated"
   * @param timestamp The date and time at which this transaction event occurred.
   * @param triggerReason Reason the Charging Station sends this message to the CSMS
   * @param seqNo Incremental sequence number, helps with determining if all messages of a
   *     transaction have been received.
   * @param transactionInfo transactionInfo
   */
  public TransactionEventRequest(
      TransactionEventEnum eventType,
      ZonedDateTime timestamp,
      TriggerReasonEnum triggerReason,
      Integer seqNo,
      Transaction transactionInfo) {
    setEventType(eventType);
    setTimestamp(timestamp);
    setTriggerReason(triggerReason);
    setSeqNo(seqNo);
    setTransactionInfo(transactionInfo);
  }

  /**
   * Gets costDetailsType contains the cost as calculated by Charging Station based on provided
   * TariffType.
   *
   * @return CostDetailsType contains the cost as calculated by Charging Station based on provided
   *     TariffType
   */
  @Nullable
  public CostDetails getCostDetails() {
    return costDetails;
  }

  /**
   * Sets costDetailsType contains the cost as calculated by Charging Station based on provided
   * TariffType.
   *
   * @param costDetails CostDetailsType contains the cost as calculated by Charging Station based on
   *     provided TariffType
   */
  public void setCostDetails(@Nullable CostDetails costDetails) {
    if (!isValidCostDetails(costDetails)) {
      throw new PropertyConstraintException(costDetails, "costDetails is invalid");
    }
    this.costDetails = costDetails;
  }

  /**
   * Returns whether the given costDetails is valid
   *
   * @param costDetails the costDetails to check the validity of
   * @return {@code true} if costDetails is valid, {@code false} if not
   */
  private boolean isValidCostDetails(@Nullable CostDetails costDetails) {
    return costDetails == null || costDetails.validate();
  }

  /**
   * Adds costDetailsType contains the cost as calculated by Charging Station based on provided
   * TariffType.
   *
   * @param costDetails CostDetailsType contains the cost as calculated by Charging Station based on
   *     provided TariffType
   * @return this
   */
  public TransactionEventRequest withCostDetails(@Nullable CostDetails costDetails) {
    setCostDetails(costDetails);
    return this;
  }

  /**
   * Gets the type of this event. The first TransactionEvent of a transaction SHALL contain:
   * "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All others SHALL
   * contain: "Updated"
   *
   * @return The type of this event
   */
  public TransactionEventEnum getEventType() {
    return eventType;
  }

  /**
   * Sets the type of this event. The first TransactionEvent of a transaction SHALL contain:
   * "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All others SHALL
   * contain: "Updated"
   *
   * @param eventType The type of this event
   */
  public void setEventType(TransactionEventEnum eventType) {
    if (!isValidEventType(eventType)) {
      throw new PropertyConstraintException(eventType, "eventType is invalid");
    }
    this.eventType = eventType;
  }

  /**
   * Returns whether the given eventType is valid
   *
   * @param eventType the eventType to check the validity of
   * @return {@code true} if eventType is valid, {@code false} if not
   */
  private boolean isValidEventType(TransactionEventEnum eventType) {
    return eventType != null;
  }

  /**
   * Gets collection of one or more sampled values in MeterValuesRequest and TransactionEvent. All
   * sampled values in a MeterValue are sampled at the same point in time.
   *
   * @return Collection of one or more sampled values in MeterValuesRequest and TransactionEvent
   */
  @Nullable
  public MeterValue[] getMeterValue() {
    return meterValue;
  }

  /**
   * Sets collection of one or more sampled values in MeterValuesRequest and TransactionEvent. All
   * sampled values in a MeterValue are sampled at the same point in time.
   *
   * @param meterValue Collection of one or more sampled values in MeterValuesRequest and
   *     TransactionEvent
   */
  public void setMeterValue(@Nullable MeterValue[] meterValue) {
    if (!isValidMeterValue(meterValue)) {
      throw new PropertyConstraintException(meterValue, "meterValue is invalid");
    }
    this.meterValue = meterValue;
  }

  /**
   * Returns whether the given meterValue is valid
   *
   * @param meterValue the meterValue to check the validity of
   * @return {@code true} if meterValue is valid, {@code false} if not
   */
  private boolean isValidMeterValue(@Nullable MeterValue[] meterValue) {
    return meterValue == null
        || (meterValue.length >= 1 && Arrays.stream(meterValue).allMatch(item -> item.validate()));
  }

  /**
   * Adds collection of one or more sampled values in MeterValuesRequest and TransactionEvent. All
   * sampled values in a MeterValue are sampled at the same point in time.
   *
   * @param meterValue Collection of one or more sampled values in MeterValuesRequest and
   *     TransactionEvent
   * @return this
   */
  public TransactionEventRequest withMeterValue(@Nullable MeterValue[] meterValue) {
    setMeterValue(meterValue);
    return this;
  }

  /**
   * Gets the date and time at which this transaction event occurred.
   *
   * @return The date and time at which this transaction event occurred
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the date and time at which this transaction event occurred.
   *
   * @param timestamp The date and time at which this transaction event occurred
   */
  public void setTimestamp(ZonedDateTime timestamp) {
    if (!isValidTimestamp(timestamp)) {
      throw new PropertyConstraintException(timestamp, "timestamp is invalid");
    }
    this.timestamp = timestamp;
  }

  /**
   * Returns whether the given timestamp is valid
   *
   * @param timestamp the timestamp to check the validity of
   * @return {@code true} if timestamp is valid, {@code false} if not
   */
  private boolean isValidTimestamp(ZonedDateTime timestamp) {
    return timestamp != null;
  }

  /**
   * Gets reason the Charging Station sends this message to the CSMS
   *
   * @return Reason the Charging Station sends this message to the CSMS
   */
  public TriggerReasonEnum getTriggerReason() {
    return triggerReason;
  }

  /**
   * Sets reason the Charging Station sends this message to the CSMS
   *
   * @param triggerReason Reason the Charging Station sends this message to the CSMS
   */
  public void setTriggerReason(TriggerReasonEnum triggerReason) {
    if (!isValidTriggerReason(triggerReason)) {
      throw new PropertyConstraintException(triggerReason, "triggerReason is invalid");
    }
    this.triggerReason = triggerReason;
  }

  /**
   * Returns whether the given triggerReason is valid
   *
   * @param triggerReason the triggerReason to check the validity of
   * @return {@code true} if triggerReason is valid, {@code false} if not
   */
  private boolean isValidTriggerReason(TriggerReasonEnum triggerReason) {
    return triggerReason != null;
  }

  /**
   * Gets incremental sequence number, helps with determining if all messages of a transaction have
   * been received.
   *
   * @return Incremental sequence number, helps with determining if all messages of a transaction
   *     have been received
   */
  public Integer getSeqNo() {
    return seqNo;
  }

  /**
   * Sets incremental sequence number, helps with determining if all messages of a transaction have
   * been received.
   *
   * @param seqNo Incremental sequence number, helps with determining if all messages of a
   *     transaction have been received
   */
  public void setSeqNo(Integer seqNo) {
    if (!isValidSeqNo(seqNo)) {
      throw new PropertyConstraintException(seqNo, "seqNo is invalid");
    }
    this.seqNo = seqNo;
  }

  /**
   * Returns whether the given seqNo is valid
   *
   * @param seqNo the seqNo to check the validity of
   * @return {@code true} if seqNo is valid, {@code false} if not
   */
  private boolean isValidSeqNo(Integer seqNo) {
    return seqNo != null && seqNo >= 0;
  }

  /**
   * Gets indication that this transaction event happened when the Charging Station was offline.
   * Default = false, meaning: the event occurred when the Charging Station was online.
   *
   * @return Indication that this transaction event happened when the Charging Station was offline
   */
  public Boolean getOffline() {
    return offline != null ? offline : false;
  }

  /**
   * Sets indication that this transaction event happened when the Charging Station was offline.
   * Default = false, meaning: the event occurred when the Charging Station was online.
   *
   * @param offline Indication that this transaction event happened when the Charging Station was
   *     offline
   */
  public void setOffline(@Nullable Boolean offline) {
    this.offline = offline;
  }

  /**
   * Adds indication that this transaction event happened when the Charging Station was offline.
   * Default = false, meaning: the event occurred when the Charging Station was online.
   *
   * @param offline Indication that this transaction event happened when the Charging Station was
   *     offline
   * @return this
   */
  public TransactionEventRequest withOffline(@Nullable Boolean offline) {
    setOffline(offline);
    return this;
  }

  /**
   * Gets if the Charging Station is able to report the number of phases used, then it SHALL provide
   * it.
   *
   * @return If the Charging Station is able to report the number of phases used, then it SHALL
   *     provide it
   */
  @Nullable
  public Integer getNumberOfPhasesUsed() {
    return numberOfPhasesUsed;
  }

  /**
   * Sets if the Charging Station is able to report the number of phases used, then it SHALL provide
   * it.
   *
   * @param numberOfPhasesUsed If the Charging Station is able to report the number of phases used,
   *     then it SHALL provide it
   */
  public void setNumberOfPhasesUsed(@Nullable Integer numberOfPhasesUsed) {
    if (!isValidNumberOfPhasesUsed(numberOfPhasesUsed)) {
      throw new PropertyConstraintException(numberOfPhasesUsed, "numberOfPhasesUsed is invalid");
    }
    this.numberOfPhasesUsed = numberOfPhasesUsed;
  }

  /**
   * Returns whether the given numberOfPhasesUsed is valid
   *
   * @param numberOfPhasesUsed the numberOfPhasesUsed to check the validity of
   * @return {@code true} if numberOfPhasesUsed is valid, {@code false} if not
   */
  private boolean isValidNumberOfPhasesUsed(@Nullable Integer numberOfPhasesUsed) {
    return numberOfPhasesUsed == null || (numberOfPhasesUsed >= 0 && numberOfPhasesUsed <= 3);
  }

  /**
   * Adds if the Charging Station is able to report the number of phases used, then it SHALL provide
   * it.
   *
   * @param numberOfPhasesUsed If the Charging Station is able to report the number of phases used,
   *     then it SHALL provide it
   * @return this
   */
  public TransactionEventRequest withNumberOfPhasesUsed(@Nullable Integer numberOfPhasesUsed) {
    setNumberOfPhasesUsed(numberOfPhasesUsed);
    return this;
  }

  /**
   * Gets the maximum current of the connected cable in Ampere (A).
   *
   * @return The maximum current of the connected cable in Ampere (A)
   */
  @Nullable
  public Integer getCableMaxCurrent() {
    return cableMaxCurrent;
  }

  /**
   * Sets the maximum current of the connected cable in Ampere (A).
   *
   * @param cableMaxCurrent The maximum current of the connected cable in Ampere (A)
   */
  public void setCableMaxCurrent(@Nullable Integer cableMaxCurrent) {
    this.cableMaxCurrent = cableMaxCurrent;
  }

  /**
   * Adds the maximum current of the connected cable in Ampere (A).
   *
   * @param cableMaxCurrent The maximum current of the connected cable in Ampere (A)
   * @return this
   */
  public TransactionEventRequest withCableMaxCurrent(@Nullable Integer cableMaxCurrent) {
    setCableMaxCurrent(cableMaxCurrent);
    return this;
  }

  /**
   * Gets the Id of the reservation that terminates as a result of this transaction.
   *
   * @return The Id of the reservation that terminates as a result of this transaction
   */
  @Nullable
  public Integer getReservationId() {
    return reservationId;
  }

  /**
   * Sets the Id of the reservation that terminates as a result of this transaction.
   *
   * @param reservationId The Id of the reservation that terminates as a result of this transaction
   */
  public void setReservationId(@Nullable Integer reservationId) {
    if (!isValidReservationId(reservationId)) {
      throw new PropertyConstraintException(reservationId, "reservationId is invalid");
    }
    this.reservationId = reservationId;
  }

  /**
   * Returns whether the given reservationId is valid
   *
   * @param reservationId the reservationId to check the validity of
   * @return {@code true} if reservationId is valid, {@code false} if not
   */
  private boolean isValidReservationId(@Nullable Integer reservationId) {
    return reservationId == null || (reservationId >= 0);
  }

  /**
   * Adds the Id of the reservation that terminates as a result of this transaction.
   *
   * @param reservationId The Id of the reservation that terminates as a result of this transaction
   * @return this
   */
  public TransactionEventRequest withReservationId(@Nullable Integer reservationId) {
    setReservationId(reservationId);
    return this;
  }

  /**
   * Gets the current preconditioning status of the BMS in the EV. Default value is Unknown.
   *
   * @return The current preconditioning status of the BMS in the EV
   */
  @Nullable
  public PreconditioningStatusEnum getPreconditioningStatus() {
    return preconditioningStatus;
  }

  /**
   * Sets the current preconditioning status of the BMS in the EV. Default value is Unknown.
   *
   * @param preconditioningStatus The current preconditioning status of the BMS in the EV
   */
  public void setPreconditioningStatus(@Nullable PreconditioningStatusEnum preconditioningStatus) {
    this.preconditioningStatus = preconditioningStatus;
  }

  /**
   * Adds the current preconditioning status of the BMS in the EV. Default value is Unknown.
   *
   * @param preconditioningStatus The current preconditioning status of the BMS in the EV
   * @return this
   */
  public TransactionEventRequest withPreconditioningStatus(
      @Nullable PreconditioningStatusEnum preconditioningStatus) {
    setPreconditioningStatus(preconditioningStatus);
    return this;
  }

  /**
   * Gets true when EVSE electronics are in sleep mode for this transaction. Default value (when
   * absent) is false.
   *
   * @return True when EVSE electronics are in sleep mode for this transaction
   */
  @Nullable
  public Boolean getEvseSleep() {
    return evseSleep;
  }

  /**
   * Sets true when EVSE electronics are in sleep mode for this transaction. Default value (when
   * absent) is false.
   *
   * @param evseSleep True when EVSE electronics are in sleep mode for this transaction
   */
  public void setEvseSleep(@Nullable Boolean evseSleep) {
    this.evseSleep = evseSleep;
  }

  /**
   * Adds true when EVSE electronics are in sleep mode for this transaction. Default value (when
   * absent) is false.
   *
   * @param evseSleep True when EVSE electronics are in sleep mode for this transaction
   * @return this
   */
  public TransactionEventRequest withEvseSleep(@Nullable Boolean evseSleep) {
    setEvseSleep(evseSleep);
    return this;
  }

  /**
   * Gets transactionInfo
   *
   * @return transactionInfo
   */
  public Transaction getTransactionInfo() {
    return transactionInfo;
  }

  /**
   * Sets transactionInfo
   *
   * @param transactionInfo transactionInfo
   */
  public void setTransactionInfo(Transaction transactionInfo) {
    if (!isValidTransactionInfo(transactionInfo)) {
      throw new PropertyConstraintException(transactionInfo, "transactionInfo is invalid");
    }
    this.transactionInfo = transactionInfo;
  }

  /**
   * Returns whether the given transactionInfo is valid
   *
   * @param transactionInfo the transactionInfo to check the validity of
   * @return {@code true} if transactionInfo is valid, {@code false} if not
   */
  private boolean isValidTransactionInfo(Transaction transactionInfo) {
    return transactionInfo != null && transactionInfo.validate();
  }

  /**
   * Gets electric Vehicle Supply Equipment
   *
   * @return Electric Vehicle Supply Equipment
   */
  @Nullable
  public EVSE getEvse() {
    return evse;
  }

  /**
   * Sets electric Vehicle Supply Equipment
   *
   * @param evse Electric Vehicle Supply Equipment
   */
  public void setEvse(@Nullable EVSE evse) {
    if (!isValidEvse(evse)) {
      throw new PropertyConstraintException(evse, "evse is invalid");
    }
    this.evse = evse;
  }

  /**
   * Returns whether the given evse is valid
   *
   * @param evse the evse to check the validity of
   * @return {@code true} if evse is valid, {@code false} if not
   */
  private boolean isValidEvse(@Nullable EVSE evse) {
    return evse == null || evse.validate();
  }

  /**
   * Adds electric Vehicle Supply Equipment
   *
   * @param evse Electric Vehicle Supply Equipment
   * @return this
   */
  public TransactionEventRequest withEvse(@Nullable EVSE evse) {
    setEvse(evse);
    return this;
  }

  /**
   * Gets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @return A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
  @Nullable
  public IdToken getIdToken() {
    return idToken;
  }

  /**
   * Sets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
  public void setIdToken(@Nullable IdToken idToken) {
    if (!isValidIdToken(idToken)) {
      throw new PropertyConstraintException(idToken, "idToken is invalid");
    }
    this.idToken = idToken;
  }

  /**
   * Returns whether the given idToken is valid
   *
   * @param idToken the idToken to check the validity of
   * @return {@code true} if idToken is valid, {@code false} if not
   */
  private boolean isValidIdToken(@Nullable IdToken idToken) {
    return idToken == null || idToken.validate();
  }

  /**
   * Adds a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   * @return this
   */
  public TransactionEventRequest withIdToken(@Nullable IdToken idToken) {
    setIdToken(idToken);
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
  public TransactionEventRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCostDetails(costDetails)
        && isValidEventType(eventType)
        && isValidMeterValue(meterValue)
        && isValidTimestamp(timestamp)
        && isValidTriggerReason(triggerReason)
        && isValidSeqNo(seqNo)
        && isValidNumberOfPhasesUsed(numberOfPhasesUsed)
        && isValidReservationId(reservationId)
        && isValidTransactionInfo(transactionInfo)
        && isValidEvse(evse)
        && isValidIdToken(idToken)
        && isValidCustomData(customData);
  }

  @Override
  public boolean transactionRelated() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionEventRequest that = (TransactionEventRequest) o;
    return Objects.equals(costDetails, that.costDetails)
        && Objects.equals(eventType, that.eventType)
        && Arrays.equals(meterValue, that.meterValue)
        && Objects.equals(timestamp, that.timestamp)
        && Objects.equals(triggerReason, that.triggerReason)
        && Objects.equals(seqNo, that.seqNo)
        && Objects.equals(offline, that.offline)
        && Objects.equals(numberOfPhasesUsed, that.numberOfPhasesUsed)
        && Objects.equals(cableMaxCurrent, that.cableMaxCurrent)
        && Objects.equals(reservationId, that.reservationId)
        && Objects.equals(preconditioningStatus, that.preconditioningStatus)
        && Objects.equals(evseSleep, that.evseSleep)
        && Objects.equals(transactionInfo, that.transactionInfo)
        && Objects.equals(evse, that.evse)
        && Objects.equals(idToken, that.idToken)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        costDetails,
        eventType,
        Arrays.hashCode(meterValue),
        timestamp,
        triggerReason,
        seqNo,
        offline,
        numberOfPhasesUsed,
        cableMaxCurrent,
        reservationId,
        preconditioningStatus,
        evseSleep,
        transactionInfo,
        evse,
        idToken,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("costDetails", costDetails)
        .add("eventType", eventType)
        .add("meterValue", meterValue)
        .add("timestamp", timestamp)
        .add("triggerReason", triggerReason)
        .add("seqNo", seqNo)
        .add("offline", offline)
        .add("numberOfPhasesUsed", numberOfPhasesUsed)
        .add("cableMaxCurrent", cableMaxCurrent)
        .add("reservationId", reservationId)
        .add("preconditioningStatus", preconditioningStatus)
        .add("evseSleep", evseSleep)
        .add("transactionInfo", transactionInfo)
        .add("evse", evse)
        .add("idToken", idToken)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
