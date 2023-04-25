package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.MonitorEnumType;
import extrawest.ocpp.model.dataTypes.enums.SetMonitoringStatusEnumType;
import extrawest.ocpp.model.validation.IntegerBetweenZeroAndNine;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Class to hold result of SetVariableMonitoring request.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "id",
        "statusInfo",
        "status",
        "type",
        "component",
        "variable",
        "severity"
})
@EqualsAndHashCode
@Getter
@ToString
@NoArgsConstructor
public class SetMonitoringResultType implements Validatable {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    private final transient Validator<Integer> integerBetweenZeroAndNineValidator = new IntegerBetweenZeroAndNine();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Id given to the VariableMonitor by the Charging Station. The Id is only returned when status is accepted. Installed VariableMonitors should have unique id's but the id's of removed Installed monitors should have unique id's but the id's of removed monitors MAY be reused.
     *
     *
     */
    @JsonProperty("id")
    public Integer id;
    /**
     * Element providing more information about the status.
     *
     *
     */
    @JsonProperty("statusInfo")
    public StatusInfoType statusInfo;
    /**
     * Status is OK if a value could be returned. Otherwise this will indicate the reason why a value could not be returned.
     *
     * (Required)
     *
     */
    @JsonProperty("status")
    public SetMonitoringStatusEnumType status;
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

    public SetMonitoringResultType(SetMonitoringStatusEnumType status,
                                   MonitorEnumType type,
                                   ComponentType component,
                                   VariableType variable,
                                   Integer severity) {
        integerBetweenZeroAndNineValidator.validate(severity);
        requiredValidator.validate(status);
        requiredValidator.validate(type);
        requiredValidator.validate(component);
        requiredValidator.validate(variable);
        this.status = status;
        this.type = type;
        this.component = component;
        this.variable = variable;
        this.severity = severity;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatusInfo(StatusInfoType statusInfo) {
        this.statusInfo = statusInfo;
    }

    public void setStatus(SetMonitoringStatusEnumType status) {
        requiredValidator.validate(status);
        this.status = status;
    }

    public void setType(MonitorEnumType type) {
        requiredValidator.validate(type);
        this.type = type;
    }

    public void setComponent(ComponentType component) {
        requiredValidator.validate(component);
        this.component = component;
    }

    public void setVariable(VariableType variable) {
        requiredValidator.validate(variable);
        this.variable = variable;
    }

    public void setSeverity(Integer severity) {
        integerBetweenZeroAndNineValidator.validate(severity);
        this.severity = severity;
    }

    @Override
    public boolean validate() {
        return integerBetweenZeroAndNineValidator.safeValidate(severity)
                &&requiredValidator.safeValidate(status)
                &&requiredValidator.safeValidate(type)
                &&requiredValidator.safeValidate(component)
                &&requiredValidator.safeValidate(variable);
    }
}
