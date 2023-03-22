package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Specifies the event notification type of the message.
 *
 *
 *
 */
public enum EventNotificationEnumType {

    HARD_WIRED_NOTIFICATION("HardWiredNotification"),
    HARD_WIRED_MONITOR("HardWiredMonitor"),
    PRECONFIGURED_MONITOR("PreconfiguredMonitor"),
    CUSTOM_MONITOR("CustomMonitor");
    private final String value;

    EventNotificationEnumType(String value) {
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
    public static EventNotificationEnumType fromValue(String value) {
        return findByField(
                EventNotificationEnumType.class,
                EventNotificationEnumType::value,
                value
        );
    }
}
