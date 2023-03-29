package eu.chargetime.ocpp.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

public enum FeatureConstants {

    AUTHORIZE("Authorize"),
    CANCEL_RESERVATION("CancelReservation"),

    BOOT_NOTIFICATION("BootNotification"),
    GET_VARIABLES("GetVariables"),
    SET_VARIABLES("SetVariables"),
    STATUS_NOTIFICATION("StatusNotification");

    private final String value;

    FeatureConstants(String value) {
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
    public static FeatureConstants fromValue(String value) {
        return findByField(
                FeatureConstants.class,
                FeatureConstants::value,
                value
        );
    }
}
