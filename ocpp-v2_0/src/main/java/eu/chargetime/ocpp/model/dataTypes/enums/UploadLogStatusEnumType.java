package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * This contains the status of the log upload.
 *
 *
 */
public enum UploadLogStatusEnumType {

    BAD_MESSAGE("BadMessage"),
    IDLE("Idle"),
    NOT_SUPPORTED_OPERATION("NotSupportedOperation"),
    PERMISSION_DENIED("PermissionDenied"),
    UPLOADED("Uploaded"),
    UPLOAD_FAILURE("UploadFailure"),
    UPLOADING("Uploading"),
    ACCEPTED_CANCELED("AcceptedCanceled");
    private final String value;

    UploadLogStatusEnumType(String value) {
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
    public static UploadLogStatusEnumType fromValue(String value) {
        return findByField(
                UploadLogStatusEnumType.class,
                UploadLogStatusEnumType::value,
                value
        );
    }
}
