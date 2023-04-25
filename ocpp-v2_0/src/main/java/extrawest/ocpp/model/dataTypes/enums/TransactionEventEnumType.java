package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;

/**
 * This contains the type of this event.
 * The first TransactionEvent of a transaction SHALL contain: "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All others SHALL contain: "Updated"
 *
 *
 */
public enum TransactionEventEnumType {

    ENDED("Ended"),
    STARTED("Started"),
    UPDATED("Updated");
    private final String value;

    TransactionEventEnumType(String value) {
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
    public static TransactionEventEnumType fromValue(String value) {
        return findByField(
                TransactionEventEnumType.class,
                TransactionEventEnumType::value,
                value
        );
    }

}
