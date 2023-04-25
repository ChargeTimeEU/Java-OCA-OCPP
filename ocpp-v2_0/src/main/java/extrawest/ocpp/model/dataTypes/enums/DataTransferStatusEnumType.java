package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This indicates the success or failure of the data transfer.
 *
 *
 */
public enum DataTransferStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    UNKNOWN_MESSAGE_ID("UnknownMessageId"),
    UNKNOWN_VENDOR_ID("UnknownVendorId");
    private final String value;

    DataTransferStatusEnumType(String value) {
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
    public static DataTransferStatusEnumType fromValue(String value) {
        return findByField(
                DataTransferStatusEnumType.class,
                DataTransferStatusEnumType::value,
                value
        );
    }
}
