package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * This contains the progress status of the publishfirmware
 * installation.
 *
 *
 */
public enum PublishFirmwareStatusEnumType {

    IDLE("Idle"),
    DOWNLOAD_SCHEDULED("DownloadScheduled"),
    DOWNLOADING("Downloading"),
    DOWNLOADED("Downloaded"),
    PUBLISHED("Published"),
    DOWNLOAD_FAILED("DownloadFailed"),
    DOWNLOAD_PAUSED("DownloadPaused"),
    INVALID_CHECKSUM("InvalidChecksum"),
    CHECKSUM_VERIFIED("ChecksumVerified"),
    PUBLISH_FAILED("PublishFailed");
    private final String value;

    PublishFirmwareStatusEnumType(String value) {
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
    public static PublishFirmwareStatusEnumType fromValue(String value) {
        return findByField(
                PublishFirmwareStatusEnumType.class,
                PublishFirmwareStatusEnumType::value,
                value
        );
    }
}
