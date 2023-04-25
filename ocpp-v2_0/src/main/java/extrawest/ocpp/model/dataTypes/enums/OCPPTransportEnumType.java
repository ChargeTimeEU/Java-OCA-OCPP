package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Communication_ Function. OCPP_ Transport. OCPP_ Transport_ Code
 * urn:x-oca:ocpp:uid:1:569356
 * Defines the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.0, but is supported by other versions of OCPP.
 *
 *
 */
public enum OCPPTransportEnumType {

    JSON("JSON"),
    SOAP("SOAP");
    private final String value;

    OCPPTransportEnumType(String value) {
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
    public static OCPPTransportEnumType fromValue(String value) {
        return findByField(
                OCPPTransportEnumType.class,
                OCPPTransportEnumType::value,
                value
        );
    }
}
