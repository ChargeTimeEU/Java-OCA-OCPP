package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This indicates whether the Charging Station is able to perform the reset.
 *
 *
 */
public enum ResetStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    SCHEDULED("Scheduled");
    private final String value;

    ResetStatusEnumType(String value) {
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
    public static ResetStatusEnumType fromValue(String value) {
        return findByField(
                ResetStatusEnumType.class,
                ResetStatusEnumType::value,
                value
        );
    }
}
