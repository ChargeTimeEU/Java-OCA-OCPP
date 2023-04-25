package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Applicable Network Interface.
 *
 *
 */
public enum OCPPInterfaceEnumType {

    WIRED_0("Wired0"),
    WIRED_1("Wired1"),
    WIRED_2("Wired2"),
    WIRED_3("Wired3"),
    WIRELESS_0("Wireless0"),
    WIRELESS_1("Wireless1"),
    WIRELESS_2("Wireless2"),
    WIRELESS_3("Wireless3");
    private final String value;

    OCPPInterfaceEnumType(String value) {
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
    public static OCPPInterfaceEnumType fromValue(String value) {
        return findByField(
                OCPPInterfaceEnumType.class,
                OCPPInterfaceEnumType::value,
                value
        );
    }
}
