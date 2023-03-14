package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/**
 * Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code
 * urn:x-oca:ocpp:uid:1:569233
 * Indicates the start point of a recurrence.
 *
 *
 */
public enum RecurrencyKindEnumType {

    DAILY("Daily"),
    WEEKLY("Weekly");
    private final String value;

    RecurrencyKindEnumType(String value) {
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
    public static RecurrencyKindEnumType fromValue(String value) {
        return findByField(
                RecurrencyKindEnumType.class,
                RecurrencyKindEnumType::value,
                value
        );
    }

}
