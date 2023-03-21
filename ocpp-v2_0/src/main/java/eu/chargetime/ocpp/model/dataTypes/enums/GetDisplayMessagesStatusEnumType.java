package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Indicates if the Charging Station has Display Messages that match the request criteria in the &lt;<getdisplaymessagesrequest,GetDisplayMessagesRequest>>
 *
 *
 */
public enum GetDisplayMessagesStatusEnumType {

    ACCEPTED("Accepted"),
    UNKNOWN("Unknown");
    private final String value;

    GetDisplayMessagesStatusEnumType(String value) {
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
    public static GetDisplayMessagesStatusEnumType fromValue(String value) {
        return findByField(
                GetDisplayMessagesStatusEnumType.class,
                GetDisplayMessagesStatusEnumType::value,
                value
        );
    }
}
