package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Returns whether the Charging Station has been able to process the message successfully. This does not guarantee the schedule will be followed to the letter. There might be other constraints the Charging Station may need to take into account.
 *
 *
 */
public enum ChargingProfileStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected");
    private final String value;

    ChargingProfileStatusEnumType(String value) {
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
    public static ChargingProfileStatusEnumType fromValue(String value) {
        return findByField(
                ChargingProfileStatusEnumType.class,
                ChargingProfileStatusEnumType::value,
                value
        );
    }
}
