package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.*;
import extrawest.ocpp.model.dataTypes.enums.TransactionEventEnumType;
import extrawest.ocpp.model.dataTypes.enums.TriggerReasonEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "eventType",
        "meterValue",
        "timestamp",
        "triggerReason",
        "seqNo",
        "offline",
        "numberOfPhasesUsed",
        "cableMaxCurrent",
        "reservationId",
        "transactionInfo",
        "evse",
        "idToken"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TransactionEventRequest extends RequestWithId {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * This contains the type of this event.
     * The first TransactionEvent of a transaction SHALL contain: "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All others SHALL contain: "Updated"
     *
     * (Required)
     *
     */
    @JsonProperty("eventType")
    public TransactionEventEnumType eventType;
    @JsonProperty("meterValue")
    public List<MeterValueType> meterValue;
    /**
     * The date and time at which this transaction event occurred.
     *
     * (Required)
     *
     */
    @JsonProperty("timestamp")
    public LocalDateTime timestamp;
    /**
     * Reason the Charging Station sends this message to the CSMS
     *
     * (Required)
     *
     */
    @JsonProperty("triggerReason")
    public TriggerReasonEnumType triggerReason;
    /**
     * Incremental sequence number, helps with determining if all messages of a transaction have been received.
     *
     * (Required)
     *
     */
    @JsonProperty("seqNo")
    public Integer seqNo;
    /**
     * Indication that this transaction event happened when the Charging Station was offline. Default = false, meaning: the event occurred when the Charging Station was online.
     *
     *
     */
    @JsonProperty("offline")
    public Boolean offline = false;
    /**
     * If the Charging Station is able to report the number of phases used, then it SHALL provide it. When omitted the CSMS may be able to determine the number of phases used via device management.
     *
     *
     */
    @JsonProperty("numberOfPhasesUsed")
    public Integer numberOfPhasesUsed;
    /**
     * The maximum current of the connected cable in Ampere (A).
     *
     *
     */
    @JsonProperty("cableMaxCurrent")
    public Integer cableMaxCurrent;
    /**
     * This contains the Id of the reservation that terminates as a result of this transaction.
     *
     *
     */
    @JsonProperty("reservationId")
    public Integer reservationId;
    /**
     * Transaction
     * urn:x-oca:ocpp:uid:2:233318
     *
     * (Required)
     *
     */
    @JsonProperty("transactionInfo")
    public TransactionType transactionInfo;
    /**
     * EVSE
     * urn:x-oca:ocpp:uid:2:233123
     * Electric Vehicle Supply Equipment
     *
     *
     */
    @JsonProperty("evse")
    public EVSEType evse;
    /**
     * Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
     *
     *
     */
    @JsonProperty("idToken")
    public IdTokenType idToken;

    public TransactionEventRequest(TransactionEventEnumType eventType,
                                   LocalDateTime timestamp,
                                   TriggerReasonEnumType triggerReason,
                                   Integer seqNo,
                                   TransactionType transactionInfo) {
        requiredValidator.validate(eventType);
        requiredValidator.validate(timestamp);
        requiredValidator.validate(triggerReason);
        requiredValidator.validate(seqNo);
        requiredValidator.validate(transactionInfo);
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.triggerReason = triggerReason;
        this.seqNo = seqNo;
        this.transactionInfo = transactionInfo;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setEventType(TransactionEventEnumType eventType) {
        requiredValidator.validate(eventType);
        this.eventType = eventType;
    }

    public void setMeterValue(List<MeterValueType> meterValue) {
        this.meterValue = meterValue;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        requiredValidator.validate(timestamp);
        this.timestamp = timestamp;
    }

    public void setTriggerReason(TriggerReasonEnumType triggerReason) {
        requiredValidator.validate(triggerReason);
        this.triggerReason = triggerReason;
    }

    public void setSeqNo(Integer seqNo) {
        requiredValidator.validate(seqNo);
        this.seqNo = seqNo;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    public void setNumberOfPhasesUsed(Integer numberOfPhasesUsed) {
        this.numberOfPhasesUsed = numberOfPhasesUsed;
    }

    public void setCableMaxCurrent(Integer cableMaxCurrent) {
        this.cableMaxCurrent = cableMaxCurrent;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public void setTransactionInfo(TransactionType transactionInfo) {
        requiredValidator.validate(transactionInfo);
        this.transactionInfo = transactionInfo;
    }

    public void setEvse(EVSEType evse) {
        this.evse = evse;
    }

    public void setIdToken(IdTokenType idToken) {
        this.idToken = idToken;
    }

    @Override
    public boolean transactionRelated() {
        return true;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(eventType)
                &&requiredValidator.safeValidate(timestamp)
                &&requiredValidator.safeValidate(triggerReason)
                &&requiredValidator.safeValidate(seqNo)
                &&requiredValidator.safeValidate(transactionInfo)
                &&transactionInfo.validate();
    }
}
