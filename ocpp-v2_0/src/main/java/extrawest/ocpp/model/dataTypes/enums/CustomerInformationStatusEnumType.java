package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Indicates whether the request was accepted.
 *
 *
 */
public enum CustomerInformationStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    INVALID("Invalid");
    private final String value;

    CustomerInformationStatusEnumType(String value) {
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
    public static CustomerInformationStatusEnumType fromValue(String value) {
        return findByField(
                CustomerInformationStatusEnumType.class,
                CustomerInformationStatusEnumType::value,
                value
        );
    }
}
