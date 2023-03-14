package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/**
 * This contains the reason for sending this message to the CSMS.
 *
 *
 */
public enum BootReasonEnumType {

    APPLICATION_RESET("ApplicationReset"),
    FIRMWARE_UPDATE("FirmwareUpdate"),
    LOCAL_RESET("LocalReset"),
    POWER_UP("PowerUp"),
    REMOTE_RESET("RemoteReset"),
    SCHEDULED_RESET("ScheduledReset"),
    TRIGGERED("Triggered"),
    UNKNOWN("Unknown"),
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
