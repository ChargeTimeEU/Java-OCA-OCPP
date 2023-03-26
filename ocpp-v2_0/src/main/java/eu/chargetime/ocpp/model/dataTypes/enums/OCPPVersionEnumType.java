package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Communication_ Function. OCPP_ Version. OCPP_ Version_ Code
 * urn:x-oca:ocpp:uid:1:569355
 * Defines the OCPP version used for this communication function.
 *
 *
 */
public enum OCPPVersionEnumType {

    OCPP_12("OCPP12"),
    OCPP_15("OCPP15"),
    OCPP_16("OCPP16"),
    OCPP_20("OCPP20");
    private final String value;

    OCPPVersionEnumType(String value) {
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
    public static OCPPVersionEnumType fromValue(String value) {
        return findByField(
                OCPPVersionEnumType.class,
                OCPPVersionEnumType::value,
                value
        );
    }
}
