package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "id"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ClearDisplayMessageRequest extends RequestWithId {

    private transient RequiredValidator requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Id of the message that SHALL be removed from the Charging Station.
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    public Integer id;

    @Override
    public boolean transactionRelated() {
        return false;
    }

    public ClearDisplayMessageRequest(Integer id) {
        requiredValidator.validate(id);
        this.id = id;
    }

    public void setCustomData(CustomDataType customData) {
        requiredValidator.validate(id);
        this.customData = customData;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(id);
    }
}
