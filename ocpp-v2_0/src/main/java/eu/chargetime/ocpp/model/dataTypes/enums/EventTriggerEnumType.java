package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Type of monitor that triggered this event, e.g. exceeding a threshold value.
 *
 *
 *
 */
public enum EventTriggerEnumType {

    ALERTING("Alerting"),
    DELTA("Delta"),
    PERIODIC("Periodic");
    private final String value;

    EventTriggerEnumType(String value) {
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
    public static EventTriggerEnumType fromValue(String value) {
        return findByField(
                EventTriggerEnumType.class,
                EventTriggerEnumType::value,
                value
        );
    }
}
