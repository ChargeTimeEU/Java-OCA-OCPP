package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;

/**
 * Enumeration of possible idToken types.
 *
 *
 */
public enum IdTokenEnumType {

    CENTRAL("Central"),
    E_MAID("eMAID"),
    ISO_14443("ISO14443"),
    ISO_15693("ISO15693"),
    KEY_CODE("KeyCode"),
    LOCAL("Local"),
    MAC_ADDRESS("MacAddress"),
    NO_AUTHORIZATION("NoAuthorization");
    private final String value;

    IdTokenEnumType(String value) {
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
    public static IdTokenEnumType fromValue(String value) {
        return findByField(
                IdTokenEnumType.class,
                IdTokenEnumType::value,
                value
        );
    }

}
