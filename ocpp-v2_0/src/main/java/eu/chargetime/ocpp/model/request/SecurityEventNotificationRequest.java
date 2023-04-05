package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "type",
        "timestamp",
        "techInfo"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SecurityEventNotificationRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    private transient Validator typeValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string50())
                    .build();

    private transient Validator techInfoValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string255())
                    .build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Type of the security event. This value should be taken from the Security events list.
     *
     * (Required)
     *
     */
    @JsonProperty("type")
    public String type;
    /**
     * Date and time at which the event occurred.
     *
     * (Required)
     *
     */
    @JsonProperty("timestamp")
    public Date timestamp;
    /**
     * Additional information about the occurred security event.
     *
     *
     */
    @JsonProperty("techInfo")
    public String techInfo;

    public SecurityEventNotificationRequest(String type, Date timestamp) {
        typeValidator.validate(type);
        requiredValidator.validate(timestamp);
        this.type = type;
        this.timestamp = timestamp;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setType(String type) {
        typeValidator.validate(type);
        this.type = type;
    }

    public void setTimestamp(Date timestamp) {
        requiredValidator.validate(timestamp);
        this.timestamp = timestamp;
    }

    public void setTechInfo(String techInfo) {
        techInfoValidator.validate(techInfo);
        this.techInfo = techInfo;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return typeValidator.safeValidate(type)
                &&requiredValidator.safeValidate(timestamp);
    }
}
