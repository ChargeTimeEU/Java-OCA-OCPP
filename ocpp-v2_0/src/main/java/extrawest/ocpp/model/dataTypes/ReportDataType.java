package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


/**
 * Class to report components, variables and variable attributes and characteristics.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "component",
        "variable",
        "variableAttribute",
        "variableCharacteristics"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ReportDataType implements Validatable {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

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
    @JsonProperty("variableAttribute")
    public List<VariableAttributeType> variableAttribute;
    /**
     * Fixed read-only parameters of a variable.
     *
     *
     */
    @JsonProperty("variableCharacteristics")
    public VariableCharacteristicsType variableCharacteristics;

    public ReportDataType(ComponentType component,
                          VariableType variable,
                          List<VariableAttributeType> variableAttribute) {
        requiredValidator.validate(component);
        requiredValidator.validate(variable);
        requiredValidator.validate(variableAttribute);
        this.component = component;
        this.variable = variable;
        this.variableAttribute = variableAttribute;
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

    public void setVariableAttribute(List<VariableAttributeType> variableAttribute) {
        requiredValidator.validate(variableAttribute);
        this.variableAttribute = variableAttribute;
    }

    public void setVariableCharacteristics(VariableCharacteristicsType variableCharacteristics) {
        this.variableCharacteristics = variableCharacteristics;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(component)
                &&requiredValidator.safeValidate(variable)
                &&requiredValidator.safeValidate(variableAttribute)
                && component.validate()
                &&variable.validate()
                &&variableAttribute.stream().filter(VariableAttributeType::validate).count() == variableAttribute.size();
    }
}
