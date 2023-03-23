package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


/**
 * Class to hold parameters of SetVariableMonitoring request.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "component",
        "variable",
        "variableMonitoring"
})
@Getter
@EqualsAndHashCode
@ToString
public class MonitoringDataType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
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
     *
     * (Required)
     *
     */
    @JsonProperty("variableMonitoring")
    public List<VariableMonitoringType> variableMonitoring;

    public MonitoringDataType(ComponentType component,
                              VariableType variable,
                              List<VariableMonitoringType> variableMonitoring) {
        requiredValidator.validate(component);
        requiredValidator.validate(variable);
        requiredValidator.validate(variableMonitoring);
        this.component = component;
        this.variable = variable;
        this.variableMonitoring = variableMonitoring;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setComponent(ComponentType component) {
        requiredValidator.validate(component);
        this.component = component;
    }

    public void setVariable(VariableType variable) {
        requiredValidator.validate(variable);
        this.variable = variable;
    }

    public void setVariableMonitoring(List<VariableMonitoringType> variableMonitoring) {
        requiredValidator.validate(variableMonitoring);
        this.variableMonitoring = variableMonitoring;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(component)
                &&requiredValidator.safeValidate(variable)
                &&requiredValidator.safeValidate(variableMonitoring)
                &&component.validate()
                &&variable.validate()
                &&variableMonitoring.stream().filter(VariableMonitoringType::validate).count() == variableMonitoring.size();
    }
}
