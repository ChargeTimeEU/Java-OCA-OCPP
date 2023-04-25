package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Indicates if the Charging Station was able to execute the request.
 *
 *
 */
public enum ClearChargingProfileStatusEnumType {

    ACCEPTED("Accepted"),
    UNKNOWN("Unknown");
    private final String value;

    ClearChargingProfileStatusEnumType(String value) {
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
    public static ClearChargingProfileStatusEnumType fromValue(String value) {
        return findByField(
                ClearChargingProfileStatusEnumType.class,
                ClearChargingProfileStatusEnumType::value,
                value
        );
    }
}
