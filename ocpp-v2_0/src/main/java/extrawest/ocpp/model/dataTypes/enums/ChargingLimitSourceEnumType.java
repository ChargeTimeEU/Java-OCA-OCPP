package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Source of the charging limit.
 *
 *
 */
public enum ChargingLimitSourceEnumType {

    EMS("EMS"),
    OTHER("Other"),
    SO("SO"),
    CSO("CSO");
    private final String value;

    ChargingLimitSourceEnumType(String value) {
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
    public static ChargingLimitSourceEnumType fromValue(String value) {
        return findByField(
                ChargingLimitSourceEnumType.class,
                ChargingLimitSourceEnumType::value,
                value
        );
    }
}
