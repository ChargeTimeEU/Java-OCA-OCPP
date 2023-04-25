package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This indicates whether the Charging Station is able to display the message.
 *
 *
 */
public enum DisplayMessageStatusEnumType {

    ACCEPTED("Accepted"),
    NOT_SUPPORTED_MESSAGE_FORMAT("NotSupportedMessageFormat"),
    REJECTED("Rejected"),
    NOT_SUPPORTED_PRIORITY("NotSupportedPriority"),
    NOT_SUPPORTED_STATE("NotSupportedState"),
    UNKNOWN_TRANSACTION("UnknownTransaction");
    private final String value;

    DisplayMessageStatusEnumType(String value) {
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
    public static DisplayMessageStatusEnumType fromValue(String value) {
        return findByField(
                DisplayMessageStatusEnumType.class,
                DisplayMessageStatusEnumType::value,
                value
        );
    }
}
