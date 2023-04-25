package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This indicates whether the Charging Station is able to perform the availability change.
 *
 *
 */
public enum ChangeAvailabilityStatusEnumType {
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    SCHEDULED("Scheduled");
    private final String value;

    ChangeAvailabilityStatusEnumType(String value) {
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
    public static ChangeAvailabilityStatusEnumType fromValue(String value) {
        return findByField(
                ChangeAvailabilityStatusEnumType.class,
                ChangeAvailabilityStatusEnumType::value,
                value
        );
    }
}
