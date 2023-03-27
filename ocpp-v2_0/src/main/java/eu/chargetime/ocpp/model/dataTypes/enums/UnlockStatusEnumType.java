package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * This indicates whether the Charging Station has unlocked the connector.
 *
 *
 */
public enum UnlockStatusEnumType {

    UNLOCKED("Unlocked"),
    UNLOCK_FAILED("UnlockFailed"),
    ONGOING_AUTHORIZED_TRANSACTION("OngoingAuthorizedTransaction"),
    UNKNOWN_CONNECTOR("UnknownConnector");
    private final String value;

    UnlockStatusEnumType(String value) {
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
    public static UnlockStatusEnumType fromValue(String value) {
        return findByField(
                UnlockStatusEnumType.class,
                UnlockStatusEnumType::value,
                value
        );
    }
}
