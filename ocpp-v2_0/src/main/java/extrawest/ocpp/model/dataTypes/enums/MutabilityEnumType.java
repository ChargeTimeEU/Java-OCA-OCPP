package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Defines the mutability of this attribute. Default is ReadWrite when omitted.
 *
 *
 */
public enum MutabilityEnumType {

    READ_ONLY("ReadOnly"),
    WRITE_ONLY("WriteOnly"),
    READ_WRITE("ReadWrite");
    private final String value;

    MutabilityEnumType(String value) {
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
    public static MutabilityEnumType fromValue(String value) {
        return findByField(
                MutabilityEnumType.class,
                MutabilityEnumType::value,
                value
        );
    }
}
