package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * APN. APN_ Authentication. APN_ Authentication_ Code
 * urn:x-oca:ocpp:uid:1:568828
 * Authentication method.
 *
 *
 */
public enum APNAuthenticationEnumType {

    CHAP("CHAP"),
    NONE("NONE"),
    PAP("PAP"),
    AUTO("AUTO");
    private final String value;

    APNAuthenticationEnumType(String value) {
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
    public static APNAuthenticationEnumType fromValue(String value) {
        return findByField(
                APNAuthenticationEnumType.class,
                APNAuthenticationEnumType::value,
                value
        );
    }
}
