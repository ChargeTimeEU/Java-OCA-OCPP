package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This contains the type of update (full or differential) of this request.
 *
 *
 */
public enum UpdateEnumType {

    DIFFERENTIAL("Differential"),
    FULL("Full");
    private final String value;

    UpdateEnumType(String value) {
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
    public static UpdateEnumType fromValue(String value) {
        return findByField(
                UpdateEnumType.class,
                UpdateEnumType::value,
                value
        );
    }
}
