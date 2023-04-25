package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;

/**
 * Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code
 * urn:x-oca:ocpp:uid:1:569232
 * Indicates the kind of schedule.
 *
 *
 */
public enum ChargingProfileKindEnumType {

    ABSOLUTE("Absolute"),
    RECURRING("Recurring"),
    RELATIVE("Relative");
    private final String value;

    ChargingProfileKindEnumType(String value) {
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
    public static ChargingProfileKindEnumType fromValue(String value) {
        return findByField(
                ChargingProfileKindEnumType.class,
                ChargingProfileKindEnumType::value,
                value
        );
    }

}
