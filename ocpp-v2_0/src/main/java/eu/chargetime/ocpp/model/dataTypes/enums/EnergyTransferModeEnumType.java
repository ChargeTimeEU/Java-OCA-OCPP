package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Charging_ Needs. Requested. Energy_ Transfer_ Mode_ Code
 * urn:x-oca:ocpp:uid:1:569209
 * Mode of energy transfer requested by the EV.
 *
 *
 */
public enum EnergyTransferModeEnumType {

    DC("DC"),
    AC_SINGLE_PHASE("AC_single_phase"),
    AC_TWO_PHASE("AC_two_phase"),
    AC_THREE_PHASE("AC_three_phase");
    private final String value;

    EnergyTransferModeEnumType(String value) {
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
    public static EnergyTransferModeEnumType fromValue(String value) {
        return findByField(
                EnergyTransferModeEnumType.class,
                EnergyTransferModeEnumType::value,
                value
        );
    }
}
