package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * This contains the type of reset that the Charging Station or EVSE should perform.
 *
 *
 */
public enum ResetEnumType {

    IMMEDIATE("Immediate"),
    ON_IDLE("OnIdle");
    private final String value;

    ResetEnumType(String value) {
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
    public static ResetEnumType fromValue(String value) {
        return findByField(
                ResetEnumType.class,
                ResetEnumType::value,
                value
        );
    }
}
