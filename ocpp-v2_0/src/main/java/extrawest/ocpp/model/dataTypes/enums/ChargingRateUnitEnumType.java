package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;

/**
 * Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code
 * urn:x-oca:ocpp:uid:1:569238
 * The unit of measure Limit is expressed in.
 *
 *
 */
public enum ChargingRateUnitEnumType {

    W("W"),
    A("A");
    private final String value;

    ChargingRateUnitEnumType(String value) {
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
    public static ChargingRateUnitEnumType fromValue(String value) {
        return findByField(
                ChargingRateUnitEnumType.class,
                ChargingRateUnitEnumType::value,
                value
        );
    }
}
