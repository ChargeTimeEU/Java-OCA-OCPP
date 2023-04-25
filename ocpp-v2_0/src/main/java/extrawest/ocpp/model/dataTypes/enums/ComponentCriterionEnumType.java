package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;

public enum ComponentCriterionEnumType {

    ACTIVE("Active"),
    AVAILABLE("Available"),
    ENABLED("Enabled"),
    PROBLEM("Problem");
    private final String value;

    ComponentCriterionEnumType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static ComponentCriterionEnumType fromValue(String value) {
        return findByField(
                ComponentCriterionEnumType.class,
                ComponentCriterionEnumType::value,
                value
        );
    }
}
