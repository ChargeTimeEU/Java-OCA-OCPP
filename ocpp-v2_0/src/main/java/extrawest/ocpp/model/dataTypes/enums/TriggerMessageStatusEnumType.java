package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Indicates whether the Charging Station will send the requested notification or not.
 *
 *
 */
public enum TriggerMessageStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    NOT_IMPLEMENTED("NotImplemented");
    private final String value;

    TriggerMessageStatusEnumType(String value) {
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
    public static TriggerMessageStatusEnumType fromValue(String value) {
        return findByField(
                TriggerMessageStatusEnumType.class,
                TriggerMessageStatusEnumType::value,
                value
        );
    }
}
