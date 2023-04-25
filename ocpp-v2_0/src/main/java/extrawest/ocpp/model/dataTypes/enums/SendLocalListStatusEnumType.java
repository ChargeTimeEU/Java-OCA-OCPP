package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This indicates whether the Charging Station has successfully received and applied the update of the Local Authorization List.
 *
 *
 */
public enum SendLocalListStatusEnumType {

    ACCEPTED("Accepted"),
    FAILED("Failed"),
    VERSION_MISMATCH("VersionMismatch");
    private final String value;

    SendLocalListStatusEnumType(String value) {
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
    public static SendLocalListStatusEnumType fromValue(String value) {
        return findByField(
                SendLocalListStatusEnumType.class,
                SendLocalListStatusEnumType::value,
                value
        );
    }
}
