package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * The updated reservation status.
 *
 *
 */
public enum ReservationUpdateStatusEnumType {

    EXPIRED("Expired"),
    REMOVED("Removed");
    private final String value;

    ReservationUpdateStatusEnumType(String value) {
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
    public static ReservationUpdateStatusEnumType fromValue(String value) {
        return findByField(
                ReservationUpdateStatusEnumType.class,
                ReservationUpdateStatusEnumType::value,
                value
        );
    }
}
