package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Charging Station indicates if it can process the request.
 *
 *
 */
public enum GetInstalledCertificateStatusEnumType {

    ACCEPTED("Accepted"),
    NOT_FOUND("NotFound");
    private final String value;

    GetInstalledCertificateStatusEnumType(String value) {
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
    public static GetInstalledCertificateStatusEnumType fromValue(String value) {
        return findByField(
                GetInstalledCertificateStatusEnumType.class,
                GetInstalledCertificateStatusEnumType::value,
                value
        );
    }
}
