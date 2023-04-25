package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;

/**
 * Status indicating whether the Charging Station accepts the request to start a transaction.
 *
 *
 */
public enum RequestStartStopStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected");
    private final String value;

    RequestStartStopStatusEnumType(String value) {
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
    public static RequestStartStopStatusEnumType fromValue(String value) {
        return findByField(
                RequestStartStopStatusEnumType.class,
                RequestStartStopStatusEnumType::value,
                value
        );
    }

}
