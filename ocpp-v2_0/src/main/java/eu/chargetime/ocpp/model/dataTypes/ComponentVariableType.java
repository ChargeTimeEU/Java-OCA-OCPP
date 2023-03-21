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


/**
 * Class to report components, variables and variable attributes and characteristics.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "component",
        "variable"
})
@Getter
@EqualsAndHashCode
@ToString
public class ComponentVariableType implements Validatable {

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
     *
     */
    @JsonProperty("variable")
    public VariableType variable;

    public ComponentVariableType(ComponentType component) {
        requiredValidator.validate(component);
        this.component = component;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setComponent(ComponentType component) {
        requiredValidator.validate(component);
        this.component = component;
    }

    public void setVariable(VariableType variable) {
        this.variable = variable;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(component)
                && component.validate();
    }
}
