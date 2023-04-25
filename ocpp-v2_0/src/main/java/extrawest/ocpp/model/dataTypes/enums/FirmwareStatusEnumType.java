package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This contains the progress status of the firmware installation.
 *
 *
 */
public enum FirmwareStatusEnumType {

    DOWNLOADED("Downloaded"),
    DOWNLOAD_FAILED("DownloadFailed"),
    DOWNLOADING("Downloading"),
    DOWNLOAD_SCHEDULED("DownloadScheduled"),
    DOWNLOAD_PAUSED("DownloadPaused"),
    IDLE("Idle"),
    INSTALLATION_FAILED("InstallationFailed"),
    INSTALLING("Installing"),
    INSTALLED("Installed"),
    INSTALL_REBOOTING("InstallRebooting"),
    INSTALL_SCHEDULED("InstallScheduled"),
    INSTALL_VERIFICATION_FAILED("InstallVerificationFailed"),
    INVALID_SIGNATURE("InvalidSignature"),
    SIGNATURE_VERIFIED("SignatureVerified");
    private final String value;

    FirmwareStatusEnumType(String value) {
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
    public static FirmwareStatusEnumType fromValue(String value) {
        return findByField(
                FirmwareStatusEnumType.class,
                FirmwareStatusEnumType::value,
                value
        );
    }
}
