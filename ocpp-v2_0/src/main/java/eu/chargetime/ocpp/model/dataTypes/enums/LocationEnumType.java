package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/**
 * Sampled_ Value. Location. Location_ Code
 * urn:x-oca:ocpp:uid:1:569265
 * Indicates where the measured value has been sampled. Default = "Outlet"
 *
 *
 *
 */
public enum LocationEnumType {

    BODY("Body"),
    CABLE("Cable"),
    EV("EV"),
    INLET("Inlet"),
    OUTLET("Outlet");
    private final String value;

    LocationEnumType(String value) {
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
    public static LocationEnumType fromValue(String value) {
        return findByField(
                LocationEnumType.class,
                LocationEnumType::value,
                value
        );
    }

}
