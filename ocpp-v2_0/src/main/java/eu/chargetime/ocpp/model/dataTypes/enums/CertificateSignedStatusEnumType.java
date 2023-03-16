package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Returns whether certificate signing has been accepted, otherwise rejected.
 *
 *
 */
public enum CertificateSignedStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected");
    private final String value;

    CertificateSignedStatusEnumType(String value) {
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
    public static CertificateSignedStatusEnumType fromValue(String value) {
        return findByField(
                CertificateSignedStatusEnumType.class,
                CertificateSignedStatusEnumType::value,
                value
        );
    }
}
