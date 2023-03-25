package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * This indicates the success or failure of the reservation.
 *
 *
 */
public enum ReserveNowStatusEnumType {

    ACCEPTED("Accepted"),
    FAULTED("Faulted"),
    OCCUPIED("Occupied"),
    REJECTED("Rejected"),
    UNAVAILABLE("Unavailable");
    private final String value;

    ReserveNowStatusEnumType(String value) {
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
    public static ReserveNowStatusEnumType fromValue(String value) {
        return findByField(
                ReserveNowStatusEnumType.class,
                ReserveNowStatusEnumType::value,
                value
        );
    }
}
