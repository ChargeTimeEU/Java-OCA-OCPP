package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Result of operation.
 *
 *
 */
public enum SetNetworkProfileStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    FAILED("Failed");
    private final String value;

    SetNetworkProfileStatusEnumType(String value) {
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
    public static SetNetworkProfileStatusEnumType fromValue(String value) {
        return findByField(
                SetNetworkProfileStatusEnumType.class,
                SetNetworkProfileStatusEnumType::value,
                value
        );
    }
}
