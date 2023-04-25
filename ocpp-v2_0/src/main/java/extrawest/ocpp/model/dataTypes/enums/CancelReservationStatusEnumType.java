package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This indicates the success or failure of the canceling of a reservation by CSMS.
 *
 *
 */
public enum CancelReservationStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected");
    private final String value;

    CancelReservationStatusEnumType(String value) {
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
    public static CancelReservationStatusEnumType fromValue(String value) {
        return findByField(
                CancelReservationStatusEnumType.class,
                CancelReservationStatusEnumType::value,
                value
        );
    }

}
