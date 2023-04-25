package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Returns whether the Charging Station has been able to remove the message.
 *
 *
 */
public enum ClearMessageStatusEnumType {

    ACCEPTED("Accepted"),
    UNKNOWN("Unknown");
    private final String value;

    ClearMessageStatusEnumType(String value) {
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
    public static ClearMessageStatusEnumType fromValue(String value) {
        return findByField(
                ClearMessageStatusEnumType.class,
                ClearMessageStatusEnumType::value,
                value
        );
    }
}
