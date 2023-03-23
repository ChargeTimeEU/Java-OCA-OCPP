package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.dataTypes.enums.AttributeEnumType;
import eu.chargetime.ocpp.model.dataTypes.enums.MutabilityEnumType;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * Attribute data of a variable.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "type",
        "value",
        "mutability",
        "persistent",
        "constant"
})
@Getter
@EqualsAndHashCode
@ToString
public class VariableAttributeType implements Validatable {

    private transient Validator valueValidator =
            new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string2500()).build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Attribute: Actual, MinSet, MaxSet, etc.
     * Defaults to Actual if absent.
     *
     *
     */
    @JsonProperty("type")
    public AttributeEnumType type = AttributeEnumType.ACTUAL;
    /**
     * Value of the attribute. May only be omitted when mutability is set to 'WriteOnly'.
     *
     * The Configuration Variable &lt;<configkey-reporting-value-size,ReportingValueSize>> can be used to limit GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max size of these values will always remain equal.
     *
     *
     */
    @JsonProperty("value")
    public String value;
    /**
     * Defines the mutability of this attribute. Default is ReadWrite when omitted.
     *
     *
     */
    @JsonProperty("mutability")
    public MutabilityEnumType mutability = MutabilityEnumType.READ_WRITE;
    /**
     * If true, value will be persistent across system reboots or power down. Default when omitted is false.
     *
     *
     */
    @JsonProperty("persistent")
    public Boolean persistent = false;
    /**
     * If true, value that will never be changed by the Charging Station at runtime. Default when omitted is false.
     *
     *
     */
    @JsonProperty("constant")
    public Boolean constant = false;

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setType(AttributeEnumType type) {
        this.type = type;
    }

    public void setValue(String value) {
        valueValidator.validate(value);
        this.value = value;
    }

    public void setMutability(MutabilityEnumType mutability) {
        this.mutability = mutability;
    }

    public void setPersistent(Boolean persistent) {
        this.persistent = persistent;
    }

    public void setConstant(Boolean constant) {
        this.constant = constant;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
