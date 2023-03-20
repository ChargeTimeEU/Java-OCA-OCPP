package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Indicates whether the message was processed properly.
 *
 *
 */
public enum Iso15118EVCertificateStatusEnumType {

    ACCEPTED("Accepted"),
    FAILED("Failed");
    private final String value;

    Iso15118EVCertificateStatusEnumType(String value) {
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
    public static Iso15118EVCertificateStatusEnumType fromValue(String value) {
        return findByField(
                Iso15118EVCertificateStatusEnumType.class,
                Iso15118EVCertificateStatusEnumType::value,
                value
        );
    }
}
