package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/**
 * Sampled_ Value. Phase. Phase_ Code
 * urn:x-oca:ocpp:uid:1:569264
 * Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
 *
 *
 */
public enum PhaseEnumType {

    L_1("L1"),
    L_2("L2"),
    L_3("L3"),
    N("N"),
    L_1_N("L1-N"),
    L_2_N("L2-N"),
    L_3_N("L3-N"),
    L_1_L_2("L1-L2"),
    L_2_L_3("L2-L3"),
    L_3_L_1("L3-L1");
    private final String value;

    PhaseEnumType(String value) {
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
    public static PhaseEnumType fromValue(String value) {
        return findByField(
                PhaseEnumType.class,
                PhaseEnumType::value,
                value
        );
    }

}
