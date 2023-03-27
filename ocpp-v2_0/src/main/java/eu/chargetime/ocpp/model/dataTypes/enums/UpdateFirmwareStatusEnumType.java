package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * This field indicates whether the Charging Station was able to accept the request.
 *
 *
 *
 */
public enum UpdateFirmwareStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    ACCEPTED_CANCELED("AcceptedCanceled"),
    INVALID_CERTIFICATE("InvalidCertificate"),
    REVOKED_CERTIFICATE("RevokedCertificate");
    private final String value;

    UpdateFirmwareStatusEnumType(String value) {
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
    public static UpdateFirmwareStatusEnumType fromValue(String value) {
        return findByField(
                UpdateFirmwareStatusEnumType.class,
                UpdateFirmwareStatusEnumType::value,
                value
        );
    }
}
