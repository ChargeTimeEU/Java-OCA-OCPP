package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Element providing more information about the status.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "reasonCode",
        "additionalInfo"
})
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class StatusInfoType implements Validatable {

    private transient Validator reasonCodeValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string20()).build();

    private transient Validator additionalInfoValidator =
            new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string512()).build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    private CustomDataType customDataType;
    /**
     * A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
     *
     * (Required)
     *
     */
    @JsonProperty("reasonCode")
    private String reasonCode;
    /**
     * Additional text to provide detailed information.
     *
     *
     */
    @JsonProperty("additionalInfo")
    private String additionalInfo;

    public StatusInfoType(String reasonCode) {
        reasonCodeValidator.validate(reasonCode);
        this.reasonCode = reasonCode;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setReasonCode(String reasonCode) {
        reasonCodeValidator.validate(reasonCode);
        this.reasonCode = reasonCode;
    }

    public void setAdditionalInfo(String additionalInfo) {
        additionalInfoValidator.validate(additionalInfo);
        this.additionalInfo = additionalInfo;
    }

    @Override
    public boolean validate() {
        return reasonCodeValidator.safeValidate(reasonCode);
    }
}
