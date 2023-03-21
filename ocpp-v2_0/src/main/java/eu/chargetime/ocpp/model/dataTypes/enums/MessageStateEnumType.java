package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * If provided the Charging Station shall return Display Messages with the given state only.
 *
 *
 */
public enum MessageStateEnumType {

    CHARGING("Charging"),
    FAULTED("Faulted"),
    IDLE("Idle"),
    UNAVAILABLE("Unavailable");
    private final String value;

    MessageStateEnumType(String value) {
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
    public static MessageStateEnumType fromValue(String value) {
        return findByField(
                MessageStateEnumType.class,
                MessageStateEnumType::value,
                value
        );
    }
}
