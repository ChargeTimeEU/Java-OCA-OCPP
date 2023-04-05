package eu.chargetime.ocpp.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "ongoingIndicator",
        "messagesInQueue"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetTransactionStatusResponse extends Confirmation {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Whether the transaction is still ongoing.
     *
     *
     */
    @JsonProperty("ongoingIndicator")
    public Boolean ongoingIndicator;
    /**
     * Whether there are still message to be delivered.
     *
     * (Required)
     *
     */
    @JsonProperty("messagesInQueue")
    public Boolean messagesInQueue;

    public GetTransactionStatusResponse(Boolean messagesInQueue) {
        requiredValidator.validate(messagesInQueue);
        this.messagesInQueue = messagesInQueue;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setOngoingIndicator(Boolean ongoingIndicator) {
        this.ongoingIndicator = ongoingIndicator;
    }

    public void setMessagesInQueue(Boolean messagesInQueue) {
        requiredValidator.validate(messagesInQueue);
        this.messagesInQueue = messagesInQueue;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(messagesInQueue);
    }
}
