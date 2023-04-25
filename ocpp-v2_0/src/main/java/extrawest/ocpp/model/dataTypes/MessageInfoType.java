package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.MessagePriorityEnumType;
import extrawest.ocpp.model.dataTypes.enums.MessageStateEnumType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;

import java.time.LocalDateTime;


/**
 * Message_ Info
 * urn:x-enexis:ecdm:uid:2:233264
 * Contains message details, for a message to be displayed on a Charging Station.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "display",
        "id",
        "priority",
        "state",
        "startDateTime",
        "endDateTime",
        "transactionId",
        "message"
})
public class MessageInfoType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    private transient Validator transactionIdValidator = new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string36())
                    .build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * A physical or logical component
     *
     *
     */
    @JsonProperty("display")
    public ComponentType display;
    /**
     * Identified_ Object. MRID. Numeric_ Identifier
     * urn:x-enexis:ecdm:uid:1:569198
     * Master resource identifier, unique within an exchange context. It is defined within the OCPP context as a positive Integer value (greater or equal to zero).
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    public Integer id;
    /**
     * Message_ Info. Priority. Message_ Priority_ Code
     * urn:x-enexis:ecdm:uid:1:569253
     * With what priority should this message be shown
     *
     * (Required)
     *
     */
    @JsonProperty("priority")
    public MessagePriorityEnumType priority;
    /**
     * Message_ Info. State. Message_ State_ Code
     * urn:x-enexis:ecdm:uid:1:569254
     * During what state should this message be shown. When omitted this message should be shown in any state of the Charging Station.
     *
     *
     */
    @JsonProperty("state")
    public MessageStateEnumType state;
    /**
     * Message_ Info. Start. Date_ Time
     * urn:x-enexis:ecdm:uid:1:569256
     * From what date-time should this message be shown. If omitted: directly.
     *
     *
     */
    @JsonProperty("startDateTime")
    public LocalDateTime startDateTime;
    /**
     * Message_ Info. End. Date_ Time
     * urn:x-enexis:ecdm:uid:1:569257
     * Until what date-time should this message be shown, after this date/time this message SHALL be removed.
     *
     *
     */
    @JsonProperty("endDateTime")
    public LocalDateTime endDateTime;
    /**
     * During which transaction shall this message be shown.
     * Message SHALL be removed by the Charging Station after transaction has
     * ended.
     *
     *
     */
    @JsonProperty("transactionId")
    public String transactionId;
    /**
     * Message_ Content
     * urn:x-enexis:ecdm:uid:2:234490
     * Contains message details, for a message to be displayed on a Charging Station.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("message")
    public MessageContentType message;

    public MessageInfoType(Integer id, MessagePriorityEnumType priority, MessageContentType message) {
        requiredValidator.validate(id);
        requiredValidator.validate(priority);
        requiredValidator.validate(message);
        this.id = id;
        this.priority = priority;
        this.message = message;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setDisplay(ComponentType display) {
        this.display = display;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPriority(MessagePriorityEnumType priority) {
        this.priority = priority;
    }

    public void setState(MessageStateEnumType state) {
        this.state = state;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setTransactionId(String transactionId) {
        transactionIdValidator.validate(transactionId);
        this.transactionId = transactionId;
    }

    public void setMessage(MessageContentType message) {
        this.message = message;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(id)
                &&requiredValidator.safeValidate(priority)
                &&requiredValidator.safeValidate(message);
    }
}
