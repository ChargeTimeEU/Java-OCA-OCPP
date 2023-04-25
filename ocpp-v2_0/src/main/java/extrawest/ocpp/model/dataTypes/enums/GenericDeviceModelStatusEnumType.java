package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This indicates whether the Charging Station is able to accept this request.
 *
 *
 */
public enum GenericDeviceModelStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    NOT_SUPPORTED("NotSupported"),
    EMPTY_RESULT_SET("EmptyResultSet");
    private final String value;

    GenericDeviceModelStatusEnumType(String value) {
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
    public static GenericDeviceModelStatusEnumType fromValue(String value) {
        return findByField(
                GenericDeviceModelStatusEnumType.class,
                GenericDeviceModelStatusEnumType::value,
                value
        );
    }
}
