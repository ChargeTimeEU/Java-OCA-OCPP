package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.dataTypes.enums.ChargingStateEnumType;
import eu.chargetime.ocpp.model.dataTypes.enums.ReasonEnumType;
import eu.chargetime.ocpp.model.validation.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * Transaction
 * urn:x-oca:ocpp:uid:2:233318
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "transactionId",
        "chargingState",
        "timeSpentCharging",
        "stoppedReason",
        "remoteStartId"
})
@Getter
@ToString
@EqualsAndHashCode
public class TransactionType implements Validatable {

    private transient Validator transactionIdValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.identifierString())
                    .addRule(OCPP2PrimDatatypes.string36())
                    .setRequired(true)
                    .build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customDataType;
    /**
     * This contains the Id of the transaction.
     *
     * (Required)
     *
     */
    @JsonProperty("transactionId")
    public String transactionId;
    /**
     * Transaction. State. Transaction_ State_ Code
     * urn:x-oca:ocpp:uid:1:569419
     * Current charging state, is required when state
     * has changed.
     *
     *
     */
    @JsonProperty("chargingState")
    public ChargingStateEnumType chargingState;
    /**
     * Transaction. Time_ Spent_ Charging. Elapsed_ Time
     * urn:x-oca:ocpp:uid:1:569415
     * Contains the total time that energy flowed from EVSE to EV during the transaction (in seconds). Note that timeSpentCharging is smaller or equal to the duration of the transaction.
     *
     *
     */
    @JsonProperty("timeSpentCharging")
    public Integer timeSpentCharging;
    /**
     * Transaction. Stopped_ Reason. EOT_ Reason_ Code
     * urn:x-oca:ocpp:uid:1:569413
     * This contains the reason why the transaction was stopped. MAY only be omitted when Reason is "Local".
     *
     *
     */
    @JsonProperty("stoppedReason")
    public ReasonEnumType stoppedReason;
    /**
     * The ID given to remote start request (&lt;<requeststarttransactionrequest, RequestStartTransactionRequest>>. This enables to CSMS to match the started transaction to the given start request.
     *
     *
     */
    @JsonProperty("remoteStartId")
    public Integer remoteStartId;

    public TransactionType(String transactionId) {
        transactionIdValidator.validate(transactionId);
        this.transactionId = transactionId;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setTransactionId(String transactionId) {
        transactionIdValidator.validate(transactionId);
        this.transactionId = transactionId;
    }

    public void setChargingState(ChargingStateEnumType chargingState) {
        this.chargingState = chargingState;
    }

    public void setTimeSpentCharging(Integer timeSpentCharging) {
        this.timeSpentCharging = timeSpentCharging;
    }

    public void setStoppedReason(ReasonEnumType stoppedReason) {
        this.stoppedReason = stoppedReason;
    }

    public void setRemoteStartId(Integer remoteStartId) {
        this.remoteStartId = remoteStartId;
    }

    @Override
    public boolean validate() {
        return transactionIdValidator.safeValidate(transactionId);
    }
}
