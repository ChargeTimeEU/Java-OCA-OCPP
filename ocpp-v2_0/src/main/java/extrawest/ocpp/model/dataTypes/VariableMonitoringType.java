package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.MonitorEnumType;
import extrawest.ocpp.model.validation.IntegerBetweenZeroAndNine;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * A monitoring setting for a variable.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "id",
        "transaction",
        "value",
        "type",
        "severity"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class VariableMonitoringType implements Validatable {

    private final transient Validator<Integer> integerBetweenZeroAndNineValidator = new IntegerBetweenZeroAndNine();

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Identifies the monitor.
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    public Integer id;
    /**
     * Monitor only active when a transaction is ongoing on a component relevant to this transaction.
     *
     * (Required)
     *
     */
    @JsonProperty("transaction")
    public Boolean transaction;
    /**
     * Value for threshold or delta monitoring.
     * For Periodic or PeriodicClockAligned this is the interval in seconds.
     *
     * (Required)
     *
     */
    @JsonProperty("value")
    public Float value;
    /**
     * The type of this monitor, e.g. a threshold, delta or periodic monitor.
     *
     * (Required)
     *
     */
    @JsonProperty("type")
    public MonitorEnumType type;
    /**
     * The severity that will be assigned to an event that is triggered by this monitor. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level.
     *
     * The severity levels have the following meaning: +
     * *0-Danger* +
     * Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. +
     * *1-Hardware Failure* +
     * Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. +
     * *2-System Failure* +
     * Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. +
     * *3-Critical* +
     * Indicates a critical error. Action is required. +
     * *4-Error* +
     * Indicates a non-urgent error. Action is required. +
     * *5-Alert* +
     * Indicates an alert event. Default severity for any type of monitoring event. +
     * *6-Warning* +
     * Indicates a warning event. Action may be required. +
     * *7-Notice* +
     * Indicates an unusual event. No immediate action is required. +
     * *8-Informational* +
     * Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. +
     * *9-Debug* +
     * Indicates information useful to developers for debugging, not useful during operations.
     *
     * (Required)
     *
     */
    @JsonProperty("severity")
    public Integer severity;

    public VariableMonitoringType(Integer id, Boolean transaction, Float value, MonitorEnumType type, Integer severity) {
        integerBetweenZeroAndNineValidator.validate(severity);
        requiredValidator.validate(id);
        requiredValidator.validate(transaction);
        requiredValidator.validate(value);
        requiredValidator.validate(type);
        this.id = id;
        this.transaction = transaction;
        this.value = value;
        this.type = type;
        this.severity = severity;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setId(Integer id) {
        requiredValidator.validate(id);
        this.id = id;
    }

    public void setTransaction(Boolean transaction) {
        requiredValidator.validate(transaction);
        this.transaction = transaction;
    }

    public void setValue(Float value) {
        requiredValidator.validate(value);
        this.value = value;
    }

    public void setType(MonitorEnumType type) {
        requiredValidator.validate(type);
        this.type = type;
    }

    public void setSeverity(Integer severity) {
        integerBetweenZeroAndNineValidator.validate(severity);
        this.severity = severity;
    }

    @Override
    public boolean validate() {
        return integerBetweenZeroAndNineValidator.safeValidate(severity)
                &&requiredValidator.safeValidate(id)
                &&requiredValidator.safeValidate(transaction)
                &&requiredValidator.safeValidate(value)
                &&requiredValidator.safeValidate(type);
    }
}
