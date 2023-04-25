package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This contains the type of availability change that the Charging Station should perform.
 *
 *
 *
 */
public enum OperationalStatusEnumType {

    INOPERATIVE("Inoperative"),
    OPERATIVE("Operative");
    private final String value;

    OperationalStatusEnumType(String value) {
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
    public static OperationalStatusEnumType fromValue(String value) {
        return findByField(
                OperationalStatusEnumType.class,
                OperationalStatusEnumType::value,
                value
        );
    }
}
