package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;

/**
 * This contains the reason for sending this message to the CSMS.
 *
 *
 */
public enum BootReasonEnumType {

    /** The Charging Station rebooted due to an application error. */
    APPLICATION_RESET("ApplicationReset"),

    /** The Charging Station rebooted due to a firmware update. */
    FIRMWARE_UPDATE("FirmwareUpdate"),

    /** The Charging Station rebooted due to a local reset command. */
    LOCAL_RESET("LocalReset"),

    /** The Charging Station powered up and registers itself with the CSMS. */
    POWER_UP("PowerUp"),

    /** The Charging Station rebooted due to a remote reset command. */
    REMOTE_RESET("RemoteReset"),

    /** The Charging Station rebooted due to a scheduled reset command. */
    SCHEDULED_RESET("ScheduledReset"),

    /** Requested by the CSMS via a TriggerMessage. */
    TRIGGERED("Triggered"),

    /** The boot reason is unknown. */
    UNKNOWN("Unknown"),

    /** The Charging Station rebooted due to an elapsed watchdog timer. */
    WATCHDOG("Watchdog");
    private final String value;

    BootReasonEnumType(String value) {
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
    public static BootReasonEnumType fromValue(String value) {
        return findByField(
                BootReasonEnumType.class,
                BootReasonEnumType::value,
                value
        );
    }

}
