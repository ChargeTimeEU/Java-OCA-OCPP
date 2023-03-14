package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/**
 * Message_ Content. Format. Message_ Format_ Code
 * urn:x-enexis:ecdm:uid:1:570848
 * Format of the message.
 *
 *
 */
public enum MessageFormatEnumType {

    ASCII("ASCII"),
    HTML("HTML"),
    URI("URI"),
    UTF_8("UTF8");
    private final String value;

    MessageFormatEnumType(String value) {
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
    public static MessageFormatEnumType fromValue(String value) {
        return findByField(
                MessageFormatEnumType.class,
                MessageFormatEnumType::value,
                value
        );
    }

}
