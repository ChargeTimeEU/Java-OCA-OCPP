package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Type of message to be triggered.
 *
 *
 */
public enum MessageTriggerEnumType {

    BOOT_NOTIFICATION("BootNotification"),
    LOG_STATUS_NOTIFICATION("LogStatusNotification"),
    FIRMWARE_STATUS_NOTIFICATION("FirmwareStatusNotification"),
    HEARTBEAT("Heartbeat"),
    METER_VALUES("MeterValues"),
    SIGN_CHARGING_STATION_CERTIFICATE("SignChargingStationCertificate"),
    SIGN_V_2_G_CERTIFICATE("SignV2GCertificate"),
    STATUS_NOTIFICATION("StatusNotification"),
    TRANSACTION_EVENT("TransactionEvent"),
    SIGN_COMBINED_CERTIFICATE("SignCombinedCertificate"),
    PUBLISH_FIRMWARE_STATUS_NOTIFICATION("PublishFirmwareStatusNotification");
    private final String value;

    MessageTriggerEnumType(String value) {
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
    public static MessageTriggerEnumType fromValue(String value) {
        return findByField(
                MessageTriggerEnumType.class,
                MessageTriggerEnumType::value,
                value
        );
    }
}
