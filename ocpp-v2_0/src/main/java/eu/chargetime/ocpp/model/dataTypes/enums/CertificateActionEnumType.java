package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Defines whether certificate needs to be installed or updated.
 *
 *
 */
public enum CertificateActionEnumType {

    INSTALL("Install"),
    UPDATE("Update");
    private final String value;

    CertificateActionEnumType(String value) {
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
    public static CertificateActionEnumType fromValue(String value) {
        return findByField(
                CertificateActionEnumType.class,
                CertificateActionEnumType::value,
                value
        );
    }
}
