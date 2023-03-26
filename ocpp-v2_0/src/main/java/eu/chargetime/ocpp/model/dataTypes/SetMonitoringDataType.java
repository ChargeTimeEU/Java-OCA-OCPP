package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.dataTypes.enums.MonitorEnumType;
import eu.chargetime.ocpp.model.validation.IntegerBetweenZeroAndNine;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * Class to hold parameters of SetVariableMonitoring request.
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
        "severity",
        "component",
        "variable"
})
@EqualsAndHashCode
@Getter
@ToString
public class SetMonitoringDataType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    private transient Validator<Integer> integerBetweenZeroAndNineValidator = new IntegerBetweenZeroAndNine();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * An id SHALL only be given to replace an existing monitor. The Charging Station handles the generation of id's for new monitors.
     *
     *
     *
     */
    @JsonProperty("id")
    public Integer id;
    /**
     * Monitor only active when a transaction is ongoing on a component relevant to this transaction. Default = false.
     *
     *
     *
     */
    @JsonProperty("transaction")
    public Boolean transaction = false;
    /**
     * Value for threshold or delta monitoring.
     * For Periodic or PeriodicClockAligned this is the interval in seconds.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("value")
    public Float value;
    /**
     * The type of this monitor, e.g. a threshold, delta or periodic monitor.
     *
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
     *
     * (Required)
     *
     */
    @JsonProperty("severity")
    public Integer severity;
    /**
     * A physical or logical component
     *
     * (Required)
     *
     */
    @JsonProperty("component")
    public ComponentType component;
    /**
     * Reference key to a component-variable.
     *
     * (Required)
     *
     */
    @JsonProperty("variable")
    public VariableType variable;

    public SetMonitoringDataType(Float value,
                                 MonitorEnumType type,
                                 Integer severity,
                                 ComponentType component,
                                 VariableType variable) {
        requiredValidator.validate(value);
        requiredValidator.validate(type);
        integerBetweenZeroAndNineValidator.validate(severity);
        requiredValidator.validate(component);
        requiredValidator.validate(variable);
        this.value = value;
        this.type = type;
        this.severity = severity;
        this.component = component;
        this.variable = variable;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTransaction(Boolean transaction) {
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

    public void setComponent(ComponentType component) {
        requiredValidator.validate(component);
        this.component = component;
    }

    public void setVariable(VariableType variable) {
        requiredValidator.validate(variable);
        this.variable = variable;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(value)
                &&requiredValidator.safeValidate(type)
                &&integerBetweenZeroAndNineValidator.safeValidate(severity)
                &&requiredValidator.safeValidate(component)
                &&requiredValidator.safeValidate(variable);
    }
}
