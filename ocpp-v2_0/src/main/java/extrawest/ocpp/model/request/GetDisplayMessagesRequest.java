package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.dataTypes.enums.MessagePriorityEnumType;
import extrawest.ocpp.model.dataTypes.enums.MessageStateEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "id",
        "requestId",
        "priority",
        "state"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetDisplayMessagesRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * If provided the Charging Station shall return Display Messages of the given ids. This field SHALL NOT contain more ids than set in &lt;<configkey-number-of-display-messages,NumberOfDisplayMessages.maxLimit>>
     *
     *
     *
     */
    @JsonProperty("id")
    public List<Integer> id;
    /**
     * The Id of this request.
     *
     * (Required)
     *
     */
    @JsonProperty("requestId")
    public Integer requestId;
    /**
     * If provided the Charging Station shall return Display Messages with the given priority only.
     *
     *
     */
    @JsonProperty("priority")
    public MessagePriorityEnumType priority;
    /**
     * If provided the Charging Station shall return Display Messages with the given state only.
     *
     *
     */
    @JsonProperty("state")
    public MessageStateEnumType state;

    public GetDisplayMessagesRequest(Integer requestId) {
        requiredValidator.validate(requestId);
        this.requestId = requestId;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public void setRequestId(Integer requestId) {
        requiredValidator.validate(requestId);
        this.requestId = requestId;
    }

    public void setPriority(MessagePriorityEnumType priority) {
        this.priority = priority;
    }

    public void setState(MessageStateEnumType state) {
        this.state = state;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(requestId);
    }
}
