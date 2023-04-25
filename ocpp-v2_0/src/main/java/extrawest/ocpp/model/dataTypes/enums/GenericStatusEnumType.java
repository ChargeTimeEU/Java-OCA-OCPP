package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * The Charging Station will indicate if it was
 * able to process the request
 *
 *
 */
public enum GenericStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected");
    private final String value;

    GenericStatusEnumType(String value) {
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
    public static GenericStatusEnumType fromValue(String value) {
        return findByField(
                GenericStatusEnumType.class,
                GenericStatusEnumType::value,
                value
        );
    }
}
