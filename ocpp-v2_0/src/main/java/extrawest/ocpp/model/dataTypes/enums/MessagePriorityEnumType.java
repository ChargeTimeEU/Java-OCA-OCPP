package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * If provided the Charging Station shall return Display Messages with the given priority only.
 *
 *
 */
public enum MessagePriorityEnumType {

    ALWAYS_FRONT("AlwaysFront"),
    IN_FRONT("InFront"),
    NORMAL_CYCLE("NormalCycle");
    private final String value;

    MessagePriorityEnumType(String value) {
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
    public static MessagePriorityEnumType fromValue(String value) {
        return findByField(
                MessagePriorityEnumType.class,
                MessagePriorityEnumType::value,
                value
        );
    }
}
