package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * VPN. Type. VPN_ Code
 * urn:x-oca:ocpp:uid:1:569277
 * Type of VPN
 *
 *
 */
public enum VPNEnumType {

    IK_EV_2("IKEv2"),
    IP_SEC("IPSec"),
    L_2_TP("L2TP"),
    PPTP("PPTP");
    private final String value;

    VPNEnumType(String value) {
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
    public static VPNEnumType fromValue(String value) {
        return findByField(
                VPNEnumType.class,
                VPNEnumType::value,
                value
        );
    }
}
