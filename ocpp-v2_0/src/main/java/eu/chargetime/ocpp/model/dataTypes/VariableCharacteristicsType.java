package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.dataTypes.enums.DataEnumType;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * Fixed read-only parameters of a variable.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "unit",
        "dataType",
        "minLimit",
        "maxLimit",
        "valuesList",
        "supportsMonitoring"
})
@Getter
@EqualsAndHashCode
@ToString
public class VariableCharacteristicsType implements Validatable {

    private transient Validator valuesListValidator =
            new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string1000()).build();

    private transient Validator unitValidator =
            new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string16()).build();

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Unit of the variable. When the transmitted value has a unit, this field SHALL be included.
     *
     *
     */
    @JsonProperty("unit")
    public String unit;
    /**
     * Data type of this variable.
     *
     * (Required)
     *
     */
    @JsonProperty("dataType")
    public DataEnumType dataType;
    /**
     * Minimum possible value of this variable.
     *
     *
     */
    @JsonProperty("minLimit")
    public Float minLimit;
    /**
     * Maximum possible value of this variable. When the datatype of this Variable is String, OptionList, SequenceList or MemberList, this field defines the maximum length of the (CSV) string.
     *
     *
     */
    @JsonProperty("maxLimit")
    public Float maxLimit;
    /**
     * Allowed values when variable is Option/Member/SequenceList.
     *
     * * OptionList: The (Actual) Variable value must be a single value from the reported (CSV) enumeration list.
     *
     * * MemberList: The (Actual) Variable value may be an (unordered) (sub-)set of the reported (CSV) valid values list.
     *
     * * SequenceList: The (Actual) Variable value may be an ordered (priority, etc) (sub-)set of the reported (CSV) valid values.
     *
     * This is a comma separated list.
     *
     * The Configuration Variable &lt;<configkey-configuration-value-size,ConfigurationValueSize>> can be used to limit SetVariableData.attributeValue and VariableCharacteristics.valueList. The max size of these values will always remain equal.
     *
     *
     *
     */
    @JsonProperty("valuesList")
    public String valuesList;
    /**
     * Flag indicating if this variable supports monitoring.
     *
     * (Required)
     *
     */
    @JsonProperty("supportsMonitoring")
    public Boolean supportsMonitoring;

    public VariableCharacteristicsType(DataEnumType dataType, Boolean supportsMonitoring) {
        requiredValidator.validate(dataType);
        requiredValidator.validate(supportsMonitoring);
        this.dataType = dataType;
        this.supportsMonitoring = supportsMonitoring;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setUnit(String unit) {
        unitValidator.validate(unit);
        this.unit = unit;
    }

    public void setDataType(DataEnumType dataType) {
        requiredValidator.validate(dataType);
        this.dataType = dataType;
    }

    public void setMinLimit(Float minLimit) {
        this.minLimit = minLimit;
    }

    public void setMaxLimit(Float maxLimit) {
        this.maxLimit = maxLimit;
    }

    public void setValuesList(String valuesList) {
        valuesListValidator.validate(valuesList);
        this.valuesList = valuesList;
    }

    public void setSupportsMonitoring(Boolean supportsMonitoring) {
        requiredValidator.validate(supportsMonitoring);
        this.supportsMonitoring = supportsMonitoring;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(dataType)
                &&requiredValidator.safeValidate(supportsMonitoring);
    }
}
