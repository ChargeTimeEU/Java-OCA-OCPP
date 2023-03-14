package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/**
 * Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code
 * urn:x-oca:ocpp:uid:1:569231
 * Defines the purpose of the schedule transferred by this profile
 *
 *
 */
public enum ChargingProfilePurposeEnumType {

    CHARGING_STATION_EXTERNAL_CONSTRAINTS("ChargingStationExternalConstraints"),
    CHARGING_STATION_MAX_PROFILE("ChargingStationMaxProfile"),
    TX_DEFAULT_PROFILE("TxDefaultProfile"),
    TX_PROFILE("TxProfile");
    private final String value;

    ChargingProfilePurposeEnumType(String value) {
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
    public static ChargingProfilePurposeEnumType fromValue(String value) {
        return findByField(
                ChargingProfilePurposeEnumType.class,
                ChargingProfilePurposeEnumType::value,
                value
        );
    }

}
