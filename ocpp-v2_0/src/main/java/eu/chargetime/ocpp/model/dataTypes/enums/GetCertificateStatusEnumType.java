package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * This indicates whether the charging station was able to retrieve the OCSP certificate status.
 *
 *
 */
public enum GetCertificateStatusEnumType {

    ACCEPTED("Accepted"),
    FAILED("Failed");
    private final String value;

    GetCertificateStatusEnumType(String value) {
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
    public static GetCertificateStatusEnumType fromValue(String value) {
        return findByField(
                GetCertificateStatusEnumType.class,
                GetCertificateStatusEnumType::value,
                value
        );
    }
}
