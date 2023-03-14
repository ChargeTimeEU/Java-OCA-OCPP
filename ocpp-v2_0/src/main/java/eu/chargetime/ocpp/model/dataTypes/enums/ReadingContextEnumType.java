package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/**
 * Sampled_ Value. Context. Reading_ Context_ Code
 * urn:x-oca:ocpp:uid:1:569261
 * Type of detail value: start, end or sample. Default = "Sample.Periodic"
 *
 *
 */
public enum ReadingContextEnumType {

    INTERRUPTION_BEGIN("Interruption.Begin"),
    INTERRUPTION_END("Interruption.End"),
    OTHER("Other"),
    SAMPLE_CLOCK("Sample.Clock"),
    SAMPLE_PERIODIC("Sample.Periodic"),
    TRANSACTION_BEGIN("Transaction.Begin"),
    TRANSACTION_END("Transaction.End"),
    TRIGGER("Trigger");
    private final String value;

    ReadingContextEnumType(String value) {
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
    public static ReadingContextEnumType fromValue(String value) {
        return findByField(
                ReadingContextEnumType.class,
                ReadingContextEnumType::value,
                value
        );
    }

}
